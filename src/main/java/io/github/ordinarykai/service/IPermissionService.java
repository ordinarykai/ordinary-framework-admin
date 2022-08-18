package io.github.ordinarykai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ordinarykai.controller.system.permission.vo.*;
import io.github.ordinarykai.entity.Permission;

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

    /**
     * 创建菜单
     *
     * @param reqVO
     */
    void create(PermissionCreateReqVO reqVO);

    /**
     * 修改菜单
     *
     * @param reqVO
     */
    void update(PermissionUpdateReqVO reqVO);

    /**
     * 删除菜单
     *
     * @param permissionId
     */
    void delete(Long permissionId);

    /**
     * 获取菜单列表
     *
     * @param reqVO
     * @return
     */
    List<PermissionListRespVO> list(PermissionListReqVO reqVO);

    /**
     * 获取菜单信息
     *
     * @param permissionId
     * @return
     */
    PermissionListRespVO get(Long permissionId);
}
