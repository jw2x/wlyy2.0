package com.yihu.jw.commnon.base.user;
/**
 * Created by LiTaohong on 2017/11/28.
 */
public class BaseUserContants {
    public static final String api_common = "/bases";
    public static final String api_baseRole = api_common+"/baseRole";
    public static final String api_employ = api_common+"/employee";
    public static final String api_baseMenu= api_common+"/employee";

    //角色常量
    public static class BaseRole{
        public static final String api_create="/";
        public static final String api_update="/";
        public static final String api_delete="/{id}";
        public static final String api_getById="/{id}";
        public static final String api_getListNoPage="/listNoPage";
        public static final String api_getList="/list";
        public static final String api_getChildren="/children/{id}";
    }

    //用户常量
    public static class Employee{
        public static final String api_create="/";
        public static final String api_update="/";
        public static final String api_delete="/{id}";
        public static final String api_getById="/{id}";
        public static final String api_getListNoPage="/listNoPage";
        public static final String api_getList="/list";
        public static final String api_getChildren="/children/{id}";

    }

    //菜单常量
    public static class BaseMenu{
        public static final String api_create="/";
        public static final String api_update="/";
        public static final String api_delete="/{id}";
        public static final String api_getById="/{id}";
        public static final String api_getListNoPage="/listNoPage";
        public static final String api_getList="/list";
        public static final String api_getChildren="/children/{id}";

    }
}
