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
 * 权限表
 * </p>
 *
 * @author wukai
 * @since 2022-08-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("permission")
@ApiModel(value = "Permission对象", description = "权限表")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限ID")
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Long permissionId;

    @ApiModelProperty("父级权限ID (顶级权限的parent_id=0)")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("类型 (1.菜单 2.接口)")
    @TableField("type")
    private Boolean type;

    @ApiModelProperty("权限名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("权限标识 (菜单权限是前端路由，接口权限是uri)")
    @TableField("value")
    private String value;

    @ApiModelProperty("序号 (按降序排列)")
    @TableField("num")
    private Integer num;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public Serializable pkVal() {
        return this.permissionId;
    }
}
