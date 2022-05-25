package com.hacker.feign;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/5/25 20:52
 * @Description:
 */
@Component
@Slf4j
public class SayHello implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Map<String, Object> variables = execution.getVariables();
        for (String s : variables.keySet()) {
            System.out.println(s);
        }
        log.info("流程进入表达式");
    }
}
