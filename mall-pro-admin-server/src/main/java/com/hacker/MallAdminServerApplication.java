package com.hacker;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zero
 * @Date: 2022/6/20 16:19
 * @Description:
 */
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class MallAdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminServerApplication.class, args);
    }
}
