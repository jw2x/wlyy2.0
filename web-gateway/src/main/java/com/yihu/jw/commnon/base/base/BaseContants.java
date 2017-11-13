package com.yihu.jw.commnon.base.base;

/**
 * Created by chenweida on 2017/5/19.
 * 基础模块的静态变量
 */
public class BaseContants {

    public static final String api_common = "/bases";

    //功能模块常量
    public static class Function{
        public static final String api_create="/function";
        public static final String api_update="/function";
        public static final String api_delete="/function/{id}";
        public static final String api_getById="/function/{id}";
        public static final String api_getListNoPage="/function/listNoPage";
        public static final String api_getList="/function/list";
        public static final String api_getChildren="/function/children/{id}";
    }

    //模块常量
    public static class Module{
        public static final String api_create="/module";
        public static final String api_update="/module";
        public static final String api_delete="/module/{id}";
        public static final String api_getById="/module/{id}";
        public static final String api_getListNoPage="/module/listNoPage";
        public static final String api_getList="/module/list";
        public static final String api_getChildren="/module/children/{id}";

    }

    public static class ModuleFun{
        public static final String api_getExistFun = "/moduleFun/existFunc/{id}";
        public static final String api_changeFun="/moduleFun/changeFun";
    }

    //saas常量
    public static class Saas{
        public static final String api_common="/saas";
        public static final String api_create="/create";
        public static final String api_update="/update";
        public static final String api_delete="/delete";
        public static final String api_getSaassNoPage="/getSaassNoPage";

    }
}
