package com.yihu.jw.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by LiTaohong on 2017/12/04.
 */
@Component
@Scope("singleton")
public class RedisCache implements Cache {
    private static String redis_pre = "svr-base";
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void setData(String module, String key, String value) {
        //获取key
        String redisKey=getKey(module,key);
        //把数据放入redis中
        redisTemplate.opsForValue().set(redisKey,value);
    }



    @Override
    public String getData(String module, String key) {
        //获取key
        String redisKey=getKey(module,key);
        //从redis中获取数据
        return redisTemplate.opsForValue().get(redisKey);
    }

    @Override
    public Set<String> keys(String module,String pattern) {
        pattern = pattern.replaceAll("\\.", "");
        String redisKey = getKey(module,pattern);
        Set<String> redisKeys = redisTemplate.keys(redisKey);
        Set<String> keys = new HashSet<>();
        for(String key:redisKeys){
            key = key.substring(8+module.length());
            keys.add(key);
        }
        return keys;
    }

    @Override
    public void removeData(String module, String key) throws Exception {
        String redisKey = getKey(module, key);
        redisTemplate.delete(redisKey);//根据key删除缓存
    }

    /**
     * 获取redis中的key
     * @param module
     * @param key
     * @return
     */
    private String getKey(String module, String key) {
        return new StringBuffer(redis_pre+":"+module+":"+key).toString();
    }
}
