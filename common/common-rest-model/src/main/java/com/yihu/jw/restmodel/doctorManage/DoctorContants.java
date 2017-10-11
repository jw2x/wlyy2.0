package com.yihu.jw.restmodel.doctorManage;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
public class DoctorContants {
    public static final String api_common="/doctorManage";

    public static class Doctor{
        public static final String api_create="/doctor";
        public static final String api_update="/doctor";
        public static final String api_delete="/doctor/{codes}";
        public static final String api_getListNoPage="/doctor/listNoPage";
        public static final String api_getList="/doctor/list";
        public static final String api_getByCode="/doctor/{code}";

        public static final String message_success_create="doctor create success";
        public static final String message_success_update="doctor update success";
        public static final String message_success_find="doctor find success";
        public static final String message_success_find_functions="doctor find success";
        public static final String message_success_delete="doctor delete success";

        public static final String message_fail_code_is_null="code is null";
        public static final String message_fail_code_no_exist="code not exist";
        public static final String message_fail_status_is_null="status is null";
    }
}
