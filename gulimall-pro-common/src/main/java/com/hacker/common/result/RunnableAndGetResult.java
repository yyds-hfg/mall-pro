package com.hacker.common.result;

/**
 * @Author: Zero
 * @Date: 2022/4/28 21:38
 * @Description:
 */
@FunctionalInterface
public interface RunnableAndGetResult<P>{
    P run();
}
