package com.hacker.redis.service.impl;

import com.hacker.redis.service.AbstractRedisStringService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Zero
 * @Date: 2022/6/13 12:23
 * @Description: Redis字符串类型(String)操作实现类
 */
@Service
public class RedisStringService extends AbstractRedisStringService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存属性
     *
     * @param key key
     * @param value value
     * @param time 过期时间
     */
    @Override
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 保存属性
     *
     * @param key key
     * @param value value
     */
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置key的值并返回其原来的值。
     *
     * @param key
     */
    @Override
    public Object getSet(String key,Object value) {
        return redisTemplate.opsForValue().getAndSet(key,value);
    }

    /**
     * 如果key不存在，设置key以保存字符串值
     * @param key
     * @param value
     */
    @Override
    public Boolean setNx(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    /**
     * 向字符串尾部追加值
     * @param key key
     * @param value value
     */
    @Override
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    /**
     * 获取字符串长度
     *
     * @param key key
     * @return
     */
    @Override
    public Long strlen(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * 按delta递增
     *
     * @param key
     * @param delta
     */
    @Override
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /**
     * 按delta递减
     *
     * @param key
     * @param delta
     */
    @Override
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }


}
