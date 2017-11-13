package com.yihu.jw.rm.base;

/**
 * Created by chenweida on 2017/6/16.
 */
public class BaseVersionRequestMapping {

    public static final String api_common = "/version";
    public static class WlyyVersion {
        public static final String message_success_create = "WlyyVersion create success";
        public static final String message_success_update = "WlyyVersion update success";
        public static final String message_success_find = "WlyyVersion find success";
        public static final String message_success_find_WlyyVersions = "WlyyVersion find success";
        public static final String message_success_delete = "WlyyVersion delete success";


        public static final String message_fail_name_exist = "WlyyVersion name exist";
        public static final String message_fail_name_is_null = "ServerVersion is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_no_exist = "id no exist";

        public static final String api_create = "/wlyyVersion";
        public static final String api_update = "/wlyyVersion";
        public static final String api_delete = "/wlyyVersion/{ids}";
        public static final String api_getById = "/wlyyVersion/{id}";
        public static final String api_getList="/wlyyVersion/list";
        public static final String api_getListNoPage="/wlyyVersion/listNoPage";

    }

    public class UserVersion {
        public static final String api_create = "/userVersion";
        public static final String api_delete = "/userVersion/{ids}";
        public static final String api_getById = "/userVersion/{id}";
        public static final String api_getList="/userVersion/list";
        public static final String api_getListNoPage="/userVersion/listNoPage";

        public static final String message_success_create="userVersion create success";
        public static final String message_success_update="update UserVersion success";
        public static final String message_success_delete="userVersion delete success";
        public static final String message_success_find="userVersion find success";
                ;
    }
}
