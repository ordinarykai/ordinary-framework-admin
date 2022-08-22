package io.github.ordinarykai.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wukai
 * @since 2022-08-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ROLE")
@KeySequence("SEQ_ROLE")
@ApiModel(value = "Role对象", description = "角色表")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    @TableId(value = "ROLE_ID", type = IdType.INPUT)
    private Long roleId;

    @ApiModelProperty("角色名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty("权限ID集合，外联permission permission_id")
    @TableField("PERMISSION_IDS")
    private String permissionIds;

    @ApiModelProperty("状态 (0.禁用 1.启用)")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public Serializable pkVal() {
        return this.roleId;
    }
}
