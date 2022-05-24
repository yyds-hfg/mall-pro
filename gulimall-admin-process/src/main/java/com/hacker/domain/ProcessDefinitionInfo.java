package com.hacker.domain;

import lombok.Builder;
import lombok.Data;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/**
 * @Author: Zero
 * @Date: 2022/5/24 13:39
 * @Description:
 */
@Data
@Builder
public class ProcessDefinitionInfo {

    /**
     *
     */
    private String definitionKey;

    private String deploymentId;

    private String resourceName;

    private String definitionId;

    private String processName;

    public static ProcessDefinitionInfo getInstance(ProcessDefinition processDefinition) {
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) processDefinition;
        return ProcessDefinitionInfo.builder()
                .definitionKey(definitionEntity.getKey())
                .deploymentId(definitionEntity.getDeploymentId())
                .resourceName(definitionEntity.getResourceName())
                .definitionId(definitionEntity.getId())
                .processName(definitionEntity.getName())
                .build();

    }

}
