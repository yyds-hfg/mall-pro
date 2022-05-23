package com.hacker.controller;

import com.hacker.common.result.R;
import com.hacker.domain.StartProcessRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zero
 * @Date: 2022/5/23 22:33
 * @Description:
 */
@RestController
@Api("")
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private RuntimeService runtimeService;


    @ApiOperation("根据流程定义的Key,启动流程")
    @PostMapping("/start")
    public R<?> startProcess(@RequestBody StartProcessRequest request) {
        return R.run(()->runtimeService
                .startProcessInstanceByKey(request.getProcessKey(),request.getBusinessKey(),request.getVars()));
    }


}
