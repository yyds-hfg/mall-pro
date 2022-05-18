package com.hacker.coupon.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Author: Zero
 * @Date: 2022/5/16 09:12
 * @Description:
 */
/**
 * @ConfigurationProperties注解
 *      将配置文件熟悉转化为Bean对象使用
 * @EnableConfigurationProperties作用:
 *      使这个单独的@ConfigurationProperties(prefix = "spring.cache")注解生效
 * @ConfigurationProperties + @Configuration = @EnableConfigurationProperties({CacheProperties.class})
 */
@EnableConfigurationProperties({CacheProperties.class})
@Configuration
public class CacheConfiguration {

    /**
     *
     * @param cacheProperties
     * @return
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager(CacheProperties cacheProperties) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCacheNames(cacheProperties.getCacheNames());
        caffeineCacheManager.setCacheSpecification(cacheProperties.getCaffeine().getSpec());
        return caffeineCacheManager;
    }

}
