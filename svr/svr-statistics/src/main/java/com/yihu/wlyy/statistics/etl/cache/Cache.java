package com.yihu.wlyy.statistics.etl.cache;

import com.yihu.wlyy.statistics.vo.DataModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/7/11.
 */
public class Cache {
    private static Map<String, List<DataModel>> cache = new HashMap<>();

    public static void addCache(String key, List<DataModel> data) {
        cache.put(key, data);
    }


    public static List<DataModel> getCache(String key) {
        return cache.get(key);
    }


    public static void cleanCache(){
        cache.clear();
    }
}
