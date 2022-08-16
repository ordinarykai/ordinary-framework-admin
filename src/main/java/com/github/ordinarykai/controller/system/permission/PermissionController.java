package com.github.ordinarykai.controller.system.permission;

import com.github.ordinarykai.controller.system.permission.vo.PermissionTreeRespVO;
import com.github.ordinarykai.framework.common.result.Result;
import com.github.ordinarykai.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/api/system/permission")
public class PermissionController {

    @Resource
    private IPermissionService permissionService;

    @GetMapping(value = "getTree")
    @ApiOperation(value = "根据角色id查询权限树", notes = "根据角色id查询权限树 (不传角色id代表查询全部)")
    public Result<List<PermissionTreeRespVO>> getTree(
            @RequestParam(value = "roleId", required = false) Long roleId
    ) {
        List<PermissionTreeRespVO> respVOList = permissionService.getTree(roleId);
        return Result.success(respVOList);
    }

    @GetMapping(value = "getLoginTree")
    @ApiOperation(value = "获取登录用户的权限树", notes = "获取登录用户的权限树")
    public Result<List<PermissionTreeRespVO>> getLoginTree() {
        List<PermissionTreeRespVO> respVOList = permissionService.getLoginTree();
        return Result.success(respVOList);
    }

}
