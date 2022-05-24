package com.hacker.service.impl;

import com.hacker.domain.ProcessDefinitionInfo;
import com.hacker.service.ProcessRepositoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(definitionKey).active().singleResult();
        return ProcessDefinitionInfo.getInstance(processDefinition);
    }

}
