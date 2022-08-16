package com.github.ordinarykai.controller.system.permission.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wukai
 * @date 2022/8/16 9:59
 */
@Data
public class PermissionTreeRespVO {

    @ApiModelProperty(value = "权限id")
    private Long permissionId;

    @ApiModelProperty(value = "父级权限id")
    private Long parentId;

    @ApiModelProperty(value = "类型 (1.菜单 2.接口)")
    private Integer type;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限标识 (菜单权限是前端路由，接口权限是uri)")
    private String value;

    @ApiModelProperty("是否选中 (0.未选中 1.选中)")
    private Integer checkArr = 0;

    @ApiModelProperty("下级权限")
    private List<PermissionTreeRespVO> children;

}
