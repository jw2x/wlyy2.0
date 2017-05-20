package com.yihu.jw.restmodel.base;

/**
 * Created by chenweida on 2017/5/19.
 * 基础模块的静态变量
 */
public class BaseContants {


    //功能模块常量
    public static class Function{
        public static final String message_success_create="function create success";
        public static final String message_success_update="function update success";
        public static final String message_success_find="function find success";
        public static final String message_success_find_functions="functions find success";
        public static final String message_success_delete="function delete success";


        public static final String message_fail_name_exist="function name exist";
        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_name_is_null="function is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_wechatCode_is_null="wechatCode is null";


        public static final String message_fail_code_no_exist="code no exist";
        public static final String api_common="function";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_getFunctions="getFunctions";

        public static final String api_getFunctionsNoPage="getFunctionsNoPage";
    }

    //模块常量
    public static class Module{
        public static final String message_success_create="Module create success";
        public static final String message_success_update="Module update success";
        public static final String message_success_find="Module find success";
        public static final String message_success_find_Modules="Module find success";
        public static final String message_success_delete="Module delete success";


        public static final String message_fail_name_exist=" name exist";
        public static final String message_fail_code_is_null=" code is null";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_saasid_is_null="saasid is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_code_no_exist="code no exist";


        public static final String api_common="module";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_getModules="getModules";
        public static final String api_getModulesNoPage="getModulesNoPage";

    }

    //saas常量
    public static class Saas{
        public static final String message_success_create="saas create success";
        public static final String message_success_update="saas update success";
        public static final String message_success_find="saas find success";
        public static final String message_success_find_saass="saas find success";
        public static final String message_success_delete="saas delete success";


        public static final String message_fail_name_exist="saas name exist";
        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_code_no_exist="code no exist";


        public static final String api_common="saas";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_getSaass="getSaass";
        public static final String api_getSaassNoPage="getSaassNoPage";

    }

    //微信token模块常量
    public static class WxAccessToken{
        public static final String message_success_create="wxAccessToken create success";
        public static final String message_success_get="wxAccessToken get success";

        public static final String api_common="wxAccessToken";
        public static final String api_create="create";
        public static final String api_get="get";
        public static final String message_fail_wechatCode_is_null="wechatCode is null";
        public static final String message_fail_expiresIn_is_null="expiresIn is null";
    }

    //微信按钮模块常量
    public static class WxMenu{
        public static final String api_common="wxMenu";
        public static final String api_create="create";
        public static final String api_get="get";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_update="update";
        public static final String api_getWxMenus="getWxMenus";
        public static final String api_getWxMenuNoPage="getWxMenuNoPage";

        public static final String message_fail_wechatCode_is_null="wechatCode is null";
        public static final String message_fail_wxMenuName_is_null="wxMenuName is null";
        public static final String message_fail_wechatcode_is_null="wechatCode is null";
        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_name_is_null="weChatMenuName is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_code_no_exist="id is null";

        public static final String message_success_update="wxMenu update success";
        public static final String message_success_delete="wxMenu delete success";
        public static final String message_success_find="wxMenu success find";
        public static final String message_success_create="wxMenu create success";
        public static final String message_success_get="wxMenu get success";
        public static final String message_success_find_functions="wxMenu find success";
    }

    public static class WxTemplate {
        public static final String api_common="wxTemplate";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_getWxTemplates="getWxTemplates";
        public static final String api_getWxTemplatesNoPage="getWxTemplatesNoPage";

        public static final String message_success_create="wxTemplate create success";
        public static final String message_success_update="wxTemplate update success";
        public static final String message_success_find="wxTemplate find success";
        public static final String message_success_find_functions="wxTemplate find success";
        public static final String message_success_delete="wxTemplate delete success";

        public static final String message_fail_name_exist="wxTemplate name exist";
        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_name_is_null="wxTemplate is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_code_no_exist="code no exist";

    }

    public static class Wechat {
        public static final String api_common="wechat";
        public static final String api_create="create";
        public static final String api_get="get";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getWechatNoPage="getWechatNoPage";

        public static final String api_getWechats="getWechats";
        public static final String api_getByCode="getByCode";
        public static final String message_success_create="wechat create success";
        public static final String message_success_update="wechat update success";
        public static final String message_success_find="wechat find success";

        public static final String message_success_find_functions="wechat find success";
        public static final String message_success_delete="wechat delete success";
        public static final String message_fail_name_exist="wechat name exist";
        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_name_is_null="wechat is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_code_no_exist="code no exist";
        public static final String message_fail_appId_is_null="appId is null";
        public static final String message_fail_appSecret_is_null="appSecret is null";
        public static final String message_fail_appId_exist="wechat appId exist";
    }
}
