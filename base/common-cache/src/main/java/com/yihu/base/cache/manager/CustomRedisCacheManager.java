package com.yihu.base.cache.manager;

import com.yihu.base.cache.util.ReflectionUtils;
import com.yihu.base.cache.util.SpringContextUtils;
import com.yihu.base.cache.cache.CustomRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

public class CustomRedisCacheManager extends RedisCacheManager implements CustomCacheManager{

    private RedisCacheManager redisCacheManager = null;

    @Autowired
    private DefaultListableBeanFactory beanFactory;

    private static final String SUPER_CACHEMAP = "cacheMap";

    private static final String SUPER_DYNAMIC = "dynamic";

    /**
     * 父类cacheNullValues字段
     */
    private static final String SUPER_CACHENULLVALUES = "cacheNullValues";

    /**
     * 父类updateCacheNames方法
     */
    private static final String SUPER_METHOD_UPDATECACHENAMES = "updateCacheNames";


    @Value("expire-time")
    private Long expireTime;

    @Value("refresh-time")
    private Long refreshTime;

    @Override
    public Long getExpireTime() {
        return expireTime;
    }

    @Override
    public Long getRefreshTime() {
        return refreshTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public CustomRedisCacheManager(RedisOperations redisOperations) {
        super(redisOperations);
    }

    public RedisCacheManager getInstance(){
        if(null == redisCacheManager){
            redisCacheManager = SpringContextUtils.getBean(RedisCacheManager.class);
        }
        return redisCacheManager;
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
                    cache = getMissingCache(cacheName,expireTime,refreshTime);
                    if(null != cache){
                        cache = decorateCache(cache);
                        cacheMap.put(cacheName,cache);

                        Class<?>[] parameterTypes = {String.class};
                        Object[] paramters = {cacheName};
                        ReflectionUtils.invokeMethod(getInstance(),SUPER_METHOD_UPDATECACHENAMES,parameterTypes,paramters);
                    }
                }
            }
        }
        return cache;
    }



    public CustomRedisCache getMissingCache(String cacheName, long expirationSecondTime, long preloadSecondTime) {
        Boolean dynamic = (Boolean) ReflectionUtils.getFieldValue(getInstance(),SUPER_DYNAMIC);
        Boolean cacheNullValues = (Boolean) ReflectionUtils.getFieldValue(getInstance(), SUPER_CACHENULLVALUES);
        return dynamic ? new CustomRedisCache(cacheName, (this.isUsePrefix() ? this.getCachePrefix().prefix(cacheName) : null),
                this.getRedisOperations(), expirationSecondTime, preloadSecondTime, cacheNullValues) : null;
    }


    /**
     * 获取过期时间
     * @return
     */
    @Override
    public Long getExpireTime(String cacheName, String[] cacheParams) {
        // 有效时间，初始化获取默认的有效时间
        Long expirationSecondTime = this.computeExpiration(cacheName);

        // 设置key有效时间
        if (cacheParams.length > 1) {
            String expirationStr = cacheParams[1];
            if (!StringUtils.isEmpty(expirationStr)) {
                // 支持配置过期时间使用EL表达式读取配置文件时间
                if (expirationStr.contains(MARK)) {
                    expirationStr = beanFactory.resolveEmbeddedValue(expirationStr);
                }
                expirationSecondTime = Long.parseLong(expirationStr);
            }
        }

        return expirationSecondTime;
    }

    /**
     * 获取自动刷新时间
     * @return
     */
    @Override
    public Long getAutoRefreshTime(String[] cacheParams) {
        // 自动刷新时间，默认是0
        Long refreshTime = 0L;
        // 设置自动刷新时间
        if (cacheParams.length > 2) {
            String preloadStr = cacheParams[2];
            if (!StringUtils.isEmpty(preloadStr)) {
                // 支持配置刷新时间使用EL表达式读取配置文件时间
                if (preloadStr.contains(MARK)) {
                    preloadStr = beanFactory.resolveEmbeddedValue(preloadStr);
                }
                refreshTime = Long.parseLong(preloadStr);
            }
        }
        return refreshTime;
    }

}
