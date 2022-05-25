package com.hacker.service;

import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/5/24 13:36
 * @Description:
 */
public interface ProcessRepositoryService {

    /**
     * 获取最新流程定义信息
     * @param definitionKey 流程定义key
     * @return ProcessDefinitionInfo
     */
    public ProcessDefinitionDto getProcessDefinition(String definitionKey);

    /**
     * 得到所有部署过的流程定义
     * @param definitionKey
     * @return
     */
    public List<ProcessDefinitionDto> getProcessDefinitionList(String definitionKey);

    /**
     * 得到部署的信息
     * @return
     */
    public List<DeploymentDto> getDeploymentInfo();
}
