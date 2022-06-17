package com.hacker.redis.service.impl;

import com.hacker.redis.service.AbstractRedisSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author: Zero
 * @Date: 2022/6/13 17:09
 * @Description:
 */
@Service
public class RedisSetService extends AbstractRedisSetService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取Set结构
     *
     * @param key
     */
    @Override
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 向Set结构中添加属性
     *
     * @param key
     * @param values
     */
    @Override
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 向Set结构中添加属性
     *
     * @param key
     * @param time
     * @param values
     */
    @Override
    public Long sAdd(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    /**
     * 是否为Set中的属性
     *
     * @param key
     * @param value
     */
    @Override
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获取Set结构的长度
     *
     * @param key
     */
    @Override
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 删除Set结构中的属性
     *
     * @param key
     * @param values
     */
    @Override
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

}
