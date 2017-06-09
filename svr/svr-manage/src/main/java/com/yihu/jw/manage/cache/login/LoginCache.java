package com.yihu.jw.manage.cache.login;

import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.restmodel.manage.ManageUserVO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/9.
 * 目前登陆的信息先放在内存中
 * 后续如果做集群管理应该放入redis中，并且需要考虑并发问题
 */
public class LoginCache {
    private static Map<String,ManageUserVO> user =new HashMap<>();

    public static ManageUserVO getCache(String key) {
        return user.get(key);
    }

    public static void addCache(String key,ManageUserVO manageUserVO) {
        user.put(key,manageUserVO);
    }
}
