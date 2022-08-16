package com.github.ordinarykai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ordinarykai.controller.system.permission.vo.PermissionTreeRespVO;
import com.github.ordinarykai.entity.Admin;
import com.github.ordinarykai.entity.Permission;
import com.github.ordinarykai.entity.Role;
import com.github.ordinarykai.framework.auth.core.AuthUtil;
import com.github.ordinarykai.mapper.PermissionMapper;
import com.github.ordinarykai.service.IAdminService;
import com.github.ordinarykai.service.IPermissionService;
import com.github.ordinarykai.service.IRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private IRoleService roleService;
    @Resource
    private IAdminService adminService;

    @Override
    public List<PermissionTreeRespVO> getTree(Long roleId) {
        if (roleId == null) {
            return getTree(new ArrayList<>(), true);
        }
        Role role = roleService.getById(roleId);
        if (Objects.isNull(role)) {
            return getTree(new ArrayList<>(), true);
        }
        List<Long> permissionIds = Arrays.stream(role.getPermissionIds().split(","))
                .filter(StringUtils::isNotBlank).map(Long::parseLong).collect(Collectors.toList());
        if (permissionIds.isEmpty()) {
            return getTree(new ArrayList<>(), true);
        }
        return getTree(permissionIds, true);
    }

    @Override
    public List<PermissionTreeRespVO> getLoginTree() {
        Long adminId = AuthUtil.get(true).getId();
        Admin admin = adminService.getById(adminId);
        if (Objects.isNull(admin) || Objects.isNull(admin.getRoleId())) {
            return Collections.emptyList();
        }
        Role role = roleService.getById(admin.getRoleId());
        if (Objects.isNull(role)) {
            return Collections.emptyList();
        }
        List<Long> permissionIds = Arrays.stream(role.getPermissionIds().split(","))
                .filter(StringUtils::isNotBlank).map(Long::parseLong).collect(Collectors.toList());
        if (permissionIds.isEmpty()) {
            return Collections.emptyList();
        }
        return getTree(permissionIds, false);
    }

    private List<PermissionTreeRespVO> getTree(Collection<Long> ids, boolean findAll) {
        List<Permission> permissionList;
        if (CollectionUtils.isNotEmpty(ids) && !findAll) {
            permissionList = this.list(this.lambdaQuery()
                    .in(Permission::getPermissionId, ids)
                    .orderByDesc(Permission::getNum));
        } else {
            permissionList = this.list(this.lambdaQuery()
                    .orderByDesc(Permission::getNum));
        }
        List<Permission> parentPermissionList = permissionList
                .stream()
                .filter(permission -> permission.getParentId() == null)
                .collect(Collectors.toList());
        permissionList.removeAll(parentPermissionList);
        return createPermissionTree(parentPermissionList, permissionList, ids);
    }

    private List<PermissionTreeRespVO> createPermissionTree(List<Permission> parentPermissionList,
                                                            List<Permission> permissionList,
                                                            Collection<Long> ids) {
        return parentPermissionList.stream().map(permission -> {
            Long permissionId = permission.getPermissionId();
            List<Permission> childPermissionList = permissionList
                    .stream()
                    .filter(childPermission -> childPermission.getParentId().equals(permissionId))
                    .collect(Collectors.toList());
            permissionList.removeAll(childPermissionList);
            PermissionTreeRespVO respVO = new PermissionTreeRespVO();
            BeanUtils.copyProperties(permission, respVO);
            respVO.setPermissionId(permissionId);
            if (ids.contains(permissionId)) {
                respVO.setCheckArr(1);
            }
            List<PermissionTreeRespVO> children = createPermissionTree(childPermissionList, permissionList, ids);
            respVO.setChildren(children);
            return respVO;
        }).collect(Collectors.toList());
    }


}
