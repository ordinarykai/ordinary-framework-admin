package io.github.ordinarykai.controller.system.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author kai
 */
@Data
public class AdminUpdateDTO {

    @ApiModelProperty("管理员id")
    @NotNull(message = "管理员id不能为空")
    private Integer adminId;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(max = 20, message = "用户名最长不超过20字符")
    private String username;

    @ApiModelProperty("昵称")
    @NotBlank(message = "昵称不能为空")
    @Size(max = 20, message = "昵称最长不超过20字符")
    private String nickname;


}
