package com.hacker;

import cn.hutool.json.JSONUtil;
import com.hacker.common.exception.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.camunda.spin.json.SpinJsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.camunda.spin.Spin.*;
import static org.camunda.spin.DataFormats.*;

/**
 * @Author: Zero
 * @Date: 2022/5/24 10:00
 * @Description:
 */
@SpringBootTest
public class ProcessInstanceServiceTest {


    @Test
    public void test() {
        SpinJsonNode json = S("{\"customer\": \"Kermit\"}", json());
        System.out.println(json);
        String jsonStr = JSONUtil.toJsonStr(new Cat("1", "急啊急啊是"));
        SpinJsonNode jsonNode = JSON(jsonStr);
        System.out.println(jsonNode);
    }
    @Test
    public void test2() {
    }
}

@Data
@AllArgsConstructor
class Cat {
    private String id;
    private String name;
}
