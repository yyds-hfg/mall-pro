package com.hacker.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hacker.po.Leave;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/6/6 19:43
 * @Description:
 */
@Data
public class LeaveDto {

    @ApiModelProperty(value = "流程定义ID")
    private String processDefId;

    @ApiModelProperty(value = "流程定义Key")
    private String processDefKey;

    @ApiModelProperty(value = "流程发起者")
    private String stater;

    @ApiModelProperty(value = "流程标题")
    private String title;

    @ApiModelProperty(value = "请假原因")
    private String reason;

    @ApiModelProperty(value = "请假天数")
    private String days;

    @ApiModelProperty(value = "流程变量键值对")
    private Map<String, Object> variables;
}
