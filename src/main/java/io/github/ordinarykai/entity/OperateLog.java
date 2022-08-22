package io.github.ordinarykai.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author wukai
 * @since 2022-08-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("OPERATE_LOG")
@KeySequence("SEQ_OPERATE_LOG")
@ApiModel(value = "OperateLog对象", description = "操作日志表")
public class OperateLog extends Model<OperateLog> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("操作日志ID")
    @TableId(value = "OPERATE_LOG_ID", type = IdType.INPUT)
    private Long operateLogId;

    @ApiModelProperty("管理员ID，外联ADMIN ADMIN_ID")
    @TableField("ADMIN_ID")
    private Long adminId;

    @ApiModelProperty("操作模块")
    @TableField("MODULE")
    private String module;

    @ApiModelProperty("操作名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty("操作分类")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty("请求方法名")
    @TableField("REQUEST_METHOD")
    private String requestMethod;

    @ApiModelProperty("请求地址")
    @TableField("REQUEST_URL")
    private String requestUrl;

    @ApiModelProperty("用户IP")
    @TableField("USER_IP")
    private String userIp;

    @ApiModelProperty("浏览器 UserAgent")
    @TableField("USER_AGENT")
    private String userAgent;

    @ApiModelProperty("Java 方法名")
    @TableField("JAVA_METHOD")
    private String javaMethod;

    @ApiModelProperty("Java 方法的参数")
    @TableField("JAVA_METHOD_ARGS")
    private String javaMethodArgs;

    @ApiModelProperty("开始时间")
    @TableField("START_TIME")
    private LocalDateTime startTime;

    @ApiModelProperty("执行时长，单位：毫秒")
    @TableField("DURATION")
    private Integer duration;

    @ApiModelProperty("结果码")
    @TableField("RESULT_CODE")
    private Integer resultCode;

    @ApiModelProperty("结果提示")
    @TableField("RESULT_MSG")
    private String resultMsg;

    @ApiModelProperty("结果数据")
    @TableField("RESULT_DATA")
    private String resultData;

    @Override
    public Serializable pkVal() {
        return this.operateLogId;
    }
}
