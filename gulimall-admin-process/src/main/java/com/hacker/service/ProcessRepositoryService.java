package com.hacker.service;

import com.hacker.domain.DeploymentInfo;
import com.hacker.domain.ProcessDefinitionInfo;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;

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
    public ProcessDefinitionInfo getProcessDefinition(String definitionKey);

    /**
     * 得到所有部署过的流程定义
     * @param definitionKey
     * @return
     */
    public List<ProcessDefinitionInfo> getProcessDefinitionList(String definitionKey);

    public List<DeploymentInfo> getDeploymentInfo();
}
