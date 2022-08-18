package io.github.ordinarykai.mapper;

import io.github.ordinarykai.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
