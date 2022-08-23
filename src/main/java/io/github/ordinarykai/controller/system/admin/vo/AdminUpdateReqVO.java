package io.github.ordinarykai.controller.system.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author kai
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminUpdateReqVO extends AdminBaseVO {

    @ApiModelProperty("管理员id")
    @NotNull(message = "管理员id不能为空")
    private Long adminId;

}
