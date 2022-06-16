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
 * spu属性值
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_product_attr_value")
@ApiModel(value="ProductAttrValue对象", description="spu属性值")
public class ProductAttrValueEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品id")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "属性id")
    @TableField("attr_id")
    private Long attrId;

    @ApiModelProperty(value = "属性名")
    @TableField("attr_name")
    private String attrName;

    @ApiModelProperty(value = "属性值")
    @TableField("attr_value")
    private String attrValue;

    @ApiModelProperty(value = "顺序")
    @TableField("attr_sort")
    private Integer attrSort;

    @ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】")
    @TableField("quick_show")
    private Integer quickShow;


    public static final String ID = "id";

    public static final String SPU_ID = "spu_id";

    public static final String ATTR_ID = "attr_id";

    public static final String ATTR_NAME = "attr_name";

    public static final String ATTR_VALUE = "attr_value";

    public static final String ATTR_SORT = "attr_sort";

    public static final String QUICK_SHOW = "quick_show";

}
