package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * sku信息
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_sku_info")
@ApiModel(value="SkuInfo对象", description="sku信息")
public class SkuInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "skuId")
    @TableId(value = "sku_id", type = IdType.AUTO)
    private Long skuId;

    @ApiModelProperty(value = "spuId")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "sku名称")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty(value = "sku介绍描述")
    @TableField("sku_desc")
    private String skuDesc;

    @ApiModelProperty(value = "所属分类id")
    @TableField("catalog_id")
    private Long catalogId;

    @ApiModelProperty(value = "品牌id")
    @TableField("brand_id")
    private Long brandId;

    @ApiModelProperty(value = "默认图片")
    @TableField("sku_default_img")
    private String skuDefaultImg;

    @ApiModelProperty(value = "标题")
    @TableField("sku_title")
    private String skuTitle;

    @ApiModelProperty(value = "副标题")
    @TableField("sku_subtitle")
    private String skuSubtitle;

    @ApiModelProperty(value = "价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "销量")
    @TableField("sale_count")
    private Long saleCount;


    public static final String SKU_ID = "sku_id";

    public static final String SPU_ID = "spu_id";

    public static final String SKU_NAME = "sku_name";

    public static final String SKU_DESC = "sku_desc";

    public static final String CATALOG_ID = "catalog_id";

    public static final String BRAND_ID = "brand_id";

    public static final String SKU_DEFAULT_IMG = "sku_default_img";

    public static final String SKU_TITLE = "sku_title";

    public static final String SKU_SUBTITLE = "sku_subtitle";

    public static final String PRICE = "price";

    public static final String SALE_COUNT = "sale_count";

}
