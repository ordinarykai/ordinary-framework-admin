package io.github.ordinarykai.controller.system.permission.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wukai
 * @date 2022/8/16 14:16
 */
@Data
public class PermissionBaseVO {

    @ApiModelProperty("父级权限ID (顶级权限的parent_id=0)")
    @NotNull(message = "父级权限ID不能为空")
    private Long parentId;

    @ApiModelProperty("类型 (1.菜单 2.接口)")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @ApiModelProperty("权限名称")
    @NotEmpty(message = "权限名称类型不能为空")
    @Size(max = 20, message = "权限名称长度不能超过20个字符")
    private String name;

    @ApiModelProperty("权限标识 (菜单权限是前端路由，接口权限是uri)")
    @NotEmpty(message = "权限标识不能为空")
    @Size(max = 100, message = "权限标识长度不能超过100个字符")
    private String value;

    @ApiModelProperty("序号 (按降序排列)")
    @NotNull(message = "序号不能为空")
    private Integer num;

}
