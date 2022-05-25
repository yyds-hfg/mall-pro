package com.hacker.controller;

import com.hacker.domain.request.TaskComplete;
import com.hacker.result.R;
import com.hacker.service.ProcessTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ApiOperation(value = "查询流程当前任务 ")
    @GetMapping("/queryTaskAgents/{businessKey}")
    public R<?> queryTaskAgents(@NotBlank(message = "businessKey不能为空") @PathVariable String businessKey) {
        return R.run(()-> processTaskService.queryTaskAgents(businessKey));
    }

    @ApiOperation("设置任务候选人")
    @GetMapping("/setCandidateUser/{taskId}/{userId}")
    public R<?> setCandidateUser(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                                 @NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(()->processTaskService.setCandidateUser(taskId,userId));
    }

    @ApiOperation("设置和候选组")
    @GetMapping("/setCandidateGroup/{taskId}/{groupId}")
    public R<?> setCandidateGroup(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                                  @NotBlank(message = "groupId不能为空") @PathVariable String groupId) {
        return R.run(()->processTaskService.setCandidateGroup(taskId,groupId));
    }

    @ApiOperation("设置认领任务人")
    @GetMapping("/setAssignee/{taskId}/{userId}")
    public R<?> setAssignee(@NotBlank(message = "taskId不能为空") @PathVariable String taskId,
                            @NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(()->processTaskService.setAssignee(taskId,userId));
    }

    @ApiOperation("完成当前人工任务")
    @PostMapping("/complete")
    public R<?> completeTask(@Valid @RequestBody TaskComplete taskComplete) {
        return R.run(()-> processTaskService.completeTask(taskComplete));
    }

    @ApiOperation(value = "查询用户代办任务 ")
    @GetMapping("/getTodoTaskPage/{userId}")
    public R<?> getTodoTaskPage(@NotBlank(message = "userId不能为空") @PathVariable String userId) {
        return R.run(()-> processTaskService.getTodoTaskPage(userId));
    }

    @ApiOperation(value = "查询已办任务")
    @GetMapping("/queryDoneTask/{userId}")
    public R<?> queryDoneTask(@PathVariable String userId) {
        return R.run(()-> processTaskService.queryDoneTask(userId));
    }

}
