package com.hacker.service;

import com.hacker.domain.TaskInfo;
import com.hacker.domain.request.TaskComplete;
import org.camunda.bpm.engine.AuthorizationException;
import org.camunda.bpm.engine.ProcessEngineException;

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
     * @param taskId 任务Id
     * @param userId 用户Id
     * @throws ProcessEngineException 当任务或用户不存在时。(Camunda是否保存了这个任务)
     * @throws AuthorizationException 用户没有权限。更新资源的权限。任务或没有权限。
     * UPDATE_TASK对资源的权限。PROCESS_DEFINITION(如果任务是正在运行的流程实例的一部分)。
     */
    public void setCandidateUser(String taskId, String userId);

    /**
     * 设置和候选组
     * @param taskId
     * @param groupId
     * @throws ProcessEngineException—当任务或组不存在时。
     * @throws AuthorizationException—用户没有权限。更新资源的权限。任务或没有权限。
     * UPDATE_TASK对资源的权限。PROCESS_DEFINITION(如果任务是正在运行的流程实例的一部分)。
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

    /**
     * 查询已办任务
     * @param userId 用户Id
     * @return
     */
    public List<TaskInfo> queryDoneTask(String userId);
}
