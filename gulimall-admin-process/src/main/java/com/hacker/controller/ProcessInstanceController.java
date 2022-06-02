package com.hacker.controller;

import com.hacker.common.exception.AccessReason;
import com.hacker.common.utils.StrUtils;
import com.hacker.domain.request.ProcessRequest;
import com.hacker.domain.request.RollbackProcessRequest;
import com.hacker.domain.request.TaskRequest;
import com.hacker.result.R;
import com.hacker.service.ProcessInstanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author: Zero
 * @Date: 2022/5/23 22:33
 * @Description:
 */
@RestController
@Api(tags = "流程实列控制器")
@RequestMapping("/process-instance")
public class ProcessInstanceController {

    @Autowired
    private ProcessInstanceService processInstanceService;

    @ApiOperation(value = "发起流程",notes = "流程发起")
    @PostMapping("/start")
    public R<?> startProcess(@NotNull @RequestBody ProcessRequest request) {
        return R.run(() -> processInstanceService.startProcessInstanceByKey(request));
    }

    @ApiOperation("流程撤回")
    @PostMapping("/cancelProcess")
    public R<?> cancelProcess(@NotNull @RequestBody TaskRequest request) {
        if (StrUtils.isAllNotBlank(request.getProcessInstId(), request.getTaskId(), request.getActicityDefKey())) {
            return R.error(AccessReason.PARAM_CHECK_EXCEPTION.exception("所需参数不能为空"));
        }
        return R.run(() -> processInstanceService.cancelProcess(request));
    }

    @ApiOperation("流程驳回")
    @PostMapping("/rollbackProcess")
    public R<?> rollbackProcess(@NotNull @RequestBody RollbackProcessRequest request) {
        if (StrUtils.isAllNotBlank(request.getRejectType(), request.getProcessInstId(), request.getTaskId())) {
            R.error(AccessReason.PARAM_CHECK_EXCEPTION.exception("所需参数不能为空"));
        }
        return R.run(() -> processInstanceService.rollbackProcess(request));
    }

    @ApiOperation("test")
    @GetMapping("/test/{processInstanceId}/{activityId}")
    public R<?> test(@PathVariable String activityId, @PathVariable String processInstanceId){
        return R.run(()->processInstanceService.test(processInstanceId,activityId));
    }

}
