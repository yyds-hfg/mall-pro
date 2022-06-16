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
 * 品牌分类关联
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_category_brand_relation")
@ApiModel(value="CategoryBrandRelation对象", description="品牌分类关联")
public class CategoryBrandRelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "品牌id")
    @TableField("brand_id")
    private Long brandId;

    @ApiModelProperty(value = "分类id")
    @TableField("catelog_id")
    private Long catelogId;

    @TableField("brand_name")
    private String brandName;

    @TableField("catelog_name")
    private String catelogName;

    public static final String ID = "id";

    public static final String BRAND_ID = "brand_id";

    public static final String CATELOG_ID = "catelog_id";

    public static final String BRAND_NAME = "brand_name";

    public static final String CATELOG_NAME = "catelog_name";
}
