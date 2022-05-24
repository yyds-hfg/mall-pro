package com.hacker.domain;

import lombok.Builder;
import lombok.Data;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/**
 * @Author: Zero
 * @Date: 2022/5/24 13:39
 * @Description:
 */
@Data
@Builder
public class ProcessDefinitionInfo {

    /** 流程描述 **/
    private String description;

    /** 这个流程定义有一个开始表单键吗? */
    private boolean hasStartFormKey;

    /** 如果进程定义处于挂起状态，则返回true */
    private boolean isSuspended;

    /** 流程定义的版本标记 */
    private String versionTag;

    /** 如果进程定义在Tasklist中是可启动的，返回true */
    private boolean isStartableInTasklist;
    
    public static ProcessDefinitionInfo getInstance(ProcessDefinition processDefinition) {
        return ProcessDefinitionInfo.builder()
                .description(processDefinition.getDescription())
                .hasStartFormKey(processDefinition.hasStartFormKey())
                .isSuspended(processDefinition.isSuspended())
                .versionTag(processDefinition.getVersionTag())
                .isStartableInTasklist(processDefinition.isStartableInTasklist())
                .build();
    }

}
