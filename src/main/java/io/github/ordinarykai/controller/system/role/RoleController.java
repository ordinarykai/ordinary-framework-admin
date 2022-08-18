package io.github.ordinarykai.controller.system.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.ordinarykai.entity.Admin;
import io.github.ordinarykai.entity.Role;
import io.github.ordinarykai.framework.auth.core.PreAuthorize;
import io.github.ordinarykai.framework.common.result.Result;
import io.github.ordinarykai.service.IAdminService;
import io.github.ordinarykai.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/api/system/role")
public class RoleController {

    @Resource
    private IRoleService roleService;
    @Resource
    private IAdminService adminService;

    @GetMapping(value = "query-select")
    @PreAuthorize("/api/system/role/query-select")
    @ApiOperation(value = "查询角色下拉框", notes = "查询角色下拉框")
    public Result<List<Role>> querySelect() {
        List<Role> roleList = roleService.querySelect();
        return Result.success(roleList);
    }

    @PostMapping("add")
    @PreAuthorize("/api/system/role/add")
    @ApiOperation(value = "添加角色", notes = "添加角色 (传name、permissionIds)")
    public Result<Void> add(@RequestBody @Valid Role role) {
        roleService.add(role);
        return Result.success();
    }

    @PostMapping("update")
    @PreAuthorize("/api/system/role/update")
    @ApiOperation(value = "编辑角色", notes = "编辑角色")
    public Result<Void> update(@RequestBody @Valid Role role) {
        roleService.update(role);
        return Result.success();
    }

    @PostMapping("delete")
    @PreAuthorize("/api/system/role/delete")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    public Result<Void> delete(
            @RequestParam("roleId") Long roleId
    ) {
        long count = adminService.count(adminService.lambdaQuery().eq(Admin::getRoleId, roleId));
        if (count > 0) {
            throw new RuntimeException("角色已关联用户，不能删除");
        }
        roleService.delete(roleId);
        return Result.success();
    }

    @GetMapping(value = "page")
    @PreAuthorize("/api/system/role/page")
    @ApiOperation(value = "查询角色分页列表", notes = "查询角色分页列表")
    public Result<Page<Role>> page(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer rows,
            @RequestParam(value = "name", required = false) String name
    ) {
        return Result.success(roleService.page(page, rows, name));
    }

    @PostMapping("update-status")
    @PreAuthorize("/api/system/role/update-status")
    @ApiOperation(value = "启用/禁用角色", notes = "启用/禁用角色")
    public Result<Void> updateStatus(
            @RequestParam("roleId") Long roleId
    ) {
        roleService.updateStatus(roleId);
        return Result.success();
    }

}
