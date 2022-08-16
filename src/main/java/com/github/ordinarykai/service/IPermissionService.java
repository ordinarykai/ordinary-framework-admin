package com.github.ordinarykai.service;

import com.github.ordinarykai.controller.system.permission.vo.PermissionTreeRespVO;
import com.github.ordinarykai.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 根据角色id查询权限树 (不传角色id代表查询全部)
     *
     * @param roleId
     * @return
     */
    List<PermissionTreeRespVO> getTree(Long roleId);

    /**
     * 获取登录用户的权限树
     *
     * @return
     */
    List<PermissionTreeRespVO> getLoginTree();

}
