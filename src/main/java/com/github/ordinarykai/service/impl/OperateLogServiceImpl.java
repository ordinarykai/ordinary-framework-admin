package com.github.ordinarykai.service.impl;

import com.github.ordinarykai.entity.OperateLog;
import com.github.ordinarykai.mapper.OperateLogMapper;
import com.github.ordinarykai.service.IOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
