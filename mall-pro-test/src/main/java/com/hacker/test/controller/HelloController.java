package com.hacker.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Zero
 * @Date: 2022/6/15 10:23
 * @Description:
 */
@RestController
public class HelloController {

    @GetMapping("/get")
    public String get() {
        return "Hello!";
    }

}
