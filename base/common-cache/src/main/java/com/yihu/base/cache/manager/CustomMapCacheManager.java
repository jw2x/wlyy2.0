package com.yihu.base.cache.manager;

import com.yihu.base.cache.cache.CustomMapCache;
import com.yihu.base.cache.cache.CustomRedisCache;
import com.yihu.base.cache.util.ReflectionUtils;
import com.yihu.base.cache.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CustomMapCacheManager extends ConcurrentMapCacheManager implements CustomCacheManager{

    @Value("expire-time")
    private Long expireTime;

    @Value("refresh-time")
    private Long refreshTime;

    @Autowired
    private ConcurrentMapCacheManager concurrentMapCacheManager;

    private static final String SUPER_CACHEMAP = "cacheMap";

    public ConcurrentMapCacheManager getInstance(){
        if(null == concurrentMapCacheManager){
            concurrentMapCacheManager = SpringContextUtils.getBean(ConcurrentMapCacheManager.class);
        }
        return concurrentMapCacheManager;
    }

    @Override
    public Long getExpireTime() {
        return null;
    }

    @Override
    public Long getRefreshTime() {
        return null;
    }

    @Override
    public Long getExpireTime(String cacheName, String[] cacheParams) {
        if(cacheParams.length > 1){
            this.expireTime = Long.parseLong(cacheParams[1]);
        }
        return expireTime;
    }

    @Override
    public Long getAutoRefreshTime(String[] cacheParams) {
        if(cacheParams.length > 2){
            this.refreshTime = Long.parseLong(cacheParams[2]);
        }
        return refreshTime;
    }

    @Override
    public Cache getCache(String name){
        String[] cacheParams = name.split(CustomCacheManager.SEPARATOR);
        String cacheName = cacheParams[0];
        if(StringUtils.isEmpty(cacheName)){
            return null;
        }
        //注解里面的过期时间覆盖默认的过期时间
        long expireTime = getExpireTime(name,cacheParams);

        //注解里面的刷新时间
        long refreshTime = getAutoRefreshTime(cacheParams);

        Object obj =  ReflectionUtils.getFieldValue(getInstance(),SUPER_CACHEMAP);
        if(null != obj && obj instanceof ConcurrentHashMap){
            ConcurrentHashMap<String,Cache> cacheMap = (ConcurrentHashMap<String,Cache>)obj;
            return getCache(name,expireTime,refreshTime,cacheMap);
        }else{
            return super.getCache(name);
        }
    }

    /**
     * @param cacheName
     * @param expireTime
     * @param refreshTime
     * @param cacheMap
     * @return
     */
    public Cache getCache(String cacheName, long expireTime, long refreshTime, ConcurrentHashMap<String,Cache> cacheMap){
        Cache cache = cacheMap.get(cacheName);
        if(null != cache){
            return cache;
        }else {
            synchronized (cacheMap){
                cache = cacheMap.get(cacheName);
                if(null == cache){
                    cache = createConcurrentMapCache(cacheName,expireTime,refreshTime);
                }
            }
        }
        return cache;
    }

    public CustomMapCache createConcurrentMapCache(String cacheName, long expirationTime, long refreshTime) {
        return new CustomMapCache(cacheName,expirationTime,refreshTime);
    }

}
