package com.github.ordinarykai.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("admin")
@ApiModel(value = "Admin对象", description = "管理员表")
public class Admin extends Model<Admin> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("管理员ID")
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Long adminId;

    @ApiModelProperty("登录账号")
    @TableField("username")
    private String username;

    @ApiModelProperty("昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("角色ID，外联role role_id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty("状态 (0.禁用 1.启用)")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("令牌")
    @TableField("token")
    private String token;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public Serializable pkVal() {
        return this.adminId;
    }
}
