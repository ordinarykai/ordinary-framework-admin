package com.github.ordinarykai.controller.system.auth.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author wukai
 * @date 2022/8/16 9:29
 */
@Data
public class AdminAuthReqVO {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(max = 20, message = "用户名或密码错误")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Size(max = 20, message = "用户名或密码错误")
    private String password;

    @ApiModelProperty("验证码")
    @NotBlank(message = "验证码不能为空")
    @Pattern(regexp = "[0-9a-zA-Z]{4}", message = "验证码错误")
    private String code;

    @ApiModelProperty("验证码uuid")
    @NotBlank(message = "验证码uuid不能为空")
    private String uuid;

}
