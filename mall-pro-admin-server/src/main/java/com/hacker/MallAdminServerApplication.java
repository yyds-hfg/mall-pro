package com.hacker;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: Zero
 * @Date: 2022/6/20 16:19
 * @Description:
 */
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class MallAdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminServerApplication.class, args);
    }
}
