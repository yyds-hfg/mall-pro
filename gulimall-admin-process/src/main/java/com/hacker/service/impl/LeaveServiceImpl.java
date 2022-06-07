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

    @Autowired
    private ProcessTaskService processTaskService;

    @Override
    public void startLeave(LeaveDto leaveDto) {
        //保存信息
        Leave leave = LeaveMap.INSTANCE.toLeave(leaveDto);
        this.baseMapper.insert(leave);
        //发起流程
        ProcessRequest processRequest = LeaveMap.INSTANCE.toProcessRequest(leaveDto);
        processRequest.setBusinessKey(leave.getId());
        ProcessInstanceDto processInstanceDto = processInstanceService.startProcessInstanceByKey(processRequest);
        //更新信息
        leave.setProcessInstanceId(processInstanceDto.getId());
        this.baseMapper.updateById(leave);

    }


}
