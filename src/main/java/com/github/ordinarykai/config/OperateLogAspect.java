package com.github.ordinarykai.config;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.ordinarykai.entity.OperateLog;
import com.github.ordinarykai.framework.auth.core.AuthInfo;
import com.github.ordinarykai.framework.auth.core.AuthUtil;
import com.github.ordinarykai.framework.common.exception.ApiException;
import com.github.ordinarykai.framework.common.result.Result;
import com.github.ordinarykai.service.IOperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.github.ordinarykai.framework.common.result.ResultCode.FAILED;

/**
 * 拦截使用 @ApiOperation 注解生成操作日志
 *
 * @author 芋道源码
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Resource
    private IOperateLogService operateLogService;

    @Around("@annotation(apiOperation)")
    public Object around(ProceedingJoinPoint joinPoint, ApiOperation apiOperation) throws Throwable {
        return around0(joinPoint, apiOperation);
    }

    private Object around0(ProceedingJoinPoint joinPoint,
                           ApiOperation apiOperation) throws Throwable {
        // 目前只记录管理端的非GET请求日志
        HttpServletRequest request = com.github.ordinarykai.framework.common.util.ServletUtil.getRequest();
        String requestURI = request.getRequestURI();
        if (!requestURI.contains("/api/system")) {
            return joinPoint.proceed();
        }
        if(request.getMethod().equalsIgnoreCase("GET")){
            return joinPoint.proceed();
        }

        // 记录开始时间
        LocalDateTime startTime = LocalDateTime.now();
        try {
            // 执行原有方法
            Object result = joinPoint.proceed();
            // 记录正常执行时的操作日志
            this.log(joinPoint, apiOperation, startTime, result, null);
            return result;
        } catch (Throwable exception) {
            this.log(joinPoint, apiOperation, startTime, null, exception);
            throw exception;
        }
    }

    private void log(ProceedingJoinPoint joinPoint,
                     ApiOperation apiOperation,
                     LocalDateTime startTime, Object result, Throwable exception) {
        try {
            // 真正记录操作日志
            this.log0(joinPoint, apiOperation, startTime, result, exception);
        } catch (Throwable ex) {
            log.error("[log][记录操作日志时，发生异常，其中参数是 joinPoint({}) apiOperation({}) result({}) exception({}) ]",
                    joinPoint, apiOperation, result, exception, ex);
        }
    }

    private void log0(ProceedingJoinPoint joinPoint,
                      ApiOperation apiOperation,
                      LocalDateTime startTime, Object result, Throwable exception) {
        OperateLog operateLogObj = new OperateLog();
        // 补全通用字段
        operateLogObj.setStartTime(startTime);
        // 补充用户信息
        fillUserFields(operateLogObj);
        // 补全模块信息
        fillModuleFields(operateLogObj, joinPoint, apiOperation);
        // 补全请求信息
        fillRequestFields(operateLogObj);
        // 补全方法信息
        fillMethodFields(operateLogObj, joinPoint, startTime, result, exception);
        // 异步记录日志
        operateLogService.save(operateLogObj);
    }

    private static void fillUserFields(OperateLog operateLogObj) {
        String property = SpringUtil.getProperty("auth.enable");
        if ("true".equals(property)) {
            AuthInfo authInfo = AuthUtil.get();
            operateLogObj.setAdminId(authInfo == null ? null : authInfo.getId());
        }
    }

    private static void fillModuleFields(OperateLog operateLogObj,
                                         ProceedingJoinPoint joinPoint,
                                         ApiOperation apiOperation) {
        Api api = getClassAnnotation(joinPoint, Api.class);
        if (api != null) {
            // 优先读取 @API 的 name 属性
            if (StrUtil.isNotEmpty(api.value())) {
                operateLogObj.setModule(api.value());
            }
            // 没有的话，读取 @API 的 tags 属性
            if (StrUtil.isEmpty(operateLogObj.getModule()) && ArrayUtil.isNotEmpty(api.tags())) {
                operateLogObj.setModule(api.tags()[0]);
            }
        }
        // name 属性
        if (apiOperation != null) {
            operateLogObj.setName(apiOperation.value());
        }
        // type 属性
        RequestMethod requestMethod = obtainFirstMatchRequestMethod(obtainRequestMethod(joinPoint));
        operateLogObj.setType(requestMethod == null ? null : requestMethod.name());
    }

    private static void fillRequestFields(OperateLog operateLogObj) {
        // 获得 Request 对象
        HttpServletRequest request = com.github.ordinarykai.framework.common.util.ServletUtil.getRequest();
        if (request == null) {
            return;
        }
        // 补全请求信息
        operateLogObj.setRequestMethod(request.getMethod());
        operateLogObj.setRequestUrl(request.getRequestURI());
        operateLogObj.setUserIp(ServletUtil.getClientIP(request));
        operateLogObj.setUserAgent(request.getHeader("User-Agent"));
    }

    private static void fillMethodFields(OperateLog operateLogObj,
                                         ProceedingJoinPoint joinPoint,
                                         LocalDateTime startTime, Object result, Throwable exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        operateLogObj.setJavaMethod(methodSignature.toString());
        operateLogObj.setJavaMethodArgs(obtainMethodArgs(joinPoint));
        operateLogObj.setResultData(obtainResultData(result));
        operateLogObj.setDuration(Duration.between(LocalDateTime.now(), startTime).getNano() / 1000000);
        // （正常）处理 resultCode 和 resultMsg 字段
        if (result instanceof Result) {
            Result<?> commonResult = (Result<?>) result;
            operateLogObj.setResultCode(commonResult.getCode());
            operateLogObj.setResultMsg(commonResult.getMessage());
        }
        // （异常）处理 resultCode 和 resultMsg 字段
        if (exception != null) {
            operateLogObj.setResultCode(exception instanceof ApiException ? ((ApiException) exception).getCode() : FAILED.getCode());
            operateLogObj.setResultMsg(exception.getMessage());
        }
    }

    private static RequestMethod obtainFirstLogRequestMethod(RequestMethod[] requestMethods) {
        if (ArrayUtil.isEmpty(requestMethods)) {
            return null;
        }
        return Arrays.stream(requestMethods).filter(requestMethod ->
                        requestMethod == RequestMethod.POST
                                || requestMethod == RequestMethod.PUT
                                || requestMethod == RequestMethod.DELETE)
                .findFirst().orElse(null);
    }

    private static RequestMethod obtainFirstMatchRequestMethod(RequestMethod[] requestMethods) {
        if (ArrayUtil.isEmpty(requestMethods)) {
            return null;
        }
        // 优先，匹配最优的 POST、PUT、DELETE
        RequestMethod result = obtainFirstLogRequestMethod(requestMethods);
        if (result != null) {
            return result;
        }
        // 然后，匹配次优的 GET
        result = Arrays.stream(requestMethods).filter(requestMethod -> requestMethod == RequestMethod.GET)
                .findFirst().orElse(null);
        if (result != null) {
            return result;
        }
        // 兜底，获得第一个
        return requestMethods[0];
    }

    private static RequestMethod[] obtainRequestMethod(ProceedingJoinPoint joinPoint) {
        RequestMapping requestMapping = AnnotationUtils.getAnnotation( // 使用 Spring 的工具类，可以处理 @RequestMapping 别名注解
                ((MethodSignature) joinPoint.getSignature()).getMethod(), RequestMapping.class);
        return requestMapping != null ? requestMapping.method() : new RequestMethod[]{};
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Annotation> T getClassAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getAnnotation(annotationClass);
    }

    private static String obtainMethodArgs(ProceedingJoinPoint joinPoint) {
        // TODO 提升：参数脱敏和忽略
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] argNames = methodSignature.getParameterNames();
        Object[] argValues = joinPoint.getArgs();
        // 拼接参数
        Map<String, Object> args = new HashMap<>();
        for (int i = 0; i < argNames.length; i++) {
            String argName = argNames[i];
            Object argValue = argValues[i];
            // 被忽略时，标记为 ignore 字符串，避免和 null 混在一起
            args.put(argName, !isIgnoreArgs(argValue) ? argValue : "[ignore]");
        }
        return JSONObject.toJSONString(args);
    }

    private static String obtainResultData(Object result) {
        // TODO 提升：结果脱敏和忽略
        if (result instanceof Result) {
            result = ((Result<?>) result).getData();
        }
        return JSONObject.toJSONString(result);
    }

    private static boolean isIgnoreArgs(Object object) {
        if (Objects.isNull(object)) {
            return true;
        }
        Class<?> clazz = object.getClass();
        // 处理数组的情况
        if (clazz.isArray()) {
            return IntStream.range(0, Array.getLength(object))
                    .anyMatch(index -> isIgnoreArgs(Array.get(object, index)));
        }
        // 递归，处理数组、Collection、Map 的情况
        if (Collection.class.isAssignableFrom(clazz)) {
            return ((Collection<?>) object).stream()
                    .anyMatch((Predicate<Object>) OperateLogAspect::isIgnoreArgs);
        }
        if (Map.class.isAssignableFrom(clazz)) {
            return isIgnoreArgs(((Map<?, ?>) object).values());
        }
        // obj
        return object instanceof MultipartFile
                || object instanceof HttpServletRequest
                || object instanceof HttpServletResponse
                || object instanceof BindingResult;
    }

}
