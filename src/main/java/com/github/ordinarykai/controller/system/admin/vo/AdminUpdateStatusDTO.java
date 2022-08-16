package com.github.ordinarykai.controller.system.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author kai
 */
@Data
public class AdminUpdateStatusDTO {

    @ApiModelProperty("管理员id")
    @NotNull(message = "管理员id不能为空")
    private Integer adminId;

}
