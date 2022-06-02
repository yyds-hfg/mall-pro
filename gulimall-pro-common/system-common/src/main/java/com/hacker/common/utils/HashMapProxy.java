package com.hacker.common.utils;

import java.util.HashMap;

/**
 * @Author: Zero
 * @Date: 2022/6/2 09:40
 * @Description: 链式HashMap
 */
public class HashMapProxy<T,V> extends HashMap<T,V> {

    public HashMapProxy<T,V> putObj(T key, V value) {
        this.put(key, value);
        return this;
    }

    @Override
    public V put(T key, V value) {
        return super.put(key, value);
    }

}
