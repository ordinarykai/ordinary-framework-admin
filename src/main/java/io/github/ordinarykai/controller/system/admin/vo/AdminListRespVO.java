package io.github.ordinarykai.controller.system.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author kai
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminListRespVO extends AdminBaseVO {

    @ApiModelProperty("管理员id")
    private Integer adminId;

    @ApiModelProperty("状态 (1.启用 2.禁用)")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
