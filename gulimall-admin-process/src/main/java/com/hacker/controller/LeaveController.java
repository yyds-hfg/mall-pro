package com.hacker.controller;

import com.hacker.common.result.R;
import com.hacker.dto.LeaveDto;
import com.hacker.service.LeaveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 请假业务 前端控制器
 * </p>
 *
 * @author Zero
 * @since 2022-06-06
 */
@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/startLeave")
    @ApiOperation(value = "发起请假流程")
    public R<?> startLeave(@RequestBody LeaveDto request) {
        return R.run(()->leaveService.startLeave(request));
    }

}

