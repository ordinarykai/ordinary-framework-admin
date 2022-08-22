package io.github.ordinarykai.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.ordinarykai.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<Role> querySelect();

    void add(Role role);

    void update(Role role);

    void delete(Long roleId);

    Page<Role> page(Integer page, Integer rows, String name);

    void updateStatus(Long roleId);

}
