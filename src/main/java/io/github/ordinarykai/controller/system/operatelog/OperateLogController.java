package io.github.ordinarykai.controller.system.operatelog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.ordinarykai.controller.system.operatelog.vo.OperateLogListRespVO;
import io.github.ordinarykai.service.OperateLogService;
import io.github.ordinarykai.framework.auth.core.PreAuthorize;
import io.github.ordinarykai.framework.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author wukai
 * @since 2022-08-16
 */
@Api(tags = "操作日志管理")
@RestController
@RequestMapping("/api/system/operate-log")
public class OperateLogController {

    @Resource
    private OperateLogService operateLogService;

    @GetMapping("page")
    @PreAuthorize("/api/system/operate-log/page")
    @ApiOperation(value = "操作日志分页列表", notes = "操作日志分页列表")
    public Result<IPage<OperateLogListRespVO>> page(
            @RequestParam("current") Integer current,
            @RequestParam("size") Integer size,
            @RequestParam(value = "module", required = false) String module,
            @RequestParam(value = "name", required = false) String name
    ) {
        IPage<OperateLogListRespVO> page = this.operateLogService.page(current, size, module, name);
        return Result.success(page);
    }

}
