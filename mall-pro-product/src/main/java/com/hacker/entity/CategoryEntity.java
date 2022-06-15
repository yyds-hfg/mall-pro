package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 商品三级分类
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_category")
@ApiModel(value="Category对象", description="商品三级分类")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类id")
    @TableId(value = "cat_id", type = IdType.AUTO)
    private Long catId;

    @ApiModelProperty(value = "分类名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "父分类id")
    @TableField("parent_cid")
    private Long parentCid;

    @ApiModelProperty(value = "层级")
    @TableField("cat_level")
    private Integer catLevel;

    @ApiModelProperty(value = "是否显示[0-不显示，1显示]")
    @TableField("show_status")
    @TableLogic(value = "1",delval = "0")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "图标地址")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "计量单位")
    @TableField("product_unit")
    private String productUnit;

    @ApiModelProperty(value = "商品数量")
    @TableField("product_count")
    private Integer productCount;

    /**
     * 表示表中不存在这个字段
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    @ApiModelProperty(notes = "表示表中不存在这个字段")
    private List<CategoryEntity> children;


    public static final String CAT_ID = "cat_id";

    public static final String NAME = "name";

    public static final String PARENT_CID = "parent_cid";

    public static final String CAT_LEVEL = "cat_level";

    public static final String SHOW_STATUS = "show_status";

    public static final String SORT = "sort";

    public static final String ICON = "icon";

    public static final String PRODUCT_UNIT = "product_unit";

    public static final String PRODUCT_COUNT = "product_count";

}
