package com.hacker.Test;

import org.junit.jupiter.api.Test;

/**
 * @Author: Zero
 * @Date: 2022/6/9 10:11
 * @Description:
 */
public class JavaFun {

    @Test
    public void test() {
        byte a = 127;
        byte b = 127;
//        b = a + b; // error : cannot convert from int to byte
        b += a; // ok
        System.out.println(b);

        System.out.println(3 * 0.1 == 0.3);
    }
}
