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
 * 属性&属性分组关联
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_attr_attrgroup_relation")
@ApiModel(value="AttrAttrgroupRelation对象", description="属性&属性分组关联")
public class AttrAttrgroupRelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "属性id")
    @TableField("attr_id")
    private Long attrId;

    @ApiModelProperty(value = "属性分组id")
    @TableField("attr_group_id")
    private Long attrGroupId;

    @ApiModelProperty(value = "属性组内排序")
    @TableField("attr_sort")
    private Integer attrSort;


    public static final String ID = "id";

    public static final String ATTR_ID = "attr_id";

    public static final String ATTR_GROUP_ID = "attr_group_id";

    public static final String ATTR_SORT = "attr_sort";

}
