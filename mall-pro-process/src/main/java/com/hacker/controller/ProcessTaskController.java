package com.hacker.controller;

import com.hacker.common.exception.AccessReason;
import com.hacker.common.result.R;
import com.hacker.common.utils.StrUtils;
import com.hacker.domain.request.QueryTaskRequest;
import com.hacker.domain.request.TaskComplete;
import com.hacker.domain.request.TodoTaskRequest;
import com.hacker.service.ProcessTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Zero
 * @Date: 2022/5/24 13:28
 * @Description:
 */
@RestController
@Api(tags = "流程任务控制器")
@RequestMapping("/task")
public class ProcessTaskController {

    @Autowired
    private ProcessTaskService processTaskService;

    @ApiOperation(value = "查询流程当前任务 ",notes = "根据业务Key查询当前流程任务节点信息")
    @PostMapping("/queryTaskAgents")
    public R<?> queryTaskAgents(@RequestBody QueryTaskRequest request) {
        return R.run(() -> processTaskService.queryActiveTask(request));
    }

    @ApiOperation(value = "设置任务候选人",notes = "设置候选人")
    @GetMapping("/setCandidateUser/{taskId}/{userId}")
    public R<?> setCandidateUser(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                                 @NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(() -> processTaskService.setCandidateUser(taskId, userId));
    }

    @ApiOperation(value = "设置任务候选组",notes = "设置任务候选组")
    @GetMapping("/setCandidateGroup/{taskId}/{groupId}")
    public R<?> setCandidateGroup(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                                  @NotBlank(message = "groupId不能为空") @PathVariable String groupId) {
        return R.run(() -> processTaskService.setCandidateGroup(taskId, groupId));
    }

    @ApiOperation(value = "设置认领任务人",notes = "将给定任务的受让人更改为给定userId。标识组件不检查用户是否已知。")
    @GetMapping("/setAssignee/{taskId}/{userId}")
    public R<?> setAssignee(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                            @NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(() -> processTaskService.setAssignee(taskId, userId));
    }

    @ApiOperation(value = "设置认领任务人",notes = "声明任务的责任:指定的用户被指定为任务的受让人。" +
                    "与setAssignee(String, String)不同的是,\n" +
                    "如果任务已经有一个分配给它的用户，那么在这里执行检查。标识组件不检查用户是否已知。")
    @GetMapping("/claim/{taskId}/{userId}")
    public R<?> claim(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                      @NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(() -> processTaskService.claim(taskId, userId));
    }

    @ApiOperation(value = "任务进行移交",notes = "将此任务的所有权转移给另一个用户。标识组件不检查用户是否已知")
    @GetMapping("/setOwner/{taskId}/{userId}")
    public R<?> setOwner(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                         @NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(() -> processTaskService.setOwner(taskId, userId));
    }

    @ApiOperation(value = "完成当前人工任务",notes = "将任务标记为已完成，并继续流程执行")
    @PostMapping("/complete")
    public R<?> completeTask(@RequestBody TaskComplete request) {
        if (StrUtils.isAllNotBlank(request.getTaskId(), request.getProcessInstanceId())) {
            return R.error(AccessReason.PARAM_CHECK_EXCEPTION.exception("参数检查异常"));
        }
        return R.run(() -> processTaskService.completeTask(request));
    }

    @ApiOperation(value = "查询用户代办任务 ",notes = "查询用户代办任务")
    @PostMapping("/getTodoTaskPage")
    public R<?> getTodoTaskPage(@RequestBody TodoTaskRequest request) {
        if (!StrUtils.isNotAllBlank(request.getUserName())) {
            throw AccessReason.PARAM_CHECK_EXCEPTION.exception("查询用户代办任务 参数不能全为空");
        }
        return R.run(() -> processTaskService.getTodoTaskPage(request));
    }

    @ApiOperation(value = "查询已办任务",notes = "查询自己已近完成办理的任务")
    @GetMapping("/queryDoneTask/{userId}")
    public R<?> queryDoneTask(@NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(() -> processTaskService.queryDoneTask(userId));
    }

}
