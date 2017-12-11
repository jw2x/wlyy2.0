package com.yihu.base.cache.cache;

import com.yihu.base.cache.config.CacheKeyGenerator;
import com.yihu.base.cache.lock.CacheLock;
import com.yihu.base.cache.util.ThreadTaskUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheElement;
import org.springframework.data.redis.cache.RedisCacheKey;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class CustomMapCache extends ConcurrentMapCache{

    @Value("expire-time")
    private Long expireTime;

    @Value("refresh-time")
    private Long refreshTime;

    public CustomMapCache(String name) {
        super(name);
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public Long getRefreshTime() {
        return refreshTime;
    }

    public CustomMapCache(String name,Long expireTime, Long refreshTime){
        super(name);
        this.expireTime = expireTime;
        this.refreshTime = refreshTime;
    }


    /**
     * 重写get方法，获取到缓存后再次取缓存剩余的时间，如果时间小余我们配置的刷新时间就手动刷新缓存。
     * 为了不影响get的性能，启用后台线程去完成缓存的刷。
     * 并且只放一个线程去刷新数据。
     *
     * @param key
     * @return
     */
    @Override
    public ValueWrapper get(Object key) {

        ValueWrapper valueWrapper = this.get(key);

        if (null != valueWrapper) {
            // 刷新缓存数据
        }
        return valueWrapper;
    }

}
