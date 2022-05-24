package com.hacker.config;

import org.camunda.bpm.spring.boot.starter.rest.CamundaJerseyResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * @Author: Zero
 * @Date: 2022/5/23 22:09
 * @Description:
 */
@Component
@ApplicationPath("/engine-rest")
public class JerseyConfig extends CamundaJerseyResourceConfig {

    @Override
    protected void registerAdditionalResources() {
        this.register("");
    }

}
