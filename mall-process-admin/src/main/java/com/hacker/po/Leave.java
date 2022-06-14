package com.hacker.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 请假业务
 * </p>
 *
 * @author Zero
 * @since 2022-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("leave")
@ApiModel(value="Leave对象", description="请假业务")
@Builder
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "流程实列Id")
    @TableField("process_instance_id")
    private String processInstanceId;

    @ApiModelProperty(value = "请假原因")
    @TableField("reason")
    private String reason;

    @ApiModelProperty(value = "请假天数")
    @TableField("days")
    private String days;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


    public static final String ID = "id";

    public static final String PROCESS_INSTANCE_ID = "process_instance_id";

    public static final String REASON = "reason";

    public static final String DAYS = "days";

    public static final String CREATE_TIME = "create_time";

}
