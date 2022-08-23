package io.github.ordinarykai.controller.system.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.ordinarykai.controller.system.admin.vo.AdminCreateReqVO;
import io.github.ordinarykai.controller.system.admin.vo.AdminListRespVO;
import io.github.ordinarykai.controller.system.admin.vo.AdminUpdateReqVO;
import io.github.ordinarykai.service.AdminService;
import io.github.ordinarykai.framework.auth.core.PreAuthorize;
import io.github.ordinarykai.framework.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Api(tags = "管理员管理")
@RestController
@RequestMapping("/api/system/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping("page")
    @PreAuthorize("/api/system/admin/page")
    @ApiOperation(value = "管理员分页列表", notes = "管理员分页列表")
    public Result<IPage<AdminListRespVO>> page(
            @RequestParam("current") Integer current,
            @RequestParam("size") Integer size,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "status", required = false) Integer status
    ) {
        IPage<AdminListRespVO> page = this.adminService.page(current, size, username, status);
        return Result.success(page);
    }

    @PostMapping("create")
    @PreAuthorize("/api/system/admin/create")
    @ApiOperation(value = "新增管理员", notes = "新增管理员（密码默认123456）")
    public Result<Void> create(@RequestBody @Valid AdminCreateReqVO reqVO) {
        this.adminService.create(reqVO);
        return Result.success();
    }

    @PostMapping("update")
    @PreAuthorize("/api/system/admin/update")
    @ApiOperation(value = "编辑管理员", notes = "编辑管理员")
    public Result<Void> update(@RequestBody @Valid AdminUpdateReqVO reqVO) {
        this.adminService.update(reqVO);
        return Result.success();
    }

    @GetMapping("get")
    @PreAuthorize("/api/system/admin/get")
    @ApiOperation(value = "查看管理员详情", notes = "查看管理员详情")
    public Result<AdminListRespVO> get(
            @RequestParam("adminId") Integer adminId
    ) {
        AdminListRespVO respVO = this.adminService.get(adminId);
        return Result.success(respVO);
    }

    @DeleteMapping("delete")
    @PreAuthorize("/api/system/admin/delete")
    @ApiOperation(value = "删除管理员", notes = "删除管理员（删除后会强制登出已登录的该账号）")
    public Result<Void> delete(
            @RequestParam("adminId") Integer adminId
    ) {
        this.adminService.delete(adminId);
        return Result.success();
    }

    @PostMapping("reset-pwd")
    @PreAuthorize("/api/system/admin/reset-pwd")
    @ApiOperation(value = "重置密码", notes = "重置密码（默认密码123456）")
    public Result<Void> resetPwd(
            @RequestParam("adminId") Integer adminId
    ) {
        this.adminService.resetPwd(adminId);
        return Result.success();
    }

    @PostMapping("update-status")
    @PreAuthorize("/api/system/admin/update-status")
    @ApiOperation(value = "启用/禁用管理员", notes = "启用/禁用管理员")
    public Result<Void> updatePwd(
            @RequestParam("adminId") Integer adminId
    ) {
        this.adminService.updateStatus(adminId);
        return Result.success();
    }

}

