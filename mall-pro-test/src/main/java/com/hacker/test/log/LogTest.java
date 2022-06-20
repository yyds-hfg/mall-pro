package com.hacker.test.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @Author: Zero
 * @Date: 2022/6/20 14:41
 * @Description:
 */
@Slf4j
public class LogTest {

    @Test
    public void test1() {
        int j =0;
        try {
           int i = 10/j;
        } catch (Exception e) {
            //SL4FJ占位符不像MessageFormat需要指定index，而是直接使用{}即可
            log.error("除数不能等于 {} ,异常信息 {}",j,e.getMessage());
        }
    }
}
