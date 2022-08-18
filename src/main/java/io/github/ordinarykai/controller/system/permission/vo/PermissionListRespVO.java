package io.github.ordinarykai.controller.system.permission.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author wukai
 * @date 2022/8/16 14:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionListRespVO extends PermissionBaseVO {

    @ApiModelProperty("权限ID")
    private Long permissionId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

}
