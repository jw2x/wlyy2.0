package com.yihu.jw.rm.base;

/**
 * Created by chenweida on 2017/5/19.
 * 基础模块的静态变量
 */
public class BaseRequestMapping {

    @Deprecated
    public static final String api_base_common = "/svr-bases";

    /**
     * 基础请求地址
     */
    private abstract static class Basic {
        public static final String CREATE =  "/create";
        public static final String DELETE = "/delete";
        public static final String UPDATE = "/update";
        public static final String PAGE = "/page";
        public static final String LIST = "/list";
    }

    /**
     * SAAS
     */
    public static class Saas extends Basic {
        public static final String PREFIX  = "/saas";
        public static final String AUDIT  = "/audit";
    }

    /**
     * 模块
     */
    public static class Module extends Basic {
        public static final String PREFIX  = "/module";
    }

    /**
     * 功能
     */
    public static class Function extends Basic {
        public static final String PREFIX  = "/function";
    }

    /**
     * 模块功能
     */
    public static class ModuleFunction extends Basic {
        public static final String PREFIX  = "/module_function";
    }

    /**
     * 主题
     */
    public static class Theme extends Basic {
        public static final String PREFIX  = "/theme";
        public static final String CHECK_STYLE = "/check_style";
    }

    /**
     * 系统字典
     */
    public static class SystemDict extends Basic {
        public static final String PREFIX  = "/system_dict";
    }

    /**
     * 系统字典项
     */
    public static class SystemDictEntry extends Basic {
        public static final String PREFIX  = "/system_dict_entry";
    }

    public static class ModuleFun{
        public static final String api_getExistFun = "/moduleFun/existFunc/{id}";
        public static final String api_changeFun="/moduleFun/changeFun";
        public static final String moduleId_is_null="moduleId is null";
        public static final String funIds_is_null="funIds is null";
    }

}
