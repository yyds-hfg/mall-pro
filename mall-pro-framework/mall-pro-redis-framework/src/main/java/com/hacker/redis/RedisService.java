package com.hacker.redis;

/**
 * @Author: Zero
 * @Date: 2022/6/13 00:10
 * @Description: Redis操作顶级接口
 */
public interface RedisService {

    /**
     * 设置过期时间
     */
    Boolean expire(String key, long time);

    /**
     * 获取过期时间
     */
    Long getExpire(String key);

    /**
     * 判断是否有该属性
     */
    Boolean hasKey(String key) ;

}

