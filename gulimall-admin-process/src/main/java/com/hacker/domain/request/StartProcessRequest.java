package com.hacker.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class StartProcessRequest {

    @ApiModelProperty("流程定义Key")
    @NotBlank(message = "流程定义Key不能为空")
    private String definitionKey;

    @ApiModelProperty("流程业务Key")
    @NotNull(message = "流程业务Key不能为空")
    private String businessKey;

    @ApiModelProperty("流程变量")
    private Map<String,Object> vars;
}
