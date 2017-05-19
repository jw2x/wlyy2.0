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
        public static final String message_fail_code_no_exist="code no exist";


        public static final String api_common="function";
        public static final String api_create="create";
        public static final String api_update="update";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_getFunctions="getFunctions";
        public static final String api_getFunctionsNoPage="getFunctionsNoPage";

    }
    //微信token模块常量
    public static class WxAccessToken{
        public static final String message_success_create="wxAccessToken create success";
        public static final String message_success_get="wxAccessToken get success";

        public static final String api_common="wxAccessToken";
        public static final String api_create="create";
        public static final String api_get="get";
        public static final String message_fail_wechatCode_is_null="wechatCode is null";
    }

    //微信按钮模块常量
    public static class WxMenu{
        public static final String message_success_create="wxMenu create success";
        public static final String message_success_get="wxMenu get success";

        public static final String api_common="wxMenu";
        public static final String api_create="create";
        public static final String api_get="get";
    }
}
