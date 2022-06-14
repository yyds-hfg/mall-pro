package com.hacker.service;

import com.hacker.domain.request.ProcessRequest;
import com.hacker.dto.LeaveDto;
import com.hacker.po.Leave;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 请假业务 服务类
 * </p>
 *
 * @author Zero
 * @since 2022-06-06
 */
public interface LeaveService extends IService<Leave> {

    void startLeave(LeaveDto request);
}
