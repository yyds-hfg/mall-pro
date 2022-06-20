package com.hacker.redis.service.impl;

import com.hacker.redis.service.AbstractRedisHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/6/13 17:14
 * @Description:
 */
@Service
public class RedisHashService extends AbstractRedisHashService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取Hash结构中的属性
     *
     * @param key
     * @param hashKey
     */
    @Override
    public Object hGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 向Hash结构中放入一个属性
     *
     * @param key
     * @param hashKey
     * @param value
     * @param time
     */
    @Override
    public Boolean hSet(String key, String hashKey, Object value, long time) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }

    /**
     * 向Hash结构中放入一个属性
     *
     * @param key
     * @param hashKey
     * @param value
     */
    @Override
    public void hSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 直接获取整个Hash结构
     *
     * @param key
     */
    @Override
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 直接设置整个Hash结构
     *
     * @param key
     * @param map
     * @param time
     */
    @Override
    public Boolean hSetAll(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }

    /**
     * 直接设置整个Hash结构
     *
     * @param key
     * @param map
     */
    @Override
    public void hSetAll(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 删除Hash结构中的属性
     *
     * @param key
     * @param hashKey
     */
    @Override
    public void hDel(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 判断Hash结构中是否有该属性
     *
     * @param key
     * @param hashKey
     */
    @Override
    public Boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * Hash结构中属性递增
     *
     * @param key
     * @param hashKey
     * @param delta
     */
    @Override
    public Long hIncr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * Hash结构中属性递减
     *
     * @param key
     * @param hashKey
     * @param delta
     */
    @Override
    public Long hDecr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }
}
