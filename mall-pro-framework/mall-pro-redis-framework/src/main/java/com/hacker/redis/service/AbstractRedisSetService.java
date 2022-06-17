package com.hacker.redis.service;

import com.hacker.redis.RedisCommonService;

import java.util.Set;

/**
 * @Author: Zero
 * @Date: 2022/6/13 17:08
 * @Description:
 */
public abstract class AbstractRedisSetService extends RedisCommonService {
    /**
     * 获取Set结构
     */
    public abstract Set<Object> sMembers(String key);

    /**
     * 向Set结构中添加属性
     */
    public abstract Long sAdd(String key, Object... values);

    /**
     * 向Set结构中添加属性
     */
    public abstract Long sAdd(String key, long time, Object... values);

    /**
     * 是否为Set中的属性
     */
    public abstract Boolean sIsMember(String key, Object value);

    /**
     * 获取Set结构的长度
     */
    public abstract Long sSize(String key);

    /**
     * 删除Set结构中的属性
     */
    public abstract Long sRemove(String key, Object... values);
}
