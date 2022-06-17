package com.hacker.redis.service;

import com.hacker.redis.RedisCommonService;

/**
 * @Author: Zero
 * @Date: 2022/6/13 12:21
 * @Description: Redis字符串类型(String)操作抽象类
 */
public abstract class AbstractRedisStringService extends RedisCommonService {
    /**
     * 保存属性
     */
    public abstract void set(String key, Object value, long time);

    /**
     * 保存属性
     */
    public abstract void set(String key, Object value);

    /**
     * 取值并赋值
     */
    public abstract Object getSet(String key,Object value);

    /**
     * 如果key不存在，设置key以保存字符串值
     * @param key
     * @param value
     */
    public abstract Boolean setNx(String key,Object value);

    /**
     * 向字符串尾部追加值
     * @param key
     * @param value
     */
    public abstract Integer append(String key,String value);

    /**
     * 获取字符串长度
     * @param key
     * @return
     */
    public abstract Long strlen(String key);

    /**
     * 按delta递增
     */
    public abstract Long incr(String key, long delta);

    /**
     * 按delta递减
     */
    public abstract Long decr(String key, long delta);

}
