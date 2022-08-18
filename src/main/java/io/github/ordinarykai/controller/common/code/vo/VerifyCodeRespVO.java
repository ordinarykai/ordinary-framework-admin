package io.github.ordinarykai.controller.common.code.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kai
 * @date 2022/8/15 21:01
 */
@Data
public class VerifyCodeRespVO {

    @ApiModelProperty("图形验证码uuid")
    private String uuid;

    @ApiModelProperty("图形验证码base64")
    private String base64;

}
