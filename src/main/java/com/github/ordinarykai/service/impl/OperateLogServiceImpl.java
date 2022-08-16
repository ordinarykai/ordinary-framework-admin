package com.github.ordinarykai.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ordinarykai.entity.OperateLog;
import com.github.ordinarykai.mapper.OperateLogMapper;
import com.github.ordinarykai.service.IOperateLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author wukai
 * @since 2022-08-16
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements IOperateLogService {

    @Override
    public IPage<OperateLog> page(Integer current, Integer size, String module, String name) {
        LambdaQueryChainWrapper<OperateLog> queryWrapper = this.lambdaQuery()
                .like(StringUtils.isNotBlank(module), OperateLog::getModule, module)
                .like(StringUtils.isNotBlank(name), OperateLog::getName, name)
                .orderByDesc(OperateLog::getStartTime);
        return this.page(queryWrapper.page(new Page<>(current, size)));
    }
}
