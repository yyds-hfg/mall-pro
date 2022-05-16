package com.atguigu.common.config;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zero
 * @Date: 2022/5/16 10:19
 * @Description:
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 将mybatis底层的反射能力开放出来供应应用使用
     * @return ReflectorFactory
     */
    @Bean
    public ReflectorFactory reflectionFactory() {
        return new DefaultReflectorFactory();
    }

}
