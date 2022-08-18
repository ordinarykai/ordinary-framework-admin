package io.github.ordinarykai.controller.system.permission.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author wukai
 * @date 2022/8/16 14:16
 */
@Data
public class PermissionListReqVO  {

    @ApiModelProperty("类型 (1.菜单 2.接口)")
    private Integer type;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限标识 (菜单权限是前端路由，接口权限是uri)")
    private String value;
}
