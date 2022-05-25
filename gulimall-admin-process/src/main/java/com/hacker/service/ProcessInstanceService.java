package com.hacker.service;

import com.hacker.domain.request.ProcessRequest;
import com.hacker.domain.request.TaskRequest;
import com.hacker.result.R;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: Zero
 * @Date: 2022/5/24 09:37
 * @Description:
 */
public interface ProcessInstanceService {

    /**
     * 根据流程定义key启动流程
     * @param request
     * @return
     */
    public ProcessInstanceDto startProcessInstanceByKey(ProcessRequest request);


    /**
     * 流程撤回
     * @param request
     */
    public void cancelProcess(TaskRequest request);


    /**
     * 流程驳回
     * @param request
     */
    public void rollbackProcess(TaskRequest request);

}
