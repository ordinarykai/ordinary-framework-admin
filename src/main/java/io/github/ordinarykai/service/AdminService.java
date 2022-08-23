package io.github.ordinarykai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.ordinarykai.controller.system.admin.vo.AdminCreateReqVO;
import io.github.ordinarykai.controller.system.admin.vo.AdminListRespVO;
import io.github.ordinarykai.controller.system.admin.vo.AdminUpdateReqVO;
import io.github.ordinarykai.controller.system.auth.vo.AdminAuthReqVO;
import io.github.ordinarykai.controller.system.auth.vo.AdminAuthRespVO;
import io.github.ordinarykai.controller.system.auth.vo.AdminUpdatePwdReqVO;
import io.github.ordinarykai.entity.Admin;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
public interface AdminService extends IService<Admin> {

    /**
     * 管理员登录
     *
     * @param reqVO
     * @param req
     * @return
     */
    AdminAuthRespVO auth(AdminAuthReqVO reqVO, HttpServletRequest req);

    /**
     * 修改当前用户密码
     *
     * @param dto
     */
    void updatePwd(AdminUpdatePwdReqVO dto);

    /**
     * 根据用户名和启用状态查询管理员分页列表
     *
     * @param current
     * @param size
     * @param username
     * @param status
     * @return 管理员分页列表
     */
    IPage<AdminListRespVO> page(Integer current, Integer size, String username, Integer status);

    /**
     * 新增管理员
     *
     * @param reqVO
     */
    void create(AdminCreateReqVO reqVO);

    /**
     * 编辑管理员
     *
     * @param reqVO
     */
    void update(AdminUpdateReqVO reqVO);

    /**
     * 查看管理员详情
     *
     * @param adminId
     * @return
     */
    AdminListRespVO get(Integer adminId);

    /**
     * 重置密码
     * @param adminId
     */
    void resetPwd(Integer adminId);

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
