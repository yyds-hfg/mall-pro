package com.hacker.redis.service.impl;

import com.hacker.redis.service.AbstractRedisListService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/6/13 14:05
 * @Description:
 */
@Service
public class RedisListService extends AbstractRedisListService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取List结构中的属性
     *
     * @param key key
     * @param start start
     * @param end end
     */
    @Override
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取List结构的长度
     *
     * @param key
     */
    @Override
    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 根据索引获取List中的属性
     *
     * @param key
     * @param index
     */
    @Override
    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 向List结构中添加属性
     *
     * @param key
     * @param value
     */
    @Override
    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 向List结构中添加属性
     *
     * @param key
     * @param value
     * @param time
     */
    @Override
    public Long lPush(String key, Object value, long time) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return index;
    }

    /**
     * 向List结构中批量添加属性
     *
     * @param key
     * @param values
     */
    @Override
    public Long lPushAll(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 向List结构中批量添加属性
     *
     * @param key
     * @param time
     * @param values
     */
    @Override
    public Long lPushAll(String key, Long time, Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, time);
        return count;
    }

    /**
     * 从List结构中移除属性
     *
     * @param key key
     * @param count count
     * @param value value
     */
    @Override
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }


}
