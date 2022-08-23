package io.github.ordinarykai.controller.system.role.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author wukai
 * @date 2022/8/23 9:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleListRespVO extends RoleBaseVO {

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("状态 (0.禁用 1.启用)")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}
