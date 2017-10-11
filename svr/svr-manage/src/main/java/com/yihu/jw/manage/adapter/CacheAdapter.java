package com.yihu.jw.manage.adapter;

import com.yihu.jw.manage.adapter.cache.Cache;
import com.yihu.jw.manage.adapter.cache.MapCache;
import com.yihu.jw.manage.adapter.cache.RedisCache;
import com.yihu.jw.manage.adapter.cache.model.LoginCacheModel;
import com.yihu.jw.manage.adapter.cache.model.RoleCacheModel;
import com.yihu.jw.util.spring.SpringContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/3 0003.
 */
@Component
@Scope("singleton")
public class CacheAdapter{

    public final static String LOGIN = "login";//登陆模块的缓存是用户对象的LoginCacheModel字符串
    public final static String ROLE = "role";//url 缓存的是RoleCacheModel list的字符串
    /**
     * 1 内存 2 redis
     */
    @Value("${cache}")
    private String cacheKey;

    public String getCacheKey() {
        return cacheKey;
    }

    /**
     * 自动适配添加缓存
     *
     * @param module
     * @param key
     * @param value
     */
    public void setData(String module, String key, Object value) {
        String valStr = null;
        //对象转json字符串
        if(value instanceof List){
            valStr = JSONArray.fromObject(value).toString();
        }else{
            valStr =  JSONObject.fromObject(value).toString();
        }
        Cache cache = getCache();
        cache.setData(module, key, valStr);
    }

    /**
     * 根据模块,key值移除缓存
     * @param module
     * @param key
     * @throws Exception
     */
    public void removeData(String module,String key) throws Exception {
        Cache cache = getCache();
        cache.removeData(module, key);
    }

    /**
     * 自动适配获取缓存
     *
     * @param module
     * @param key
     * @return
     */
    public Object getData(String module, String key) {
        Cache cache = getCache();
        String joData = cache.getData(module, key);
        return getObject(module, joData);
    }

    /**
     *
     * @param module
     * @param pattern
     * @return
     */
    public Set<String> keys(String module,String pattern){
        Cache cache = getCache();
        return cache.keys(module,pattern);
    }



    /**
     * 获取缓存工具
     *
     * @return
     */
    private Cache getCache() {
        switch (cacheKey) {
            case "1": {
                return SpringContext.getService(MapCache.class);
            }
            case "2": {
                return SpringContext.getService(RedisCache.class);
            }
            default: {
                return SpringContext.getService(MapCache.class);
            }
        }
    }

    private Object getObject(String module, String joData) {
        switch (module) {
            case LOGIN: {
                return JSONObject.toBean(JSONObject.fromObject(joData), LoginCacheModel.class);
            }
            case ROLE: {
                List<RoleCacheModel> rcm=new ArrayList<>();
                JSONArray ja=JSONArray.fromObject(joData);
                ja.stream().forEach(one->{
                    rcm.add((RoleCacheModel) JSONObject.toBean(JSONObject.fromObject(one),RoleCacheModel.class));
                });
                return rcm;
            }
        }
        return null;
    }
}
