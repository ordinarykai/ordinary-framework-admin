package com.github.ordinarykai.controller.common.code;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.UUID;
import com.github.ordinarykai.controller.common.code.vo.VerifyCodeRespVO;
import com.github.ordinarykai.framework.common.result.Result;
import com.github.ordinarykai.framework.redis.core.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.github.ordinarykai.constant.RedisConstant.REDIS_VERIFY_CODE;

/**
 * @author kai
 * @date 2022/8/15 20:52
 */
@Api(tags = "验证码")
@RestController
@RequestMapping("/api/common/code")
public class CodeController {

    @Resource
    private RedisService redisService;

    @GetMapping(value = "/verify-code")
    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码")
    public Result<VerifyCodeRespVO> getVerifyCode() {
        //自定义验证码（随机4位字母+数字，排除了O/o/0）
        String baseNumber = "123456789";
        String baseCapitalLetter = "ABCDEFGHIJKLMNPQRSTUVWXY";
        String baseSmallLetter = "abcdefghijklmnpqrstuvwxy";
        RandomGenerator randomGenerator = new RandomGenerator(baseCapitalLetter +
                baseSmallLetter + baseNumber, 4);
        //设置验证码宽高和干扰线条数
        LineCaptcha shearCaptcha = CaptchaUtil.createLineCaptcha(80, 25, 4, 15);
        shearCaptcha.setGenerator(randomGenerator);
        //将验证码存入redis,过期时间设为5分钟
        String uuid = UUID.fastUUID().toString(true);
        redisService.set(REDIS_VERIFY_CODE + uuid, shearCaptcha.getCode(), 5 * 60);
        VerifyCodeRespVO respVO = new VerifyCodeRespVO();
        respVO.setUuid(uuid);
        respVO.setBase64(shearCaptcha.getImageBase64());
        return Result.success(respVO);
    }


}
