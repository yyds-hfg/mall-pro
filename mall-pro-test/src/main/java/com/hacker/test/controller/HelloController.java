package com.hacker.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

/**
 * @Author: Zero
 * @Date: 2022/6/15 10:23
 * @Description:
 */
@RestController
public class HelloController {
    @Resource
    private Docket docket;

    @GetMapping("/get")
    public String get() {
        System.out.println(docket.toString());
        return "Hello!";
    }
}
