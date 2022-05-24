package com.hacker.service;

import com.hacker.domain.TaskInfo;
import com.hacker.domain.request.TaskComplete;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/5/24 13:31
 * @Description:
 */
public interface ProcessTaskService {
    /**
     * 查询代办任务
     */
    public List<TaskInfo> queryTaskAgents(String businessKey);

    /**
     * 设置候选人
     * @param taskId
     * @param userId
     */
    public void setCandidateUser(String taskId, String userId);

    /**
     * 设置和候选组
     * @param taskId
     * @param groupId
     */
    public void setCandidateGroup(String taskId, String groupId);

    /**
     * 将给定任务的受让人更改为给定userId。标识组件不检查用户是否已知。
     * @param taskId
     * @param userId
     */
    public void setAssignee(String taskId, String userId);

    /**
     * 声明任务的责任:指定的用户被指定为任务的受让人。与setAssignee(String, String)不同的是，
     * 如果任务已经有一个分配给它的用户，那么在这里执行检查。标识组件不检查用户是否已知。
     * @param taskId 请求任务，不能为空。
     * @param userId 当userId为空时，任务是无人认领的，没有分配给任何人。
     * @throws ProcessEngineException 当任务不存在或任务已经被其他用户认领时
     */
    public void claim(String taskId, String userId);

    /**
     * 将此任务的所有权转移给另一个用户。标识组件不检查用户是否已知
     * @param taskId
     * @param userId
     */
    public void setOwner(String taskId, String userId);

    /**
     * 完成任务
     * @param taskComplete
     */
    public void completeTask(TaskComplete taskComplete);
}
