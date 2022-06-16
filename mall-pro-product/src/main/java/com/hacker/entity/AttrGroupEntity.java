package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 属性分组
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_attr_group")
@ApiModel(value="AttrGroup对象", description="属性分组")
public class AttrGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分组id")
    @TableId(value = "attr_group_id", type = IdType.AUTO)
    private Long attrGroupId;

    @ApiModelProperty(value = "组名")
    @TableField("attr_group_name")
    private String attrGroupName;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "描述")
    @TableField("descript")
    private String descript;

    @ApiModelProperty(value = "组图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "所属分类id")
    @TableField("catelog_id")
    private Long catelogId;


    @TableField(exist = false)
    private Long[] catelogPath;


    public static final String ATTR_GROUP_ID = "attr_group_id";

    public static final String ATTR_GROUP_NAME = "attr_group_name";

    public static final String SORT = "sort";

    public static final String DESCRIPT = "descript";

    public static final String ICON = "icon";

    public static final String CATELOG_ID = "catelog_id";

}
