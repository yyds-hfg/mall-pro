package com.hacker;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.event.PreUndeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

/**
 * @Author: Zero
 * @Date: 2022/5/23 18:54
 * @Description:
 */
@SpringBootApplication
@EnableProcessApplication
@EnableFeignClients
public class CamundaProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundaProcessApplication.class, args);
    }

    /**
     * 使用部署回调
     *
     * @param event
     */
    @EventListener
    public void onPostDeploy(PostDeployEvent event) {
    }

    @EventListener
    public void onPreUndeploy(PreUndeployEvent event) {
    }

}
