package com.github.ordinarykai.controller.system.permission;

import com.github.ordinarykai.controller.system.permission.vo.*;
import com.github.ordinarykai.framework.auth.core.PreAuthorize;
import com.github.ordinarykai.framework.common.result.Result;
import com.github.ordinarykai.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Comparator;
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

    @GetMapping(value = "get-tree")
    @PreAuthorize("/api/system/permission/get-tree")
    @ApiOperation(value = "根据角色id查询权限树", notes = "根据角色id查询权限树 (不传角色id代表查询全部)")
    public Result<List<PermissionTreeRespVO>> getTree(
            @RequestParam(value = "roleId", required = false) Long roleId
    ) {
        List<PermissionTreeRespVO> respVOList = permissionService.getTree(roleId);
        return Result.success(respVOList);
    }

    @GetMapping(value = "get-login-tree")
    @PreAuthorize("/api/system/permission/get-login-tree")
    @ApiOperation(value = "获取登录用户的权限树", notes = "获取登录用户的权限树")
    public Result<List<PermissionTreeRespVO>> getLoginTree() {
        List<PermissionTreeRespVO> respVOList = permissionService.getLoginTree();
        return Result.success(respVOList);
    }

    @PostMapping("/create")
    @PreAuthorize("/api/system/permission/create")
    @ApiOperation(value = "创建菜单", notes = "创建菜单")
    public Result<Void> create(@RequestBody @Valid PermissionCreateReqVO reqVO) {
        permissionService.create(reqVO);
        return Result.success();
    }

    @PostMapping("/update")
    @PreAuthorize("/api/system/permission/update")
    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    public Result<Void> update(@RequestBody @Valid PermissionUpdateReqVO reqVO) {
        permissionService.update(reqVO);
        return Result.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("/api/system/permission/delete")
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    public Result<Void> delete(
            @RequestParam("permissionId") Long permissionId
    ) {
        permissionService.delete(permissionId);
        return Result.success();
    }

    @GetMapping("/list")
    @PreAuthorize("/api/system/permission/list")
    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表")
    public Result<List<PermissionListRespVO>> getMenus(PermissionListReqVO reqVO) {
        List<PermissionListRespVO> list = permissionService.list(reqVO);
        return Result.success(list);
    }

    @GetMapping("/get")
    @PreAuthorize("/api/system/permission/get")
    @ApiOperation(value = "获取菜单信息", notes = "获取菜单信息")
    public Result<PermissionListRespVO> get(
            @RequestParam("permissionId") Long permissionId
    ) {
        PermissionListRespVO respVO = permissionService.get(permissionId);
        return Result.success(respVO);
    }

}
