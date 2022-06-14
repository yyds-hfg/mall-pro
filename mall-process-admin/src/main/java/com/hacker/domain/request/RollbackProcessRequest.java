package com.hacker.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/5/27 14:54
 * @Description:
 */
@Data
public class RollbackProcessRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程实例ID")
    private String processInstId;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "活动定义Key")
    private String acticityDefKey;

    @ApiModelProperty(value = "驳回类型，1：起草节点，2：上一节点，3：目标节点")
    private String rejectType;

    @ApiModelProperty(value = "目标节点Id")
    private String toActId;

    @ApiModelProperty(value = "流程变量键值对")
    private Map<String, Object> variables;

    @ApiModelProperty(value = "任务变量键值对")
    private Map<String, Object> localVariables;

}
