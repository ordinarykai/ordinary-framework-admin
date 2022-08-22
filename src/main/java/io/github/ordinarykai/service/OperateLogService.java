package io.github.ordinarykai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ordinarykai.entity.OperateLog;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author wukai
 * @since 2022-08-16
 */
public interface OperateLogService extends IService<OperateLog> {

    /**
     * 操作日志分页列表
     * @param current
     * @param size
     * @param module
     * @param name
     * @return
     */
    IPage<OperateLog> page(Integer current, Integer size, String module, String name);
}
