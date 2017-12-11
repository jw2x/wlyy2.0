package com.yihu.base.cache.support;

import com.yihu.base.cache.cache.CustomMapCache;
import com.yihu.base.cache.config.CacheKeyGenerator;
import com.yihu.base.cache.manager.CustomCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

@Component
public class MapCacheSupportImpl extends CacheSupportImpl implements CacheSupport {
    @Autowired
    private CustomCacheManager customCacheManager;

    @Override
    public void registerInvocation(Object invokedBean, Method invokedMethod, Class[] invocationParamTypes, Object[] invocationArgs, Set<String> annoationCacheNames, String cacheKey) {
        Collection<? extends Cache> caches = getCache(annoationCacheNames);
        CacheInvocation cacheInvocation = new CacheInvocation(CacheKeyGenerator.getCacheKey(),invokedBean,invokedMethod,invocationArgs,invocationParamTypes);

        for(Cache cache:caches){
            if(cache instanceof CustomMapCache){
                CustomMapCache customMapCache = (CustomMapCache)cache;

            }
        }
    }

    @Override
    public void refreshCacheByKey(String cacheName, String cacheKey) {
//        RedisTemplate redisTemplate = RedisTemplateUtils.getRedisTemplate(redisConnectionFactory);
//        CacheInvocation cacheInvocation = (CacheInvocation)redisTemplate.opsForValue().get(cacheKey);
//        if(null != cacheInvocation){
//            refreshCache(cacheInvocation,cacheName);
//        }

    }

    private void refreshCache(CacheInvocation cacheInvocation,String cacheName){
        Object computed = invoke(cacheInvocation);
        //获取缓存对象
        Cache cache = customCacheManager.getCache(cacheName);
        //更新缓存
        cache.put(cacheInvocation.getKey(),computed);

        CustomMapCache customMapCache = (CustomMapCache)cache;
    }

}
