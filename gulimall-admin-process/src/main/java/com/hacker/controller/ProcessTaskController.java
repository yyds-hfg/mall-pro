package com.hacker.controller;

import com.hacker.domain.request.TaskComplete;
import com.hacker.result.R;
import com.hacker.service.ProcessTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


    @ApiOperation("完成当前人工任务")
    @PostMapping("/complete")
    public R<?> completeTask(@Valid @RequestBody TaskComplete taskComplete) {
        return R.run(()-> processTaskService.completeTask(taskComplete));
    }

}
