package com.yihu.jw.manage.adapter.cache;

import java.util.Set;

/**
 * Created by Administrator on 2017/7/4 0004.
 */
public interface Cache {
    void setData(String module, String key, String value);

    String getData(String module, String key);

    Set<String> keys(String module,String pattern);

    void removeData(String module, String key) throws Exception;
}
