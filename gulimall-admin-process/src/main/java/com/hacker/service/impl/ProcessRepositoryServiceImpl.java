package com.hacker.service.impl;

import com.hacker.common.annotation.SystemLog;
import com.hacker.service.ProcessRepositoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Zero
 * @Date: 2022/5/24 13:36
 * @Description:
 */
@Service
public class ProcessRepositoryServiceImpl implements ProcessRepositoryService {

    @Autowired
    private RepositoryService repositoryService;

    @Override
    @Transactional
    public ProcessDefinitionDto getProcessDefinition(String definitionKey) {
        return getProcessDefinitionLists(definitionKey).get(0);
    }

    @Override
    @Transactional
    public List<ProcessDefinitionDto> getProcessDefinitionList(String definitionKey) {
        return getProcessDefinitionLists(definitionKey);
    }

    @Override
    @Transactional
    @SystemLog
    public List<DeploymentDto> getDeploymentInfo() {
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        return list.stream().map(DeploymentDto::fromDeployment).collect(Collectors.toList());
    }

    /**
     * 获取当前流程定义所部署的所以流程定义信息
     *
     * @param definitionKey 流程定义Key
     * @return List<ProcessDefinitionInfo>
     */
    private List<ProcessDefinitionDto> getProcessDefinitionLists(String definitionKey) {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(definitionKey).active().orderByDeploymentTime().asc().list();
        return list.stream().map(ProcessDefinitionDto::fromProcessDefinition).collect(Collectors.toList());
    }

}
