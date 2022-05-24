package com.hacker.controller;

import com.hacker.domain.request.StartProcessRequest;
import com.hacker.domain.request.TaskComplete;
import com.hacker.result.R;
import com.hacker.service.ProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zero
 * @Date: 2022/5/23 22:33
 * @Description:
 */
@RestController
@Api(tags = "流程控制器")
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @ApiOperation(value = "根据流程定义的Key,启动流程")
    @PostMapping("/start")
    public R<?> startProcess(@RequestBody StartProcessRequest request) {
        return R.run(()->processService.startProcessInstanceByKey(request));
    }

    @ApiOperation("查询代办任务 ")
    @GetMapping("/queryTaskAgents/{businessKey}")
    public R<?> queryTaskAgents(@PathVariable String businessKey) {
        return R.run(()->processService.queryTaskAgents(businessKey));
    }

    @ApiOperation("6.完成当前人工任务")
    @PostMapping("/complete")
    public R complete(@RequestBody TaskComplete taskComplete) {
//        String businessId = taskComplete.getBusinessId();
//        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessId)
//                .singleResult();
//        taskService.complete(task.getId());
        return R.ok();
    }


}
