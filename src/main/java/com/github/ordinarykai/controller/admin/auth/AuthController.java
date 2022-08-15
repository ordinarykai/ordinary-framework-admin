package com.github.ordinarykai.controller.admin.auth;

import com.github.ordinarykai.framework.auth.core.AuthUtil;
import com.github.ordinarykai.framework.common.result.Result;
import com.github.ordinarykai.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author kai
 * @date 2022/8/15 20:52
 */
@Api(tags = "管理员认证")
@RestController
@RequestMapping("/api/admin/auth")
public class AuthController {

    @Resource
    private IAdminService adminService;

    @PostMapping("out")
    @ApiOperation(value = "管理员登出", notes = "管理员登出")
    public Result<Void> out() {
        AuthUtil.out();
        return Result.success();
    }

}
