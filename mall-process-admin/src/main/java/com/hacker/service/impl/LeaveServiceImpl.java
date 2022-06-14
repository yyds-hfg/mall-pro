package com.hacker.service.impl;

import com.hacker.domain.request.ProcessRequest;
import com.hacker.dto.LeaveDto;
import com.hacker.mapstruct.LeaveMap;
import com.hacker.po.Leave;
import com.hacker.mapper.LeaveMapper;
import com.hacker.service.LeaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hacker.service.ProcessInstanceService;
import com.hacker.service.ProcessTaskService;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 请假业务 服务实现类
 * </p>
 *
 * @author Zero
 * @since 2022-06-06
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {

    @Autowired
    private ProcessInstanceService processInstanceService;

    /**
     * 流程定义
     */
    public static final String PROCESS_KEY = "Process_1nduia6";

    @Override
    public void startLeave(LeaveDto leaveDto) {
        //插入请假业务表单
        Leave leave = LeaveMap.INSTANCE.toLeave(leaveDto);
        this.baseMapper.insert(leave);

        //调用Camunda流程引擎发起流程
        ProcessRequest processRequest = LeaveMap.INSTANCE.toProcessRequest(leaveDto);
        processRequest.setBusinessKey(leave.getId());
        ProcessInstanceDto processInstanceDto = processInstanceService.startProcessInstanceByKey(processRequest);

        //将流程实例添加到请假业务表中
        leave.setProcessInstanceId(processInstanceDto.getId());
        this.baseMapper.updateById(leave);

    }


}
