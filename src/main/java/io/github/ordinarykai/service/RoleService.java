package io.github.ordinarykai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ordinarykai.controller.system.role.vo.RoleCreateReqVO;
import io.github.ordinarykai.controller.system.role.vo.RoleListRespVO;
import io.github.ordinarykai.controller.system.role.vo.RoleUpdateReqVO;
import io.github.ordinarykai.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
public interface RoleService extends IService<Role> {

    List<RoleListRespVO> select();

    void create(RoleCreateReqVO reqVO);

    void update(RoleUpdateReqVO reqVO);

    void delete(Long roleId);

    Page<Role> page(Integer page, Integer rows, String name);

    void updateStatus(Long roleId);

}
