package com.yihu.jw.restmodel.base.version;

/**
 * Created by chenweida on 2017/6/16.
 */
public class BaseVersionContants {

    public static final String api_common = "/version";

    public static class BaseServerVersion {
        public static final String message_success_create = "ServerVersion create success";
        public static final String message_success_update = "ServerVersion update success";
        public static final String message_success_find = "ServerVersion find success";
        public static final String message_success_find_BaseServerVersions = "ServerVersions find success";
        public static final String message_success_delete = "ServerVersion delete success";


        public static final String message_fail_name_exist = "ServerVersion name exist";
        public static final String message_fail_code_is_null = "code is null";
        public static final String message_fail_name_is_null = "ServerVersion is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_code_no_exist = "code no exist";

        public static final String api_create = "/serverVersion";
        public static final String api_update = "/serverVersion";
        public static final String api_delete = "/serverVersion/{codes}";
        public static final String api_getByCode = "/serverVersion/{code}";
        public static final String api_getList="/serverVersion/list";
        public static final String api_getListNoPage="/serverVersion/listNoPage";

    }

    public static class BaseServerUrlVersion {
        public static final String message_success_create = "ServerUrlVersion create success";
        public static final String message_success_update = "ServerUrlVersion update success";
        public static final String message_success_find = "ServerUrlVersion find success";
        public static final String message_success_find_BaseServerUrlVersions = "ServerUrlVersion find success";
        public static final String message_success_delete = "ServerUrlVersion delete success";

        public static final String message_fail_name_exist = "ServerUrlVersion name exist";
        public static final String message_fail_code_is_null = "code is null";
        public static final String message_fail_name_is_null = "ServerUrlVersion is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_code_no_exist = "code no exist";

        public static final String api_create = "/serverUrl";
        public static final String api_update = "/serverUrl";
        public static final String api_delete = "/serverUrl/{codes}";
        public static final String api_getByCode = "/serverUrl/{code}";
        public static final String api_getList="/serverUrl/list";
        public static final String api_getListNoPage="/serverUrl/listNoPage";

    }

    public static class BaseServerVersionLog {
        public static final String message_success_create = "BaseServerVersionLog create success";
        public static final String message_success_update = "BaseServerVersionLog update success";
        public static final String message_success_find = "BaseServerVersionLog find success";
        public static final String message_success_find_BaseServerVersionLog = "BaseServerVersionLog find success";
        public static final String message_success_delete = "BaseServerVersionLog delete success";

        public static final String message_fail_code_is_null = "code is null";
        public static final String message_fail_name_is_null = "ServerVersion is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_code_no_exist = "code no exist";

        public static final String api_create = "/serverVersionLog";
        public static final String api_update = "/serverVersionLog";
        public static final String api_delete = "/serverVersionLog/{codes}";
        public static final String api_getByCode = "/serverVersionLog/{code}";
        public static final String api_getList="/serverVersionLog/list";
        public static final String api_getListNoPage="/serverVersionLog/listNoPage";

    }


    public static class WlyyVersion {
        public static final String message_success_create = "WlyyVersion create success";
        public static final String message_success_update = "WlyyVersion update success";
        public static final String message_success_find = "WlyyVersion find success";
        public static final String message_success_find_WlyyVersions = "WlyyVersion find success";
        public static final String message_success_delete = "WlyyVersion delete success";


        public static final String message_fail_name_exist = "WlyyVersion name exist";
        public static final String message_fail_code_is_null = "code is null";
        public static final String message_fail_name_is_null = "ServerVersion is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_code_no_exist = "code no exist";

        public static final String api_create = "/wlyyVersion";
        public static final String api_update = "/wlyyVersion";
        public static final String api_delete = "/wlyyVersion/{codes}";
        public static final String api_getByCode = "/wlyyVersion/{code}";
        public static final String api_getList="/wlyyVersion/list";
        public static final String api_getListNoPage="/wlyyVersion/listNoPage";

    }

    public class UserUrlVersion {
        public static final String api_create = "/userUrlVersion";
        public static final String api_update = "/userUrlVersion";
        public static final String api_delete = "/userUrlVersion/{codes}";
        public static final String api_getByCode = "/userUrlVersion/{code}";
        public static final String api_getList="/userUrlVersion/list";
        public static final String api_getListNoPage="/userUrlVersion/listNoPage";
        public static final String api_changeUserVersion="/userUrlVersion/userVersion";

        public static final String message_success_create="userUrlVersion create success";
        public static final String message_success_update="update UserVersion success";
        public static final String message_success_delete="userUrlVersion delete success";
        public static final String message_success_find="userUrlVersion find success";
                ;
    }
}
