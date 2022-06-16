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
 * spu信息介绍
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_spu_info_desc")
@ApiModel(value="SpuInfoDesc对象", description="spu信息介绍")
public class SpuInfoDescEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "spu_id", type = IdType.ASSIGN_UUID)
    private Long spuId;

    @ApiModelProperty(value = "商品介绍")
    @TableField("decript")
    private String decript;


    public static final String SPU_ID = "spu_id";

    public static final String DECRIPT = "decript";
}
