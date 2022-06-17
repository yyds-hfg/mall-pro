package com.hacker.redis.service;

import com.hacker.redis.RedisCommonService;

import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/6/13 17:13
 * @Description:
 */
public abstract class AbstractRedisHashService extends RedisCommonService {
    /**
     * 获取Hash结构中的属性
     */
    public abstract Object hGet(String key, String hashKey);

    /**
     * 向Hash结构中放入一个属性
     */
    public abstract  Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * 向Hash结构中放入一个属性
     */
    public abstract void hSet(String key, String hashKey, Object value);

    /**
     * 直接获取整个Hash结构
     */
    public abstract Map<Object, Object> hGetAll(String key);

    /**
     * 直接设置整个Hash结构
     */
    public abstract  Boolean hSetAll(String key, Map<String, Object> map, long time);

    /**
     * 直接设置整个Hash结构
     */
    public abstract void hSetAll(String key, Map<String, Object> map);

    /**
     * 删除Hash结构中的属性
     */
    public abstract void hDel(String key, Object... hashKey);

    /**
     * 判断Hash结构中是否有该属性
     */
    public abstract Boolean hHasKey(String key, String hashKey);

    /**
     * Hash结构中属性递增
     */
    public abstract Long hIncr(String key, String hashKey, Long delta);

    /**
     * Hash结构中属性递减
     */
    public abstract Long hDecr(String key, String hashKey, Long delta);
}
