package com.github.ordinarykai.controller.system.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kai
 */
@Data
public class AdminVO {

    @ApiModelProperty("管理员id")
    private Integer adminId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("状态 (1.启用 2.禁用)")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
