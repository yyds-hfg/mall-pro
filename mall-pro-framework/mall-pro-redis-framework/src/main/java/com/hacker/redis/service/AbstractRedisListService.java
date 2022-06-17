package com.hacker.redis.service;

import com.hacker.redis.RedisCommonService;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/6/13 14:04
 * @Description:
 */
public abstract class AbstractRedisListService extends RedisCommonService {

    /**
     * 获取List结构中的属性
     */
    public abstract List<Object> lRange(String key, long start, long end);

    /**
     * 获取List结构的长度
     */
    public abstract Long lSize(String key);

    /**
     * 根据索引获取List中的属性
     */
    public abstract Object lIndex(String key, long index);

    /**
     * 向List结构中添加属性
     */
    public abstract  Long lPush(String key, Object value);

    /**
     * 向List结构中添加属性
     */
    public abstract Long lPush(String key, Object value, long time);

    /**
     * 向List结构中批量添加属性
     */
    public abstract  Long lPushAll(String key, Object... values);

    /**
     * 向List结构中批量添加属性
     */
    public abstract Long lPushAll(String key, Long time, Object... values);

    /**
     * 从List结构中移除属性
     */
    public abstract Long lRemove(String key, long count, Object value);
}
