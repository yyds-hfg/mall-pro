package com.hacker.service.impl;

import com.hacker.common.utils.StrUtils;
import com.hacker.domain.request.QueryTaskRequest;
import com.hacker.domain.request.TaskComplete;
import com.hacker.domain.request.TaskRequest;
import com.hacker.service.ProcessHistoryService;
import com.hacker.service.ProcessTaskService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.rest.dto.history.HistoricTaskInstanceDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Zero
 * @Date: 2022/5/24 13:31
 * @Description:
 */
@Service
@Slf4j
public class ProcessTaskServiceImpl implements ProcessTaskService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessHistoryService processHistoryService;

    @Override
    @Transactional
    public List<TaskDto> queryActiveTask(QueryTaskRequest request) {
        log.info(String.format("查询代办任务,流程业务Key [{%s}] ,流程实例Id [{%s}]", request.getBusinessKey(),request.getProcessInsId()));
        List<Task> list = null;
        if (!StrUtils.isBlank(request.getBusinessKey())) {
            list = taskService.createTaskQuery()
                    .processInstanceBusinessKey(request.getBusinessKey())
                    .active()
                    .list();
        } else if (!StrUtils.isBlank(request.getProcessInsId())) {
            list = taskService.createTaskQuery()
                    .processInstanceId(request.getProcessInsId())
                    .active()
                    .list();
        }
        assert list != null;
        log.info(String.format("查询代办任务完成,代办任务数 [{%d}]", list.size()));
        return list.stream().map(TaskDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setCandidateUser(String taskId, String userId) {
        taskService.addCandidateUser(taskId, userId);
    }

    @Override
    @Transactional
    public void setCandidateGroup(String taskId, String groupId) {
        taskService.addCandidateGroup(taskId, groupId);
    }

    @Override
    @Transactional
    public void setAssignee(String taskId, String userId) {
        taskService.setAssignee(taskId, userId);
    }

    @Override
    @Transactional
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    @Override
    @Transactional
    public void setOwner(String taskId, String userId) {
        taskService.setOwner(taskId, userId);
    }

    @Transactional
    @Override
    public List<TaskDto> completeTask(TaskRequest request) {
        taskService.createComment(request.getTaskId(), request.getProcessInstId(), request.getMessage());
        taskService.complete(request.getTaskId(), request.getVariables());
        return this.queryActiveTask(QueryTaskRequest.builder().processInsId(request.getProcessInstId()).build());
    }

    @Transactional
    @Override
    public List<TaskDto> getTodoTaskPage(String userId) {
        // 查询待办任务
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(userId) // 分配给自己
                .orderByTaskCreateTime()
                .desc()
                .list();// 创建时间倒序
        return list.stream().map(TaskDto::fromEntity).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public List<HistoricTaskInstanceDto> queryDoneTask(String userId) {
        return processHistoryService.getDoneTaskPage(userId);
    }
}
