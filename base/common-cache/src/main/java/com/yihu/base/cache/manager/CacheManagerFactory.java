package com.yihu.base.cache.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;


public class CacheManagerFactory {

    @Value("cache")
    private String caceType;

    public CacheManager getCacheManager(){
        CacheManager cacheManager = null;
        if(StringUtils.isEmpty(caceType)){
            return cacheManager;
        }
        switch (Integer.parseInt(caceType)){
            case 1:
                cacheManager = new CustomMapCacheManager();
                break;
            case 2:
                cacheManager = new CustomRedisCacheManager(new RedisTemplate());
                break;
        }
        return cacheManager;
    }
}
