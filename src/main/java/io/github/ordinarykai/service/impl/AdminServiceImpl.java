package io.github.ordinarykai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ordinarykai.controller.system.admin.vo.AdminAddDTO;
import io.github.ordinarykai.controller.system.admin.vo.AdminUpdateDTO;
import io.github.ordinarykai.controller.system.admin.vo.AdminUpdatePwdDTO;
import io.github.ordinarykai.controller.system.admin.vo.AdminVO;
import io.github.ordinarykai.controller.system.auth.vo.AdminAuthReqVO;
import io.github.ordinarykai.controller.system.auth.vo.AdminAuthRespVO;
import io.github.ordinarykai.entity.Admin;
import io.github.ordinarykai.entity.Permission;
import io.github.ordinarykai.entity.Role;
import io.github.ordinarykai.framework.auth.core.AuthInfo;
import io.github.ordinarykai.framework.auth.core.AuthUtil;
import io.github.ordinarykai.framework.common.exception.ApiException;
import io.github.ordinarykai.framework.redis.core.RedisService;
import io.github.ordinarykai.mapper.AdminMapper;
import io.github.ordinarykai.service.IAdminService;
import io.github.ordinarykai.service.IPermissionService;
import io.github.ordinarykai.service.IRoleService;
import io.github.ordinarykai.util.MyStringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.github.ordinarykai.constant.CommonConstant.*;
import static io.github.ordinarykai.constant.RedisConstant.REDIS_VERIFY_CODE;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private RedisService redisService;
    @Resource
    private IRoleService roleService;
    @Resource
    private IPermissionService permissionService;

    @Override
    public AdminAuthRespVO auth(AdminAuthReqVO reqVO, HttpServletRequest req) {

        String code = redisService.get(REDIS_VERIFY_CODE + reqVO.getUuid());
        if (!reqVO.getCode().equalsIgnoreCase(code)) {
            throw new ApiException("验证码错误或已过期");
        }

        Admin admin = this.getOne(Wrappers.lambdaQuery(Admin.class)
                .eq(Admin::getUsername, reqVO.getUsername()));
        if (Objects.isNull(admin)) {
            throw new ApiException("用户名或密码错误");
        }
        String password = MyStringUtil.twiceMd5Encode(reqVO.getPassword());
        if (!admin.getPassword().equals(password)) {
            throw new ApiException("用户名或密码错误");
        }
        if (DISABLE.equals(admin.getStatus())) {
            throw new ApiException("该用户已被禁用");
        }

        redisService.del(REDIS_VERIFY_CODE + reqVO.getUuid());

        AuthInfo loginInfo = new AuthInfo();
        loginInfo.setId(admin.getAdminId());
        loginInfo.setAccount(admin.getUsername());
        // 缓存用户权限
        if (Objects.nonNull(admin.getRoleId())) {
            loginInfo.setRoleIds(Collections.singletonList(admin.getRoleId()));
            Role role = roleService.getOne(roleService.lambdaQuery()
                    .eq(Role::getRoleId, admin.getRoleId())
                    .last("limit 1"));
            if (StringUtils.isNotBlank(role.getPermissionIds())) {
                List<String> permissionIds = Arrays.asList(role.getPermissionIds().split(","));
                List<String> permissionValues = permissionService.listByIds(permissionIds)
                        .stream()
                        .map(Permission::getValue)
                        .collect(Collectors.toList());
                loginInfo.setPermissions(permissionValues);
            }
        }
        String token = AuthUtil.set(loginInfo);
        // 更新用户当前token
        admin.setToken(token);
        admin.updateById();

        AdminAuthRespVO respVO = new AdminAuthRespVO();
        respVO.setUsername(admin.getUsername());
        respVO.setNickname(admin.getNickname());
        respVO.setToken(token);
        return respVO;
    }

    @Override
    public IPage<AdminVO> page(Integer current, Integer size, String username, Integer status) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<Admin>()
                .like(StringUtils.isNotBlank(username), Admin::getUsername, username)
                .eq(Objects.nonNull(status), Admin::getStatus, status);
        Page<Admin> page = this.page(new Page<>(current, size), queryWrapper);
        return page.convert(admin -> {
            AdminVO adminVO = new AdminVO();
            BeanUtils.copyProperties(admin, adminVO);
            return adminVO;
        });
    }

    @Override
    public void add(AdminAddDTO adminAddDTO) {
        String username = adminAddDTO.getUsername();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, username);
        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new ApiException("用户名已存在，请重新输入");
        }
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setNickname(adminAddDTO.getNickname());
        admin.setPassword(MyStringUtil.twiceMd5Encode(DEFAULT_PWD));
        admin.insert();
    }

    @Override
    public void update(AdminUpdateDTO adminUpdateDTO) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, adminUpdateDTO.getUsername())
                .notIn(Admin::getAdminId, adminUpdateDTO.getAdminId());
        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new ApiException("用户名已存在，请重新输入");
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminUpdateDTO, admin);
        admin.updateById();
    }

    @Override
    public AdminVO query(Integer adminId) {
        Admin admin = this.getById(adminId);
        if (Objects.isNull(admin)) {
            throw new ApiException("系统异常，该管理员不存在");
        }
        AdminVO adminVO = new AdminVO();
        BeanUtils.copyProperties(admin, adminVO);
        return adminVO;
    }

    @Override
    public void updatePwd(AdminUpdatePwdDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new ApiException("两次新密码输入不一致，请重新输入");
        }
        AuthInfo authInfo = AuthUtil.get(true);
        Admin admin = this.getById(authInfo.getId());
        if (Objects.isNull(admin)) {
            throw new ApiException("系统异常，当前登录用户信息不存在");
        }
        String oldPassword = MyStringUtil.twiceMd5Encode(dto.getOldPassword());
        if (!oldPassword.equals(admin.getPassword())) {
            throw new ApiException("原密码错误，请重新输入");
        }
        admin.setPassword(MyStringUtil.twiceMd5Encode(dto.getPassword()));
        admin.updateById();
    }

    @Override
    public void delete(Integer adminId) {
        Admin admin = this.getById(adminId);
        if (Objects.isNull(admin)) {
            return;
        }
        boolean b = admin.deleteById();
        if (b) {
            // 踢出已登录用户
            AuthUtil.out(admin.getToken());
        }
    }

    @Override
    public void updateStatus(Integer adminId) {
        Admin admin = this.getById(adminId);
        if (Objects.isNull(admin)) {
            throw new ApiException("系统异常，该管理员不存在");
        }
        if (admin.getStatus().equals(ENABLE)) {
            admin.setStatus(DISABLE);
            // 踢出已登录用户
            AuthUtil.out(admin.getToken());
        } else {
            admin.setStatus(ENABLE);
        }
        admin.updateById();
    }

}
