package com.yihu.jw.rm.base;

public class BaseLoginRequestMapping {
    public static final String api_common = BaseRequestMapping.api_base_common + "/login";
    public static final String api_gateway_common = BaseRequestMapping.api_base_common + "/gateway/login";

    /**
     * 用户账号类型
     */
    public static class BaseLoginAccount {
        public static final String api_create = "/baseLoginAccount";
        public static final String api_update = "/baseLoginAccount";
        public static final String api_delete = "/baseLoginAccount/{ids}";
        public static final String api_getById = "/baseLoginAccount/{id}";
        public static final String api_getOne="/baseLoginAccount";
        public static final String mobileSendSms = "/code/sms";
        public static final String api_checkoutInfo = "/register/checkInfo";
        public static final String api_accountSub = "/register/accountSub";
        public static final String api_login= "/authentication/form";

        public static final String message_success_create = "baseLoginAccount create success";
        public static final String message_success_update = "baseLoginAccount update success";
        public static final String message_success_find = "baseLoginAccount find success";
        public static final String message_success_delete = "baseLoginAccount delete success";

        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_saasid_is_null = "saasId is null";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_saasid_no_exist = "saasId no exist";
        public static final String message_fail_role_no_exist = "baseLoginAccount no exist";

        public static final String message_param_id_is_null = "baseLoginAccount id param cannot be null";
        public static final String message_param_saasid_is_null = "baseLoginAccount saasId param cannot be null";
    }

}
