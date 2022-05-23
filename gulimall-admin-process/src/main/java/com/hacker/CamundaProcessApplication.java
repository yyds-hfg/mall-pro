package com.hacker;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import java.util.UUID;

/**
 * @Author: Zero
 * @Date: 2022/5/23 18:54
 * @Description:
 */
@SpringBootApplication
@EnableProcessApplication
public class CamundaProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundaProcessApplication.class,args);
    }

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @EventListener
    public void processPostDeploy(PostDeployEvent event) {
        System.out.println("部署流程");
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("bpmn/start.bpmn").deploy();
        System.out.println("部署时间为:"+deployment.getDeploymentTime());
        System.out.println("流程部署Id为"+deployment.getId());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_07v2a2w");
        System.out.println("流程实例Id:"+processInstance.getRootProcessInstanceId());
        System.out.println("流程业务Key"+processInstance.getBusinessKey());
    }

}
