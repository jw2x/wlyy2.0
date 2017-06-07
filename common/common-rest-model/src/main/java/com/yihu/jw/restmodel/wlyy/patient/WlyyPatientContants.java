package com.yihu.jw.restmodel.wlyy.patient;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class WlyyPatientContants {
    public static class Advertisement{
        public static final String api_common="advertisement";
        public static final String api_create="create";
        public static final String api_delete="delete";
        public static final String api_getByCode="getByCode";
        public static final String api_update="update";
        public static final String api_queryPage="queryPage";
        public static final String api_getList="getList";
        public static final String api_getListByPatientCode="getListByPatientCode";

        public static final String message_success_update="advertisement update success";
        public static final String message_success_delete="advertisement delete success";
        public static final String message_success_find="advertisement find success";
        public static final String message_success_create="advertisement create success";
        public static final String message_success_find_functions="advertisement find success";

        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_code_no_exist="code no exist";
        public static final String message_fail_status_is_null="status is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_code_exist="code exist";
        public static final String message_fail_saasid_is_null="saasid is null";
        public static final String message_fail_picture_is_null="picture is null";
        public static final String message_fail_wlyyAdvertisement_is_not_exist="WlyyAdvertisement is not exist";
    }
}
