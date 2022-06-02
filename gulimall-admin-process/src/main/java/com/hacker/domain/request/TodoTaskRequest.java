package com.hacker.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Zero
 * @Date: 2022/5/30 08:58
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoTaskRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "索引开始的地方")
    private Integer firstResult;

    @ApiModelProperty(value = "检索maxResults个结果")
    private Integer maxResults;

}
