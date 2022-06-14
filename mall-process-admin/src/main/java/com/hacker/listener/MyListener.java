package com.hacker.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Zero
 * @Date: 2022/6/9 11:03
 * @Description:
 */
@Component
public class MyListener implements TaskListener, ExecutionListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        System.out.println("事件名字"+eventName);
    }

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String eventName = execution.getEventName();
        System.out.println("事件名字"+eventName);
        updateLeaveState();
    }

    private void updateLeaveState() {
        System.out.println("流程走完了");
        System.out.println("更改请假状态");
    }

}
