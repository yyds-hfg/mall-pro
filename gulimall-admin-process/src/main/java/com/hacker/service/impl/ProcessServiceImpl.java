package com.hacker.service.impl;

import com.hacker.domain.ProcessInstanceInfo;
import com.hacker.domain.request.StartProcessRequest;
import com.hacker.domain.TaskInfo;
import com.hacker.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Zero
 * @Date: 2022/5/24 09:37
 * @Description:
 */
@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public ProcessInstanceInfo startProcessInstanceByKey(StartProcessRequest request) {
        log.info(String.format("开启一个流程,流程定义Key [{%s}],流程业务Key [{%s}]",request.getDefinitionKey(),request.getBusinessKey()));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(request.getDefinitionKey(), request.getBusinessKey(), request.getVars());
        log.info(String.format("流程启动成功,流程定义Id [{%s}]",processInstance.getProcessDefinitionId()));
        return ProcessInstanceInfo.getInstance(processInstance);
    }

    @Override
    public List<TaskInfo> queryTaskAgents(String businessKey) {
        log.info(String.format("查询代办任务,流程业务Key [{%s}]",businessKey));
        List<Task> list = taskService.createTaskQuery().processInstanceBusinessKey(businessKey)
                .active().list();
        log.info(String.format("查询代办任务完成,代办任务数 [{%d}]",list.size()));
        return list.stream().map(TaskInfo::getInstrance).collect(Collectors.toList());
    }



}
