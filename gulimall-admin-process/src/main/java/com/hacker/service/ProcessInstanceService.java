package com.hacker.service;

import com.hacker.domain.request.StartProcessRequest;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;

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
    public ProcessInstanceDto startProcessInstanceByKey(StartProcessRequest request);

}
