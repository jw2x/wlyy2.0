package com.yihu.jw.manage.adapter.cache;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/3 0003.
 */
@Component
@Scope("singleton")
public class MapCache implements Cache {
    /**
     * 缓存的map,key是module value是对应model的缓存
     */
    private static Map<String, Map<String, String>> cacha = new HashMap<>();

    @Override
    public void setData(String module, String key, String value) {
        //获取map
        Map<String, String> map=cacha.get(module);
        if(map==null){
            map=new HashMap<>();
            cacha.put(module,map);
        }
        //放入缓存
        map.put(key,value);
    }

    @Override
    public String getData(String module, String key) {
        Map<String, String> map=cacha.get(module);
        if(map==null){
            return null;
        }
        return map.get(key);
    }


    /**
     * 通过正则,获取key值
     * @param module
     * @param pattern
     * @return
     */
    @Override
    public Set<String> keys(String module,String pattern) {
        Map<String, String> map=cacha.get(module);
        if(map==null){
            return null;
        }
        Set<String> keys = map.keySet();
        Set<String> newKeys = new HashSet<String>();
        for(String key:keys){
            if(Pattern.matches(pattern, key)){
                newKeys.add(key);
            }
        }
        return newKeys;
    }

    @Override
    public void removeData(String module, String key) throws Exception {
        Map<String, String> map=cacha.get(module);
        if(map==null){
            throw new Exception("this "+module+" does not exist cache");
        }
        map.remove(key);
    }
}
