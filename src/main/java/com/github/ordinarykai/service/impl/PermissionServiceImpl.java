package com.github.ordinarykai.service.impl;

import com.github.ordinarykai.entity.Permission;
import com.github.ordinarykai.mapper.PermissionMapper;
import com.github.ordinarykai.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
