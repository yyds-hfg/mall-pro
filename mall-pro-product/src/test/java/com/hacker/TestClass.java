package com.hacker;

/**
 * @Author: Zero
 * @Date: 2022/6/21 16:20
 * @Description:
 */
public class TestClass {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(((int) ((Math.random() + 1) * 10000)));
        }
    }
}
