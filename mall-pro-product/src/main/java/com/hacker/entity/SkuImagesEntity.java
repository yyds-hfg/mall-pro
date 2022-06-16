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
 * sku图片
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_sku_images")
@ApiModel(value="SkuImages对象", description="sku图片")
public class SkuImagesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "sku_id")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "图片地址")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty(value = "排序")
    @TableField("img_sort")
    private Integer imgSort;

    @ApiModelProperty(value = "默认图[0 - 不是默认图，1 - 是默认图]")
    @TableField("default_img")
    private Integer defaultImg;


    public static final String ID = "id";

    public static final String SKU_ID = "sku_id";

    public static final String IMG_URL = "img_url";

    public static final String IMG_SORT = "img_sort";

    public static final String DEFAULT_IMG = "default_img";

}
