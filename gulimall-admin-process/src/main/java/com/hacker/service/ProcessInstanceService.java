package com.hacker.service;

import com.hacker.domain.request.ProcessRequest;
import com.hacker.domain.request.TaskRequest;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/5/24 09:37
 * @Description:
 */
public interface ProcessInstanceService {

    /**
     * 根据流程定义key启动流程
     *
     * @param request
     * @return
     */
    ProcessInstanceDto startProcessInstanceByKey(ProcessRequest request);


    /**
     * 流程撤回
     *
     * @param request
     */
    void cancelProcess(TaskRequest request);


    /**
     * 流程驳回
     *
     * @param request
     */
    List<TaskDto> rollbackProcess(TaskRequest request);

}
