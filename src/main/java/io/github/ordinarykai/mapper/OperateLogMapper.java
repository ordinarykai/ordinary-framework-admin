package io.github.ordinarykai.mapper;

import io.github.ordinarykai.entity.OperateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author wukai
 * @since 2022-08-16
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {

}
