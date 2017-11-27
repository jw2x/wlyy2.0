package com.yihu.jw.rm.base;

/**
 * Created by chenweida on 2017/5/19.
 * 基础模块的静态变量
 */
public class BaseSmsRequestMapping {

    public static final String api_common=BaseRequestMapping.api_base_common+"/sms";
    //短信常量
    public static class Sms{
        public static final String message_success_create="sms create success";
        public static final String message_success_update="sms update success";
        public static final String message_success_find="sms find success";
        public static final String message_success_find_smss="smss find success";
        public static final String message_success_delete="sms delete success";


        public static final String message_fail_name_exist="sms name exist";
        public static final String message_fail_name_is_null="sms is null";
        public static final String message_fail_id_is_null="id is null";


        public static final String api_create="sms";
        public static final String api_update="sms";

        public static final String api_getSmss="getSmss";

        public static final String api_getSmssNoPage="getSmssNoPage";
    }

    //短信接口模块常量
    public static class SmsGateway{
        public static final String message_success_create="SmsGateway create success";
        public static final String message_success_update="SmsGateway update success";
        public static final String message_success_find="SmsGateway find success";
        public static final String message_success_find_SmsGateways="SmsGateways find success";
        public static final String message_success_delete="SmsGateway delete success";


        public static final String message_fail_name_exist="SmsGateway name exist";
        public static final String message_fail_name_is_null="SmsGateway is null";
        public static final String message_fail_id_is_null="id is null";


        public static final String message_fail_id_no_exist="id no exist";
        public static final String api_create="smsGateway";
        public static final String api_update="smsGateway";
        public static final String api_delete="smsGateway";
        public static final String api_getById="getSmsGatewayById";
        public static final String api_getSmsGateways="getSmsGateways";

        public static final String api_getSmsGatewaysNoPage="getSmsGatewaysNoPage";
    }
}
