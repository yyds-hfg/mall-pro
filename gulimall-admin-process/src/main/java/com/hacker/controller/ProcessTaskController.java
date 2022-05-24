package com.hacker.controller;

import com.hacker.domain.request.TaskComplete;
import com.hacker.result.R;
import com.hacker.service.ProcessTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public R<?> queryTaskAgents(@PathVariable String businessKey) {
        return R.run(()-> processTaskService.queryTaskAgents(businessKey));
    }

    @ApiOperation("设置任务候选人")
    @GetMapping("/setCandidateUser/{taskId}/{userId}")
    public R<?> setCandidateUser(@PathVariable String taskId, @PathVariable String userId) {
        return R.run(()->processTaskService.setCandidateUser(taskId,userId));
    }

    @ApiOperation("设置和候选组")
    @GetMapping("/setCandidateGroup/{taskId}/{groupId}")
    public R<?> setCandidateGroup(@PathVariable String taskId, @PathVariable String groupId) {
        return R.run(()->processTaskService.setCandidateGroup(taskId,groupId));
    }

    @ApiOperation("设置认领任务人")
    @GetMapping("/setAssignee/{taskId}/{userId}")
    public R<?> setAssignee(@PathVariable String taskId, @PathVariable String userId) {
        return R.run(()->processTaskService.setAssignee(taskId,userId));
    }

    @ApiOperation("完成当前人工任务")
    @PostMapping("/complete")
    public R<?> completeTask(@Valid @RequestBody TaskComplete taskComplete) {
        return R.run(()-> processTaskService.completeTask(taskComplete));
    }

}
