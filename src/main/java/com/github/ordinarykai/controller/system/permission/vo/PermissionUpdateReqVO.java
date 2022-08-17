package com.github.ordinarykai.controller.system.permission.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author wukai
 * @date 2022/8/16 14:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionUpdateReqVO extends PermissionBaseVO {

    @ApiModelProperty("权限ID")
    @NotNull(message = "权限ID不能为空")
    private Long permissionId;

}
