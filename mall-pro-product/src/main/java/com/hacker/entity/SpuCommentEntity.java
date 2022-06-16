package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
 * 商品评价
 * </p>
 *
 * @author Zero
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_spu_comment")
@ApiModel(value="SpuComment对象", description="商品评价")
public class SpuCommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "sku_id")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "spu_id")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty(value = "商品名字")
    @TableField("spu_name")
    private String spuName;

    @ApiModelProperty(value = "会员昵称")
    @TableField("member_nick_name")
    private String memberNickName;

    @ApiModelProperty(value = "星级")
    @TableField("star")
    private Boolean star;

    @ApiModelProperty(value = "会员ip")
    @TableField("member_ip")
    private String memberIp;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "显示状态[0-不显示，1-显示]")
    @TableField("show_status")
    private Boolean showStatus;

    @ApiModelProperty(value = "购买时属性组合")
    @TableField("spu_attributes")
    private String spuAttributes;

    @ApiModelProperty(value = "点赞数")
    @TableField("likes_count")
    private Integer likesCount;

    @ApiModelProperty(value = "回复数")
    @TableField("reply_count")
    private Integer replyCount;

    @ApiModelProperty(value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
    @TableField("resources")
    private String resources;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "用户头像")
    @TableField("member_icon")
    private String memberIcon;

    @ApiModelProperty(value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
    @TableField("comment_type")
    private Integer commentType;


    public static final String ID = "id";

    public static final String SKU_ID = "sku_id";

    public static final String SPU_ID = "spu_id";

    public static final String SPU_NAME = "spu_name";

    public static final String MEMBER_NICK_NAME = "member_nick_name";

    public static final String STAR = "star";

    public static final String MEMBER_IP = "member_ip";

    public static final String CREATE_TIME = "create_time";

    public static final String SHOW_STATUS = "show_status";

    public static final String SPU_ATTRIBUTES = "spu_attributes";

    public static final String LIKES_COUNT = "likes_count";

    public static final String REPLY_COUNT = "reply_count";

    public static final String RESOURCES = "resources";

    public static final String CONTENT = "content";

    public static final String MEMBER_ICON = "member_icon";

    public static final String COMMENT_TYPE = "comment_type";


}
