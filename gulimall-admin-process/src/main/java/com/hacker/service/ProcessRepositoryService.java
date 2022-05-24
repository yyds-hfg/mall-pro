package com.hacker.service;

import com.hacker.domain.ProcessDefinitionInfo;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/**
 * @Author: Zero
 * @Date: 2022/5/24 13:36
 * @Description:
 */
public interface ProcessRepositoryService {

    /**
     * 获取流程定义信息
     * @param definitionKey 流程定义key
     * @return ProcessDefinitionInfo
     */
    public ProcessDefinitionInfo getProcessDefinition(String definitionKey);
}
