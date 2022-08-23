package io.github.ordinarykai.controller.system.operatelog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wukai
 * @since 2022-08-22
 */
@Data
public class OperateLogListRespVO {

    @ApiModelProperty("操作日志ID")
    private Long operateLogId;

    @ApiModelProperty("管理员ID，外联ADMIN ADMIN_ID")
    private Long adminId;

    @ApiModelProperty("管理员用户名")
    private String username;

    @ApiModelProperty("操作模块")
    private String module;

    @ApiModelProperty("操作名")
    private String name;

    @ApiModelProperty("操作分类")
    private String type;

    @ApiModelProperty("请求方法名")
    private String requestMethod;

    @ApiModelProperty("请求地址")
    private String requestUrl;

    @ApiModelProperty("用户IP")
    private String userIp;

    @ApiModelProperty("浏览器 UserAgent")
    private String userAgent;

    @ApiModelProperty("Java 方法名")
    private String javaMethod;

    @ApiModelProperty("Java 方法的参数")
    private String javaMethodArgs;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("执行时长，单位：毫秒")
    private Integer duration;

    @ApiModelProperty("结果码")
    private Integer resultCode;

    @ApiModelProperty("结果提示")
    private String resultMsg;

    @ApiModelProperty("结果数据")
    private String resultData;

}
