package com.hacker.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: Zero
 * @Date: 2022/6/15 10:22
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MallTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallTestApplication.class,args);
    }
}
