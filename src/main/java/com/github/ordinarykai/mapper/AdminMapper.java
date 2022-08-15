package com.github.ordinarykai.mapper;

import com.github.ordinarykai.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
