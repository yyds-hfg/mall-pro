package com.hacker.service.impl;

import com.hacker.common.exception.AccessReason;
import com.hacker.common.exception.Assert;
import com.hacker.common.utils.StrUtils;
import com.hacker.domain.request.QueryTaskRequest;
import com.hacker.domain.request.TaskComplete;
import com.hacker.domain.request.TodoTaskRequest;
import com.hacker.po.Role;
import com.hacker.po.User;
import com.hacker.service.ProcessHistoryService;
import com.hacker.service.ProcessTaskService;
import com.hacker.service.RoleService;
import com.hacker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.rest.dto.history.HistoricTaskInstanceDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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
    public TaskDto backTask(String taskId, String userId) {
        Task task = getTask(taskId);
        Assert.isTrue(task!=null,"没有该任务");
        Assert.isTrue(userId.equals(Objects.requireNonNull(task).getAssignee()),"[%s] 用户没有拾取该任务,无法归还",userId);
        taskService.setAssignee(taskId,null);
        return TaskDto.fromEntity(getTask(taskId));
    }

    private Task getTask(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    @Override
    @Transactional
    public TaskDto claim(String taskId, String userId) {
        Task task = getTask(taskId);
        Assert.isTrue(task.getAssignee()==null,"任务已经被拾取");
        taskService.claim(taskId, userId);
        return TaskDto.fromEntity(getTask(taskId));
    }

    @Override
    @Transactional
    public void setOwner(String taskId, String userId) {
        taskService.setOwner(taskId, userId);
    }

    @Transactional
    @Override
    public List<TaskDto> completeTask(TaskComplete request) {
        Task task = getTask(request.getTaskId());
        Assert.isTrue(task.getAssignee().equals(request.getUserId()),"[%s] 用户没有拾取该任务",request.getUserId());
        Comment comment = taskService.createComment(request.getTaskId(), request.getProcessInstanceId(), request.getComment());
        Assert.isTrue(comment!=null,"[%s] 创建任务评论失败",task.getId());
        taskService.complete(request.getTaskId(), request.getVars());
        return this.queryActiveTask(QueryTaskRequest.builder().processInsId(request.getProcessInstanceId()).build());
    }

    @Transactional
    @Override
    public List<TaskDto> getTodoTaskPage(TodoTaskRequest request) {
        if (request.getFirstResult()==null) {
            request.setFirstResult(0);
            request.setMaxResults(20);
        }
        //得到角色
        User user = userService.getById(request.getUserId());
        Assert.isTrue(user!=null, AccessReason.PARAM_CHECK_EXCEPTION::exception);

        String[] roleIds = user.getRoleId().split(",");
        List<Role> roleList = roleService.listByIds(getList(roleIds));
        List<String> roleCodeList = roleList.stream().map(Role::getRoleCode).collect(Collectors.toList());

        //查询待办任务--已经认领的任务
        List<Task> list = taskService.createTaskQuery()
                .or()
                .taskAssignee(request.getUserId())      //分配给自己
                .taskCandidateGroupIn(roleCodeList)     //查询出角色
                .taskCandidateUser(request.getUserId()) //候选用户
                .endOr()
                .orderByTaskCreateTime()
                .desc()                                 //创建时间倒序
                .listPage(request.getFirstResult(),request.getMaxResults());

        //查出没有被别人拾取的任务
        list = list.stream().filter(task ->task.getAssignee()==null ||
                task.getAssignee().equals(request.getUserId())).collect(Collectors.toList());

        return list.stream().map(TaskDto::fromEntity).collect(Collectors.toList());
    }

    private List<String> getList(String[] roleIds) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, roleIds);
        return list;
    }

    @Transactional
    @Override
    public List<HistoricTaskInstanceDto> queryDoneTask(String userId) {
        return processHistoryService.getDoneTaskPage(userId);
    }
}
