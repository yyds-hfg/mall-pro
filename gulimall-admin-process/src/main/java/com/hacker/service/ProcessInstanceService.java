package com.hacker.service;

import com.hacker.domain.request.ProcessRequest;
import com.hacker.domain.request.RollbackProcessRequest;
import com.hacker.domain.request.TaskRequest;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/5/24 09:37
 * @Description:
 */
public interface ProcessInstanceService {
    /**
     * 流程部署
     * @param file
     * @param name
     * @param source
     * @return
     * @throws IOException
     */
    DeploymentDto deployProcess(MultipartFile file, String name, String source) throws IOException;

    /**
     * 根据流程定义key、流程定义id启动流程
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
    List<TaskDto> cancelProcess(TaskRequest request);


    /**
     * 流程驳回
     *
     * @param request
     */
    List<TaskDto> rollbackProcess(RollbackProcessRequest request);


    void test(String processInstanceId,String activityId);

}
