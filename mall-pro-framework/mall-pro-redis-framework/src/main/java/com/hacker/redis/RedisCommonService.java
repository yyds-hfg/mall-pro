package com.hacker.redis;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Zero
 * @Date: 2022/6/13 12:18
 * @Description: Redis公共操作抽象类
 */
public abstract class RedisCommonService implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置过期时间
     */
    @Override
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 获取过期时间
     */
    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断是否有该属性
     */
    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

}
