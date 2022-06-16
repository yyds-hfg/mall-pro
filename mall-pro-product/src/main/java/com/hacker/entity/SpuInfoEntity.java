package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * spu信息
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_spu_info")
@ApiModel(value="SpuInfo对象", description="spu信息")
public class SpuInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品名称")
    @TableField("spu_name")
    private String spuName;

    @ApiModelProperty(value = "商品描述")
    @TableField("spu_description")
    private String spuDescription;

    @ApiModelProperty(value = "所属分类id")
    @TableField("catalog_id")
    private Long catalogId;

    @ApiModelProperty(value = "品牌id")
    @TableField("brand_id")
    private Long brandId;

    @TableField("weight")
    private BigDecimal weight;

    @ApiModelProperty(value = "上架状态[0 - 下架，1 - 上架]")
    @TableField("publish_status")
    private Integer publishStatus;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String SPU_NAME = "spu_name";

    public static final String SPU_DESCRIPTION = "spu_description";

    public static final String CATALOG_ID = "catalog_id";

    public static final String BRAND_ID = "brand_id";

    public static final String WEIGHT = "weight";

    public static final String PUBLISH_STATUS = "publish_status";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";
}
