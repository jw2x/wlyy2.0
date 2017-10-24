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
        public static final String api_delete="/function/{codes}";
        public static final String api_getByCode="/function/{code}";
        public static final String api_getListNoPage="/function/listNoPage";
        public static final String api_getList="/function/list";
        public static final String api_assignFunction="/assignFunction";
        public static final String api_getModuleFunctions="/api_getModuleFunctions";
        public static final String api_getChildren="/function/children/{code}";
    }

    //模块常量
    public static class Module{
        public static final String api_create="/module";
        public static final String api_update="/module";
        public static final String api_delete="/module/{codes}";
        public static final String api_getByCode="/module/{code}";
        public static final String api_getListNoPage="/module/listNoPage";
        public static final String api_getList="/module/list";
        public static final String api_getChildren="/module/children/{code}";

    }

    public static class ModuleFun{
        public static final String api_getExistFun = "/moduleFun/existFunc/{code}";
        public static final String api_changeFun="/moduleFun/changeFun";
    }

    //saas常量
    public static class Saas{
        public static final String api_common="/saas";
        public static final String api_create="/create";
        public static final String api_update="/update";
        public static final String api_delete="/delete";
        public static final String api_getByCode="/getByCode";
        public static final String api_getSaass="/getSaass";
        public static final String api_getSaassNoPage="/getSaassNoPage";

    }
}
