package io.github.carrothole.carrot.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存工具类
 *
 * @author moon
 * @since 0.0.1
 */
public class CacheUtil {

    private static final Map<String, CacheValue> CACHE_MAP = new HashMap<>();

    /**
     * 添加缓存
     * @param key 缓存key
     * @param value 缓存只
     * @param keepTime 保留时长 ms
     */
    public static void addCache(String key, Object value, long keepTime){
        CACHE_MAP.put(key,new CacheValue(value,System.currentTimeMillis()+keepTime));
    }

    /**
     * 获取缓存
     *
     * @param key 缓存key
     * @return {@link CacheValue}
     */
    public static CacheValue getCache(String key){
      return CACHE_MAP.get(key);
    }


    /**
     * 删除缓存
     * @param key 缓存key
     * @return {@link CacheValue}
     */
    public static CacheValue removeCache(String key){
        return CACHE_MAP.remove(key);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CacheValue {
        /**
         * 缓存值
         */
        private Object value;
        /**
         * 到期时间
         */
        private long expireTime;
    }
}
