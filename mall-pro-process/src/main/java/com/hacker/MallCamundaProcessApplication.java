package com.hacker;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.event.PreUndeployEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;

/**
 * @Author: Zero
 * @Date: 2022/5/23 18:54
 * @Description: Camunda工作流
 */
@SpringBootApplication
@EnableProcessApplication
@EnableConfigurationProperties
public class MallCamundaProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCamundaProcessApplication.class, args);
    }

    /**
     * 使用部署回调
     *
     * @param event event
     */
    @EventListener
    public void onPostDeploy(PostDeployEvent event) {
    }

    @EventListener
    public void onPreUndeploy(PreUndeployEvent event) {
    }

}
