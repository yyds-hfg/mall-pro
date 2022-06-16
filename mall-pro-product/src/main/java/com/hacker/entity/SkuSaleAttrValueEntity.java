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
 * sku销售属性&值
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_sku_sale_attr_value")
@ApiModel(value="SkuSaleAttrValue对象", description="sku销售属性&值")
public class SkuSaleAttrValueEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "sku_id")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "attr_id")
    @TableField("attr_id")
    private Long attrId;

    @ApiModelProperty(value = "销售属性名")
    @TableField("attr_name")
    private String attrName;

    @ApiModelProperty(value = "销售属性值")
    @TableField("attr_value")
    private String attrValue;

    @ApiModelProperty(value = "顺序")
    @TableField("attr_sort")
    private Integer attrSort;


    public static final String ID = "id";

    public static final String SKU_ID = "sku_id";

    public static final String ATTR_ID = "attr_id";

    public static final String ATTR_NAME = "attr_name";

    public static final String ATTR_VALUE = "attr_value";

    public static final String ATTR_SORT = "attr_sort";

}
