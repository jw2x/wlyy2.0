package com.yihu.jw.cache;

import java.util.Set;

/**
 * Created by LiTaohong on 2017/12/04.
 */
public interface Cache {
    void setData(String module, String key, String value);

    String getData(String module, String key);

    Set<String> keys(String module, String pattern);

    void removeData(String module, String key) throws Exception;
}
