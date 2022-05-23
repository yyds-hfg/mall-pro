package com.hacker.ware.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.hacker.ware.dao")
@Configuration
public class WareMyBatisConfig {

    @Bean
    public PaginationInnerInterceptor getPaginationInnerInterceptor() {
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        innerInterceptor.setDbType(DbType.MYSQL);  //设置数据库类型
        //innerInterceptor.setMaxLimit();  //设置单页条数限制
        return innerInterceptor;
    }
}
