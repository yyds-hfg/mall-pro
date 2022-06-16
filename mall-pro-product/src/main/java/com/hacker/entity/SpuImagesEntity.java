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
 * spu图片
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_spu_images")
@ApiModel(value="SpuImages对象", description="spu图片")
public class SpuImagesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "spu_id")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "图片名")
    @TableField("img_name")
    private String imgName;

    @ApiModelProperty(value = "图片地址")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty(value = "顺序")
    @TableField("img_sort")
    private Integer imgSort;

    @ApiModelProperty(value = "是否默认图")
    @TableField("default_img")
    private Integer defaultImg;


    public static final String ID = "id";

    public static final String SPU_ID = "spu_id";

    public static final String IMG_NAME = "img_name";

    public static final String IMG_URL = "img_url";

    public static final String IMG_SORT = "img_sort";

    public static final String DEFAULT_IMG = "default_img";

}
