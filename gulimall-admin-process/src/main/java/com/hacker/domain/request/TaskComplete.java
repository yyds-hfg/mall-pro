package com.hacker.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/5/24 11:34
 * @Description:
 */
@Data
public class TaskComplete {

    @ApiModelProperty(value = "任务Id")
    @NotBlank(message = "任务Id不能为空")
    private String taskId;

    @ApiModelProperty(value = "流程变量")
    private Map<String, Object> vars;
}
