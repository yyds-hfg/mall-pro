package com.hacker.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zero
 * @Date: 2022/5/16 10:19
 * @Description: mybatis-plus配置
 */
@Configuration
@MapperScan(value = "${mybatis-plus.base-package}",annotationClass = Mapper.class)
public class MybatisPlusConfig {

    /**
     * 将mybatis底层的反射能力开放出来供应应用使用
     *
     * @return ReflectorFactory
     */
    @Bean(name = "reflectorFactory")
    public ReflectorFactory reflectionFactory() {
        return new DefaultReflectorFactory();
    }

    /**
     * MybatisPlus分页插件，自动识别数据库类型
     *
     * @return PaginationInnerInterceptor
     */
    @Bean
    public PaginationInnerInterceptor getPaginationInnerInterceptor() {
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        innerInterceptor.setDbType(DbType.MYSQL);  //设置数据库类型
        //innerInterceptor.setMaxLimit();  //设置单页条数限制
        return innerInterceptor;
    }

}
