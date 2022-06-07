package com.hacker.controller;

import com.hacker.common.exception.AccessReason;
import com.hacker.common.exception.Assert;
import com.hacker.common.result.R;
import com.hacker.domain.request.ProcessRequest;
import com.hacker.domain.request.TaskComplete;
import com.hacker.domain.request.TodoTaskRequest;
import com.hacker.service.ProcessInstanceService;
import com.hacker.service.ProcessTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Zero
 * @Date: 2022/6/1 15:52
 * @Description:
 */
@RestController
@Api(tags = "流程操作控制器")
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessInstanceService processInstanceService;

    @Autowired
    private ProcessTaskService processTaskService;

    @ApiOperation(value = "流程部署",notes = "流程部署")
    @PostMapping("/deployProcess")
    public R<?> deployProcess(@NotNull(message = "部署资源不能为空") MultipartFile file,
                              String name, String source) {
        return R.run(()-> processInstanceService.deployProcess(file, name, source));
    }

    @ApiOperation(value = "发起流程",notes = "流程发起")
    @PostMapping("/start")
    public R<?> startProcess(@NotNull @RequestBody ProcessRequest request) {
        return R.run(() -> processInstanceService.startProcessInstanceByKey(request));
    }

    @ApiOperation(value = "流程挂起")
    @GetMapping("/suspendProcess/{processInstanceId}")
    public R<?> suspendProcess(@PathVariable String processInstanceId) {
        Assert.notNull(processInstanceId,AccessReason.NULL_POINT_EXCEPTION::exception);
        return R.run(() -> processInstanceService.suspendProcess(processInstanceId));
    }

    @ApiOperation(value = "流程激活")
    @GetMapping("/activateProcess/{processInstanceId}")
    public R<?> activateProcess(@PathVariable String processInstanceId) {
        Assert.notNull(processInstanceId,AccessReason.NULL_POINT_EXCEPTION::exception);
        return R.run(() -> processInstanceService.activateProcess(processInstanceId));
    }

    @ApiOperation(value = "拾取任务",notes = "声明任务的责任:指定的用户被指定为任务的受让人。" +
            "与setAssignee(String, String)不同的是,如果任务已经有一个分配给它的用户，" +
            "那么在这里执行检查。标识组件不检查用户是否已知。")
    @GetMapping("/claim/{taskId}/{userId}")
    public R<?> claim(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                      @NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(() -> processTaskService.claim(taskId, userId));
    }

    @GetMapping("/backTask/{taskId}/{userId}")
    @ApiOperation(value = "归还任务")
    public R<?> backTask(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                         @NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(()->processTaskService.backTask(taskId,userId));
    }

    @ApiOperation(value = "完成当前人工任务",notes = "将任务标记为已完成，并继续流程执行")
    @PostMapping("/completeTask")
    public R<?> completeTask(@RequestBody TaskComplete request) {
        return R.run(() -> processTaskService.completeTask(request));
    }

    @ApiOperation(value = "查询用户代办任务 ",notes = "查询用户代办任务")
    @PostMapping("/getTodoTaskPage")
    public R<?> getTodoTaskPage(@RequestBody TodoTaskRequest request) {
        Assert.isTrue(StringUtils.isNotBlank(request.getUserId()),AccessReason.PARAM_CHECK_EXCEPTION::exception);
        return R.run(() -> processTaskService.getTodoTaskPage(request));
    }

    @ApiOperation(value = "查询已办任务",notes = "查询自己已近完成办理的任务")
    @GetMapping("/queryDoneTask/{userId}")
    public R<?> queryDoneTask(@NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(() -> processTaskService.queryDoneTask(userId));
    }

}
