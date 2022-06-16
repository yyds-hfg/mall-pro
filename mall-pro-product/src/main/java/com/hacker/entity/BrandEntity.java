package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.hacker.common.valid.AddGroup;
import com.hacker.common.valid.ListValue;
import com.hacker.common.valid.UpdateGroup;
import com.hacker.common.valid.UpdateStatusGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * <p>
 * 品牌
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_brand")
@ApiModel(value="Brand对象", description="品牌")
public class BrandEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @NotNull(message = "修改必须指定品牌id", groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id", groups = {AddGroup.class})
    @ApiModelProperty(value = "品牌id")
    @TableId(value = "brand_id", type = IdType.AUTO)
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名必须提交", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "品牌名")
    @TableField("name")
    private String name;
    /**
     * 品牌logo地址
     */
    @NotBlank(groups = {AddGroup.class})
    @URL(message = "logo必须是一个合法的url地址", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "品牌logo地址")
    @TableField("logo")
    private String logo;
    /**
     * 介绍
     */

    @ApiModelProperty(value = "介绍")
    @TableField("descript")
    private String descript;

    /**
     * 显示状态[0-不显示；1-显示]
     */
    @NotNull(groups = {AddGroup.class, UpdateStatusGroup.class})
    @ListValue(vals = {0, 1}, groups = {AddGroup.class, UpdateStatusGroup.class})
    @ApiModelProperty(value = "显示状态[0-不显示；1-显示]")
    @TableField("show_status")
    private Integer showStatus;

    /**
     * 检索首字母
     */
    @NotEmpty(groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "检索首字母")
    @TableField("first_letter")
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull(groups = {AddGroup.class})
    @Min(value = 0, message = "排序必须大于等于0", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;


    public static final String BRAND_ID = "brand_id";

    public static final String NAME = "name";

    public static final String LOGO = "logo";

    public static final String DESCRIPT = "descript";

    public static final String SHOW_STATUS = "show_status";

    public static final String FIRST_LETTER = "first_letter";

    public static final String SORT = "sort";
}
