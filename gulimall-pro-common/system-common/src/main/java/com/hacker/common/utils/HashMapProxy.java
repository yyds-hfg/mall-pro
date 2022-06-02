package com.hacker.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zero
 * @Date: 2022/6/2 09:40
 * @Description:
 */
public class HashMapProxy<T,V> extends HashMap<T,V> {

    public HashMapProxy(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public HashMapProxy(int initialCapacity) {
        super(initialCapacity);
    }

    public HashMapProxy() {
    }

    public HashMapProxy(Map<? extends T, ? extends V> m) {
        super(m);
    }


    public HashMapProxy<T,V> putObj(T key, V value) {
        this.put(key, value);
        return this;
    }

}
