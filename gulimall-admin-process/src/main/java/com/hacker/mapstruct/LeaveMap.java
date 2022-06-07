package com.hacker.mapstruct;

import com.hacker.domain.request.ProcessRequest;
import com.hacker.dto.LeaveDto;
import com.hacker.po.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: Zero
 * @Date: 2022/6/6 19:50
 * @Description:
 */
@Mapper
public interface LeaveMap {

    LeaveMap INSTANCE = Mappers.getMapper(LeaveMap.class);

    Leave toLeave(LeaveDto leaveDto);

    ProcessRequest toProcessRequest(LeaveDto leaveDto);
}
