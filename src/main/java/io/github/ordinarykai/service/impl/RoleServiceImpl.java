package io.github.ordinarykai.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ordinarykai.entity.Role;
import io.github.ordinarykai.framework.common.exception.ApiException;
import io.github.ordinarykai.mapper.RoleMapper;
import io.github.ordinarykai.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static io.github.ordinarykai.constant.CommonConstant.DISABLE;
import static io.github.ordinarykai.constant.CommonConstant.ENABLE;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> querySelect() {
        // TODO: 2022/8/16
        return null;
    }

    @Override
    public void add(Role role) {
        Long count = this.lambdaQuery()
                .eq(Role::getName, role.getName())
                .count();
        if (count > 0) {
            throw new RuntimeException("角色名称已存在，请重新输入");
        }
        role.setStatus(ENABLE);
        role.insert();
    }

    @Override
    public void update(Role role) {
        long count = this.count(this.lambdaQuery()
                .eq(Role::getName, role.getName())
                .ne(Role::getRoleId, role.getRoleId()));
        if (count > 0) {
            throw new RuntimeException("角色名称已存在，请重新输入");
        }
        role.updateById();
    }

    @Override
    public void delete(Long roleId) {
        this.removeById(roleId);
    }

    @Override
    public Page<Role> page(Integer page, Integer rows, String name) {
        return this.lambdaQuery()
                .like(StringUtils.isNotBlank(name), Role::getName, name)
                .orderByDesc(Role::getCreateTime)
                .page(new Page<>(page, rows));
    }

    @Override
    public void updateStatus(Long roleId) {
        Role role = this.getById(roleId);
        if (Objects.isNull(role)) {
            throw new ApiException("系统错误，角色信息不存在");
        }
        role.setStatus(role.getStatus().equals(ENABLE) ? DISABLE : ENABLE);
        role.updateById();
        // TODO: 2022/4/8 角色禁用后对应用户要踢出
    }

}
