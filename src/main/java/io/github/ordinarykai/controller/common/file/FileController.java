package io.github.ordinarykai.controller.common.file;

import io.github.ordinarykai.controller.common.file.vo.FileRespVO;
import io.github.ordinarykai.framework.common.result.Result;
import io.github.ordinarykai.framework.file.core.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author kai
 * @date 2022/8/15 20:52
 */
@Api(tags = "文件上传")
@RestController
@RequestMapping("/api/common/file")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping(value = "upload")
    @ApiOperation(value = "上传文件", notes = "上传文件")
    public Result<FileRespVO> upload(@RequestPart(value = "file") MultipartFile file) {
        FileRespVO respVO = new FileRespVO();
        respVO.setUrl(fileService.upload(file));
        return Result.success(respVO);
    }

}
