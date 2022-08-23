package io.github.ordinarykai.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ordinarykai.controller.system.role.vo.RoleCreateReqVO;
import io.github.ordinarykai.controller.system.role.vo.RoleListRespVO;
import io.github.ordinarykai.controller.system.role.vo.RoleUpdateReqVO;
import io.github.ordinarykai.entity.Role;
import io.github.ordinarykai.mapper.RoleMapper;
import io.github.ordinarykai.service.RoleService;
import io.github.ordinarykai.framework.common.exception.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<RoleListRespVO> select() {
        List<Role> roleList = this.lambdaQuery()
                .eq(Role::getStatus, ENABLE)
                .orderByDesc(Role::getCreateTime)
                .list();
        return roleList.stream().map(role -> {
            RoleListRespVO respVO = new RoleListRespVO();
            BeanUtils.copyProperties(role, respVO);
            return respVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void create(RoleCreateReqVO reqVO) {
        Long count = this.lambdaQuery()
                .eq(Role::getName, reqVO.getName())
                .count();
        if (count > 0) {
            throw new RuntimeException("角色名称已存在，请重新输入");
        }
        Role role = new Role();
        BeanUtils.copyProperties(reqVO, role);
        role.setStatus(ENABLE);
        role.insert();
    }

    @Override
    public void update(RoleUpdateReqVO reqVO) {
        long count = this.count(this.lambdaQuery()
                .eq(Role::getName, reqVO.getName())
                .ne(Role::getRoleId, reqVO.getRoleId()));
        if (count > 0) {
            throw new RuntimeException("角色名称已存在，请重新输入");
        }
        Role role = new Role();
        BeanUtils.copyProperties(reqVO, role);
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
