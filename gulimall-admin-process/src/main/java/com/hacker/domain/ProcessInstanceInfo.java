package com.hacker.domain;

import lombok.Builder;
import lombok.Data;
import org.camunda.bpm.engine.runtime.ProcessInstance;

/**
 * @Author: Zero
 * @Date: 2022/5/24 09:39
 * @Description:
 */
@Data
@Builder
public class ProcessInstanceInfo {
    /**
     * The id of the process definition of the process instance.
     */
    private String processDefinitionId;

    /**
     * The business key of this process instance.
     */
    private String businessKey;

    /**
     * The id of the root process instance associated with this process instance.
     */
    private String rootProcessInstanceId;

    /**
     * The id of the case instance associated with this process instance.
     */
    private String caseInstanceId;

    /**
     * returns true if the process instance is suspended
     */
    private boolean suspended;

    public static ProcessInstanceInfo getInstance(ProcessInstance processInstance) {
        return ProcessInstanceInfo.builder()
                .processDefinitionId(processInstance.getProcessDefinitionId())
                .businessKey(processInstance.getBusinessKey())
                .rootProcessInstanceId(processInstance.getRootProcessInstanceId())
                .caseInstanceId(processInstance.getCaseInstanceId())
                .suspended(processInstance.isSuspended())
                .build();
    }

}
