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
     * SAAS默认模块
     */
    public static class SaasDefaultModuleFunction extends Basic {
        public static final String PREFIX  = "/saas_default_module_function";
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

    /**
     * 用户
     */
    public static class User extends Basic {
        public static final String PREFIX  = "/user";
        public static final String CHECK_USERNAME = "/check_username";
    }

    /**
     * 用户角色
     */
    public static class UserRole extends Basic {
        public static final String PREFIX  = "/user_role";
    }

    /**
     * 用户取消的模块或者功能
     */
    public static class UserHideModuleFunction extends Basic {
        public static final String PREFIX  = "/user_hide_module_function";
    }

    /**
     * 角色
     */
    public static class Role extends Basic {
        public static final String PREFIX  = "/role";
    }

    /**
     * 角色模块功能
     */
    public static class RoleModuleFunction extends Basic {
        public static final String PREFIX  = "/role_module_function";
    }

    /**
     * 微信
     */
    public static class WeChat extends Basic {
        public static final String PREFIX  = "/wechat";
    }

    /**
     * 短信网关
     */
    public static class SmsGateway extends Basic {
        public static final String PREFIX  = "/sms_gateway";
        public static final String SEND  = "/send";
    }

    /**
     * 短信模板
     */
    public static class SmsTemplate extends Basic {
        public static final String PREFIX  = "/sms_template";
    }

}
