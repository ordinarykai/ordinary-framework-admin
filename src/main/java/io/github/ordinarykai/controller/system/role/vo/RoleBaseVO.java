package io.github.ordinarykai.controller.system.role.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author wukai
 * @date 2022/8/23 9:52
 */
@Data
public class RoleBaseVO {

    @ApiModelProperty("角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Size(max = 20, message = "角色名称长度不能超过20个字符")
    private String name;

    @ApiModelProperty("权限ID集合，外联permission permission_id")
    private String permissionIds;

}
