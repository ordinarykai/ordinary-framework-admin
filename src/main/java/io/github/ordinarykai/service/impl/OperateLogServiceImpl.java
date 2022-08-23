package io.github.ordinarykai.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.ordinarykai.controller.system.operatelog.vo.OperateLogListRespVO;
import io.github.ordinarykai.entity.Admin;
import io.github.ordinarykai.entity.OperateLog;
import io.github.ordinarykai.mapper.OperateLogMapper;
import io.github.ordinarykai.service.AdminService;
import io.github.ordinarykai.service.OperateLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author wukai
 * @since 2022-08-16
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements OperateLogService {

    @Resource
    private AdminService adminService;

    @Override
    public IPage<OperateLogListRespVO> page(Integer current, Integer size, String module, String name) {
        Page<OperateLog> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(module), OperateLog::getModule, module)
                .like(StringUtils.isNotBlank(name), OperateLog::getName, name)
                .orderByDesc(OperateLog::getStartTime)
                .page(new Page<>(current, size));
        return page.convert(operateLog -> {
            OperateLogListRespVO respVO = new OperateLogListRespVO();
            BeanUtils.copyProperties(operateLog, respVO);
            if (Objects.nonNull(operateLog.getAdminId())) {
                Admin admin = adminService.getById(operateLog.getAdminId());
                if (Objects.nonNull(admin)) {
                    respVO.setUsername(admin.getUsername());
                }
            }
            return respVO;
        });
    }
}
