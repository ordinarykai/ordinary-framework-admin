package io.github.ordinarykai.controller.common.file.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kai
 * @date 2022/8/15 21:01
 */
@Data
public class FileRespVO {

    @ApiModelProperty("文件url")
    private String url;

}
