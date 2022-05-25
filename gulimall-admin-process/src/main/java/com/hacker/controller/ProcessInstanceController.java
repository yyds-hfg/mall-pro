package com.hacker.controller;

import com.hacker.domain.request.ProcessRequest;
import com.hacker.result.R;
import com.hacker.service.ProcessInstanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: Zero
 * @Date: 2022/5/23 22:33
 * @Description:
 */
@RestController
@Api(tags = "流程实列控制器")
@RequestMapping("/process")
public class ProcessInstanceController {

    @Autowired
    private ProcessInstanceService processInstanceService;

    @ApiOperation(value = "发起流程")
    @PostMapping("/start")
    public R<?> startProcess(@Valid @RequestBody ProcessRequest request) {
        return R.run(()-> processInstanceService.startProcessInstanceByKey(request));
    }

}
