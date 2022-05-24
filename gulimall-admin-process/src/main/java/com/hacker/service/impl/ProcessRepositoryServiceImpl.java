package com.hacker.service.impl;

import com.hacker.domain.DeploymentInfo;
import com.hacker.domain.ProcessDefinitionInfo;
import com.hacker.service.ProcessRepositoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
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
    public ProcessDefinitionInfo getProcessDefinition(String definitionKey) {
        return getProcessDefinitionLists(definitionKey).get(0);
    }

    @Override
    @Transactional
    public List<ProcessDefinitionInfo> getProcessDefinitionList(String definitionKey) {
        return getProcessDefinitionLists(definitionKey);
    }

    @Override
    @Transactional
    public List<DeploymentInfo> getDeploymentInfo() {
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        return list.stream().map(DeploymentInfo::getInstance).collect(Collectors.toList());
    }

    /**
     * 获取当前流程定义所部署的所以流程定义信息
     * @param definitionKey 流程定义Key
     * @return List<ProcessDefinitionInfo>
     */
    private List<ProcessDefinitionInfo> getProcessDefinitionLists(String definitionKey) {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(definitionKey).active().orderByDeploymentTime().asc().list();
        return list.stream().map(ProcessDefinitionInfo::getInstance).collect(Collectors.toList());
    }

}
