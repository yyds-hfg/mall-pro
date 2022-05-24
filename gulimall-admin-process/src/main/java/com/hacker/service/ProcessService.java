package com.hacker.service;

import com.hacker.domain.ProcessInstanceInfo;
import com.hacker.domain.request.StartProcessRequest;
import com.hacker.domain.TaskInfo;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/5/24 09:37
 * @Description:
 */
public interface ProcessService {

    /**
     * 根据流程定义key启动流程
     * @param request
     * @return
     */
    public ProcessInstanceInfo startProcessInstanceByKey(StartProcessRequest request);

    /**
     * 查询代办任务
     */
    public List<TaskInfo> queryTaskAgents(String businessKey);
}
