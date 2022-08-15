package com.github.ordinarykai.service.impl;

import com.github.ordinarykai.entity.Role;
import com.github.ordinarykai.mapper.RoleMapper;
import com.github.ordinarykai.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
