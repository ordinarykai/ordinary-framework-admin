package com.github.ordinarykai.service.impl;

import com.github.ordinarykai.entity.Admin;
import com.github.ordinarykai.mapper.AdminMapper;
import com.github.ordinarykai.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
