package com.hacker.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zero
 * @Date: 2022/5/27 10:38
 * @Description:
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "查询当前流程任务")
public class QueryTaskRequest {

    @ApiModelProperty(value = "业务Key")
    private String businessKey;

    @ApiModelProperty("流程实例Id")
    private String processInsId;
}
