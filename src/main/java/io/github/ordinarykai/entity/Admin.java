package io.github.ordinarykai.entity;

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
 * @since 2022-08-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ADMIN")
@KeySequence("SEQ_ADMIN")
@ApiModel(value = "Admin对象", description = "管理员表")
public class Admin extends Model<Admin> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("管理员ID")
    @TableId(value = "ADMIN_ID", type = IdType.INPUT)
    private Long adminId;

    @ApiModelProperty("登录账号")
    @TableField("USERNAME")
    private String username;

    @ApiModelProperty("昵称")
    @TableField("NICKNAME")
    private String nickname;

    @ApiModelProperty("密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty("角色ID，外联ROLE ROLE_ID")
    @TableField("ROLE_ID")
    private Long roleId;

    @ApiModelProperty("状态 (0.禁用 1.启用)")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty("令牌")
    @TableField("TOKEN")
    private String token;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public Serializable pkVal() {
        return this.adminId;
    }
}
