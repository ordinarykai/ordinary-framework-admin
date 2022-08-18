package io.github.ordinarykai.controller.system.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author kai
 */
@Data
public class AdminUpdatePwdDTO {

    @ApiModelProperty("原密码")
    @NotBlank(message = "原密码不能为空")
    @Size(max = 20, message = "原密码错误")
    private String oldPassword;

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    @Size(max = 20, message = "新密码最长不超过20字符")
    private String password;

    @ApiModelProperty("确认密码")
    @NotBlank(message = "确认密码不能为空")
    @Size(max = 20, message = "确认密码最长不超过20字符")
    private String confirmPassword;

}
