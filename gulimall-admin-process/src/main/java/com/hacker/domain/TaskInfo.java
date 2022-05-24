package com.hacker.domain;

import lombok.Builder;
import lombok.Data;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;

import java.util.Date;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/5/24 10:04
 * @Description:
 */
@Data
@Builder
public class TaskInfo {

    private String id;

    private String name;

    private String description;

    private int priority;

    private String owner;

    private String assignee;

    private DelegationState delegationState;

    private String processInstanceId;

    private String executionId;

    private String processDefinitionId;

    private String caseInstanceId;

    private String caseExecutionId;

    private String caseDefinitionId;

    private Date createTime;

    private String taskDefinitionKey;

    private Date dueDate;

    private Date followUpDate;

    private String parentTaskId;

    private boolean isSuspended;

    private String formKey;

    private String tenantId;

    public static TaskInfo getTaskInstrance(Task task) {
        return TaskInfo.builder()
                .id(task.getId())
                .description(task.getDescription())
                .createTime(task.getCreateTime())
                .name(task.getName())
                .build();
    }

    public static TaskInfo getHistoricTaskInstance(HistoricTaskInstance historicTaskInstance) {
        return TaskInfo.builder()
                .id(historicTaskInstance.getId())
                .description(historicTaskInstance.getDescription())
                .createTime(historicTaskInstance.getStartTime())
                .name(historicTaskInstance.getName())
                .build();
    }

}
