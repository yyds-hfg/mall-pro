package com.hacker.service.impl;

import com.hacker.domain.request.ProcessRequest;
import com.hacker.domain.request.StartProcessRequest;
import com.hacker.service.ProcessInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/5/24 09:37
 * @Description:
 */
@Service
@Slf4j
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    @Transactional
    public ProcessInstanceDto startProcessInstanceByKey(ProcessRequest request) {
        log.info("开启一个流程----------------");
        ProcessInstance processInstance = null;
        Map<String, Object> variables = request.getVariables();
        variables.put("starter",request.getStarter());
        if (StringUtils.isNotBlank(request.getProcessDefId())) {
            processInstance = runtimeService.startProcessInstanceById(request.getProcessDefId(),
                    request.getBusinessKey(), variables);
        }
        if (StringUtils.isNotBlank(request.getProcessDefKey())) {
            processInstance = runtimeService.startProcessInstanceByKey(request.getProcessDefKey(),
                    request.getBusinessKey(),variables);
        }

        Assert.isTrue(processInstance!=null,"流程启动失败");
        log.info(String.format("流程启动成功,流程实列Id [{%s}]",processInstance.getProcessInstanceId()));
        return ProcessInstanceDto.fromProcessInstance(processInstance);
    }

}
