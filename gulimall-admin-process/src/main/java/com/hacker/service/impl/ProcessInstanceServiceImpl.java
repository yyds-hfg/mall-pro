package com.hacker.service.impl;

import com.hacker.common.annotation.SystemLog;
import com.hacker.common.exception.AccessReason;
import com.hacker.common.exception.Assert;
import com.hacker.consts.TaskConstance;
import com.hacker.domain.request.ProcessRequest;
import com.hacker.domain.request.QueryTaskRequest;
import com.hacker.domain.request.RollbackProcessRequest;
import com.hacker.domain.request.TaskRequest;
import com.hacker.po.Business;
import com.hacker.service.BusinessService;
import com.hacker.service.ProcessInstanceService;
import com.hacker.service.ProcessTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipInputStream;

/**
 * @Author: Zero
 * @Date: 2022/5/24 09:37
 * @Description:
 */
@Service
@Slf4j
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    @Autowired
    private ProcessTaskService processTaskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private RepositoryService repositoryService;


    @Override
    public DeploymentDto deployProcess(MultipartFile multipartFile, String name, String source) throws IOException {
        Deployment deploy = repositoryService.createDeployment()
                .name(name)
                .source(source)
                .addInputStream(multipartFile.getOriginalFilename(),multipartFile.getInputStream())
                .deploy();
        return DeploymentDto.fromDeployment(deploy);
    }

    @Override
    @SystemLog
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ProcessInstanceDto startProcessInstanceByKey(ProcessRequest request) {
        ProcessInstance processInstance = null;
        Map<String, Object> variables = request.getVariables();
        variables.put("stater", request.getStater());
        //通过流程id或者流程key发起流程
        if (StringUtils.isNotBlank(request.getProcessDefId())) {
            processInstance = runtimeService.startProcessInstanceById(request.getProcessDefId(),
                    request.getBusinessKey(), variables);
        } else if (StringUtils.isNotBlank(request.getProcessDefKey())) {
            processInstance = runtimeService.startProcessInstanceByKey(request.getProcessDefKey(),
                    request.getBusinessKey(), variables);
        }
        Assert.isTrue(processInstance != null,AccessReason.NULL_POINT_EXCEPTION::exception);
       /** Assert.isTrue(businessService.save(Business.builder()
                .businessKey(request.getBusinessKey())
                .processInstanceId(processInstance.getProcessInstanceId())
                .businessStater(request.getStater())
                .businessTitle(request.getTitle())
                .businessType(request.getBusinessType())
                .createTime(LocalDateTime.now())
                .build()),"业务 [%s] 存储发生异常",request.getBusinessKey());*/
        log.info(String.format("流程启动成功,流程实列Id [{%s}]", Objects.requireNonNull(processInstance).getProcessInstanceId()));
        return ProcessInstanceDto.fromProcessInstance(processInstance);
    }

    @Override
    public ProcessInstanceDto suspendProcess(String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        return ProcessInstanceDto.fromProcessInstance(processInstance);
    }

    @Override
    public ProcessInstanceDto activateProcess(String processInstanceId) {
        runtimeService.activateProcessInstanceById(processInstanceId);
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        return ProcessInstanceDto.fromProcessInstance(processInstance);
    }

    @Override
    public List<TaskDto> cancelProcess(TaskRequest request) {
        ActivityInstance tree = runtimeService.getActivityInstance(request.getProcessInstId());
        taskService.createComment(request.getTaskId(), request.getProcessInstId(), "撤回流程");
        if (tree == null) {
            throw AccessReason.PARAM_CHECK_EXCEPTION.exception("活动实例不能为空");
        }
        runtimeService
                .createProcessInstanceModification(request.getProcessInstId())
                .cancelActivityInstance(getInstanceIdForActivity(tree, tree.getActivityId()))
                .startBeforeActivity(request.getActicityDefKey())
                .execute();
        return processTaskService.queryActiveTask(QueryTaskRequest.builder().processInsId(request.getProcessInstId()).build());
    }


    @Override
    @SystemLog
    public List<TaskDto> rollbackProcess(RollbackProcessRequest request) {
        ActivityInstance tree = runtimeService.getActivityInstance(request.getProcessInstId());

        String rejectType = request.getRejectType();
        if (StringUtils.isBlank(rejectType)) {
            throw AccessReason.POCESS_REJECT_TYPE.exception("驳回类型不能为空");
        }
        if (rejectType.equals(TaskConstance.REJECT_TO_START)) {
            List<HistoricActivityInstance> resultList = historyService
                    .createHistoricActivityInstanceQuery()
                    .processInstanceId(request.getProcessInstId())
                    .activityType("userTask")
                    .finished()
                    .orderByHistoricActivityInstanceEndTime()
                    .asc()
                    .list();
            if (resultList == null || resultList.size() <= 0) {
                throw AccessReason.POCESS_REJECT_TYPE.exception("未找到发起节点");
            }
            //找到第一个人工起草节点
            request.setToActId(resultList.get(0).getActivityId());

        } else if (rejectType.equals(TaskConstance.REJECT_TO_LAST)) {
            List<HistoricActivityInstance> resultList = historyService
                    .createHistoricActivityInstanceQuery()
                    .processInstanceId(request.getProcessInstId())
                    .activityType("userTask")
                    .finished()
                    .orderByHistoricActivityInstanceEndTime()
                    .desc()
                    .list();
            if (resultList == null || resultList.size() <= 0) {
                throw AccessReason.POCESS_REJECT_TYPE.exception("未找到上一节点");
            }
            //找到上一个节点
            request.setToActId(resultList.get(0).getActivityId());

        } else if (rejectType.equals(TaskConstance.REJECT_TO_TARGET)) {
            if (StringUtils.isBlank(request.getToActId())) {
                throw AccessReason.POCESS_REJECT_TYPE.exception("指定目标节点不能为空");
            }
        } else {
            throw AccessReason.POCESS_REJECT_TYPE.exception("驳回类型值不对，三种类型  1：起草节点，2：上一节点，3：目标节点");
        }

        taskService.createComment(request.getTaskId(), request.getProcessInstId(), "驳回流程");
        runtimeService
                .createProcessInstanceModification(request.getProcessInstId())
//                .cancelAllForActivity(request.getActicityDefKey()) //当前活动定义key
                .cancelActivityInstance(getInstanceIdForActivity(tree, request.getActicityDefKey()))
                .startBeforeActivity(request.getToActId())
                .setVariables(request.getVariables())
                .setVariablesLocal(request.getLocalVariables())
                .execute();
        return processTaskService.queryActiveTask(QueryTaskRequest.builder().processInsId(request.getProcessInstId()).build());
    }

    @Override
    public void test(String processInstanceId, String activityId) {

    }


    /**
     *
     * @param activityInstance activityInstance
     * @param activityId activityId
     * @return
     */
    public String getInstanceIdForActivity(ActivityInstance activityInstance, String activityId) {
        ActivityInstance instance = getChildInstanceForActivity(activityInstance, activityId);
        if (instance != null) {
            return instance.getId();
        }
        return null;
    }

    public ActivityInstance getChildInstanceForActivity(ActivityInstance activityInstance, String activityId) {
        if (activityId.equals(activityInstance.getActivityId())) {
            return activityInstance;
        }
        for (ActivityInstance childInstance : activityInstance.getChildActivityInstances()) {
            ActivityInstance instance = getChildInstanceForActivity(childInstance, activityId);
            if (instance != null) {
                return instance;
            }
        }
        return null;
    }

}
