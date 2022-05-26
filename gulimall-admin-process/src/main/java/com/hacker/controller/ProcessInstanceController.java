package com.hacker.controller;

import com.hacker.common.exception.AccessReason;
import com.hacker.domain.request.ProcessRequest;
import com.hacker.domain.request.TaskRequest;
import com.hacker.result.R;
import com.hacker.service.ProcessInstanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public R<?> startProcess(@RequestBody ProcessRequest request) {
        return R.run(()-> processInstanceService.startProcessInstanceByKey(request));
    }

    @ApiOperation("流程撤回")
    @PostMapping("/cancelProcess")
    public R<?> cancelProcess(@RequestBody TaskRequest request) {
        if (StringUtils.isNoneBlank(request.getProcessInstId(),request.getTaskId(),request.getTaskDefKey())) {
            throw AccessReason.PARAM_CHECK_EXCEPTION.exception("所需参数不能为空");
        }
        return R.run(()->processInstanceService.cancelProcess(request));
    }

    @ApiOperation("流程驳回")
    @PostMapping("/rollbackProcess")
    public R<?> rollbackProcess(@RequestBody TaskRequest request) {
        if (StringUtils.isNoneBlank(request.getRejectType(),request.getProcessInstId(),
                request.getToActId(),request.getTaskId())) {
            throw AccessReason.PARAM_CHECK_EXCEPTION.exception("所需参数不能为空");
        }
        return R.run(()->processInstanceService.rollbackProcess(request));
    }


}
