package io.github.ordinarykai.controller.system.auth;

import io.github.ordinarykai.controller.system.auth.vo.AdminAuthReqVO;
import io.github.ordinarykai.controller.system.auth.vo.AdminAuthRespVO;
import io.github.ordinarykai.controller.system.auth.vo.AdminUpdatePwdReqVO;
import io.github.ordinarykai.service.AdminService;
import io.github.ordinarykai.framework.auth.core.AuthUtil;
import io.github.ordinarykai.framework.auth.core.PreAuthorize;
import io.github.ordinarykai.framework.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author kai
 * @date 2022/8/15 20:52
 */
@Api(tags = "管理员认证")
@RestController
@RequestMapping("/api/system/auth")
public class AuthController {

    @Resource
    private AdminService adminService;

    @PostMapping("login")
    @ApiOperation(value = "管理员登录", notes = "管理员登录")
    public Result<AdminAuthRespVO> auth(@RequestBody @Valid AdminAuthReqVO reqVO, HttpServletRequest req) {
        AdminAuthRespVO respVO = this.adminService.auth(reqVO, req);
        return Result.success(respVO);
    }

    @PostMapping("out")
    @ApiOperation(value = "管理员登出", notes = "管理员登出")
    public Result<Void> out() {
        AuthUtil.out();
        return Result.success();
    }

    @PostMapping("update-pwd")
    @PreAuthorize("/api/system/auth/update-pwd")
    @ApiOperation(value = "修改当前用户密码", notes = "修改当前用户密码")
    public Result<Void> updatePwd(@RequestBody @Valid AdminUpdatePwdReqVO dto) {
        this.adminService.updatePwd(dto);
        return Result.success();
    }

}
