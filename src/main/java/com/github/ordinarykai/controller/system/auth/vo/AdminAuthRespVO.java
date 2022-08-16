package com.github.ordinarykai.controller.system.auth.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kai
 * @date 2022/8/15 21:08
 */
@Data
public class AdminAuthRespVO {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("token令牌（访问接口时需要将token放到header中）")
    private String token;

}
