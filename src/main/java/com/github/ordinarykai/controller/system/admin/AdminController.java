package com.github.ordinarykai.controller.system.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.ordinarykai.controller.system.admin.vo.*;
import com.github.ordinarykai.framework.common.result.Result;
import com.github.ordinarykai.service.IAdminService;
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
    private IAdminService adminService;

    @GetMapping("page")
    @ApiOperation(value = "管理员分页列表", notes = "管理员分页列表")
    public Result<IPage<AdminVO>> page(
            @RequestParam("current") Integer current,
            @RequestParam("size") Integer size,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "status", required = false) Integer status
    ) {
        IPage<AdminVO> page = this.adminService.page(current, size, username, status);
        return Result.success(page);
    }

    @PostMapping("add")
    @ApiOperation(value = "新增管理员", notes = "新增管理员（密码默认123456）")
    public Result<Void> add(@RequestBody @Valid AdminAddDTO dto) {
        this.adminService.add(dto);
        return Result.success();
    }

    @PostMapping("update")
    @ApiOperation(value = "编辑管理员", notes = "编辑管理员")
    public Result<Void> update(@RequestBody @Valid AdminUpdateDTO dto) {
        this.adminService.update(dto);
        return Result.success();
    }

    @GetMapping("query")
    @ApiOperation(value = "查看管理员详情", notes = "查看管理员详情")
    public Result<AdminVO> query(
            @RequestParam("adminId") Integer adminId
    ) {
        AdminVO adminVO = this.adminService.query(adminId);
        return Result.success(adminVO);
    }

    @DeleteMapping("delete")
    @ApiOperation(value = "删除管理员", notes = "删除管理员（删除后会强制登出已登录的该账号）")
    public Result<Void> delete(
            @RequestParam("adminId") Integer adminId
    ) {
        this.adminService.delete(adminId);
        return Result.success();
    }

    @PostMapping("updatePwd")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public Result<Void> updatePwd(@RequestBody @Valid AdminUpdatePwdDTO dto) {
        this.adminService.updatePwd(dto);
        return Result.success();
    }

    @PostMapping("updateStatus")
    @ApiOperation(value = "启用/禁用管理员", notes = "启用/禁用管理员")
    public Result<Void> updatePwd(@RequestBody @Valid AdminUpdateStatusDTO dto) {
        this.adminService.updateStatus(dto.getAdminId());
        return Result.success();
    }

}

