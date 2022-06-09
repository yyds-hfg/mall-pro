package com.hacker.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * 流程参数对象
 *
 * @Author: Zero
 * @Date: 2022/5/25 11:44
 * @Description:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ProcessRequest-流程参数对象")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程定义ID")
    private String processDefId;

    @ApiModelProperty(value = "流程定义Key")
    private String processDefKey;

    @ApiModelProperty(value = "流程发起者")
    private String stater;

    @ApiModelProperty(value = "流程标题")
    private String title;

    @ApiModelProperty(value = "外部业务系统数据主键标识值")
    private String businessKey;

    @ApiModelProperty(value = "流程变量键值对")
    private Map<String, Object> variables;

}
