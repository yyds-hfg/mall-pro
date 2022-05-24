package com.hacker.service.impl;

import com.hacker.domain.ProcessInstanceInfo;
import com.hacker.domain.request.StartProcessRequest;
import com.hacker.domain.TaskInfo;
import com.hacker.domain.request.TaskComplete;
import com.hacker.service.ProcessInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Zero
 * @Date: 2022/5/24 09:37
 * @Description:
 */
@Service
@Slf4j
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    @Transactional
    public ProcessInstanceInfo startProcessInstanceByKey(StartProcessRequest request) {
        log.info(String.format("开启一个流程,流程定义Key [{%s}],流程业务Key [{%s}]",request.getDefinitionKey(),request.getBusinessKey()));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(request.getDefinitionKey(), request.getBusinessKey(), request.getVars());
        log.info(String.format("流程启动成功,流程定义Id [{%s}]",processInstance.getProcessDefinitionId()));
        return ProcessInstanceInfo.getInstance(processInstance);
    }

}
