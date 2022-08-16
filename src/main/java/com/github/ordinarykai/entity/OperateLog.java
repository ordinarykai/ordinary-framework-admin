package com.github.ordinarykai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author 芋道源码
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("operate_log")
@ApiModel(value = "OperateLog对象", description = "操作日志表")
public class OperateLog extends Model<OperateLog> {

    @ApiModelProperty("操作日志ID")
    @TableId(value = "operate_log_id", type = IdType.AUTO)
    private Long operateLogId;

    @ApiModelProperty("管理员ID，外联admin admin_id")
    @TableField(value = "admin_id")
    private Long adminId;

    @ApiModelProperty("操作模块")
    @TableField(value = "module")
    private String module;

    @ApiModelProperty("操作名")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty("操作分类")
    @TableField(value = "type")
    private String type;

    @ApiModelProperty("请求方法名")
    @TableField(value = "request_method")
    private String requestMethod;

    @ApiModelProperty("请求地址")
    @TableField(value = "request_url")
    private String requestUrl;

    @ApiModelProperty("用户IP")
    @TableField(value = "user_ip")
    private String userIp;

    @ApiModelProperty("浏览器 UserAgent")
    @TableField(value = "user_agent")
    private String userAgent;

    @ApiModelProperty("Java 方法名")
    @TableField(value = "java_method")
    private String javaMethod;

    @ApiModelProperty("Java 方法的参数")
    @TableField(value = "java_method_args")
    private String javaMethodArgs;

    @ApiModelProperty("开始时间")
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    @ApiModelProperty("执行时长，单位：毫秒")
    @TableField(value = "duration")
    private Integer duration;

    @ApiModelProperty("结果码")
    @TableField(value = "result_code")
    private Integer resultCode;

    @ApiModelProperty("结果提示")
    @TableField(value = "result_msg")
    private String resultMsg;

    @ApiModelProperty("结果数据")
    @TableField(value = "result_data")
    private String resultData;

    @Override
    public Serializable pkVal() {
        return this.operateLogId;
    }

}
