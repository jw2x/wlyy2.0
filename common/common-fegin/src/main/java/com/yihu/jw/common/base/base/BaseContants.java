package com.yihu.jw.common.base.base;

/**
 * Created by chenweida on 2017/5/19.
 * 基础模块的静态变量
 */
public class BaseContants {
    public static final String api_common = "/bases";
    public static final String api_function = api_common+"/function";
    public static final String api_module = api_common+"/module";
    public static final String api_moduleFun = api_common+"/moduleFun";
    public static final String api_saas = api_common+"/saas";
    public static final String api_systemDict = api_common+"/systemDict";
    public static final String api_systemDictList = api_common+"/systemDictList";

    //功能模块常量
    public static class Function{
        public static final String api_create="/";
        public static final String api_update="/";
        public static final String api_delete="/{id}";
        public static final String api_getById="/{id}";
        public static final String api_getListNoPage="/listNoPage";
        public static final String api_getList="/list";
        public static final String api_getChildren="/children/{id}";
    }

    //模块常量
    public static class Module{
        public static final String api_create="/";
        public static final String api_update="/";
        public static final String api_delete="/{id}";
        public static final String api_getById="/{id}";
        public static final String api_getListNoPage="/listNoPage";
        public static final String api_getList="/list";
        public static final String api_getChildren="/children/{id}";

    }

    public static class ModuleFun{
        public static final String api_getExistFun = "/existFunc/{id}";
        public static final String api_changeFun="/changeFun";
    }

    //saas常量
    public static class Saas{
        public static final String api_create="/";
        public static final String api_update="/";
        public static final String api_delete="/{id}";
        public static final String api_getSaassNoPage="/getSaassNoPage";

    }

    //SystemDict常量
    public static class SystemDict{
        public static final String api_create="/";
        public static final String api_update="/";
        public static final String api_delete="/{id}";
        public static final String api_getSystemDictNoPage="/getSaassNoPage";

    }

    //SystemDictList常量
    public static class SystemDictList{
        public static final String api_create="/";
        public static final String api_update="/";
        public static final String api_delete="/{id}";
        public static final String api_getSystemDictListNoPage="/getSaassNoPage";

    }
}
