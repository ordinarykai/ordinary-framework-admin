package io.github.ordinarykai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.ordinarykai.controller.system.admin.vo.AdminAddDTO;
import io.github.ordinarykai.controller.system.admin.vo.AdminUpdateDTO;
import io.github.ordinarykai.controller.system.admin.vo.AdminUpdatePwdDTO;
import io.github.ordinarykai.controller.system.admin.vo.AdminVO;
import io.github.ordinarykai.controller.system.auth.vo.AdminAuthReqVO;
import io.github.ordinarykai.controller.system.auth.vo.AdminAuthRespVO;
import io.github.ordinarykai.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 管理员登录
     *
     * @param reqVO
     * @param req
     * @return
     */
    AdminAuthRespVO auth(AdminAuthReqVO reqVO, HttpServletRequest req);

    /**
     * 根据用户名和启用状态查询管理员分页列表
     *
     * @param current
     * @param size
     * @param username
     * @param status
     * @return 管理员分页列表
     */
    IPage<AdminVO> page(Integer current, Integer size, String username, Integer status);

    /**
     * 新增管理员
     *
     * @param adminAddDTO
     */
    void add(AdminAddDTO adminAddDTO);

    /**
     * 编辑管理员
     *
     * @param adminUpdateDTO
     */
    void update(AdminUpdateDTO adminUpdateDTO);

    /**
     * 查看管理员详情
     *
     * @param adminId
     * @return
     */
    AdminVO query(Integer adminId);

    /**
     * 修改密码
     *
     * @param dto
     */
    void updatePwd(AdminUpdatePwdDTO dto);

    /**
     * 删除管理员
     *
     * @param adminId
     */
    void delete(Integer adminId);

    /**
     * 启用/禁用管理员
     *
     * @param adminId
     */
    void updateStatus(Integer adminId);

}
