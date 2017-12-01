package com.yihu.jw.rm.base;

public class BaseUserRequestMapping {
    public static final String api_common = BaseRequestMapping.api_base_common + "/user";

    /**
     * 基础角色
     */
    public static class BaseRole {
        public static final String api_create = "/baseRole";
        public static final String api_update = "/baseRole";
        public static final String api_delete = "/baseRole/{ids}";
        public static final String api_getById = "/baseRole/{id}";
        public static final String api_getList="/baseRole/list";
        public static final String api_getOne="/baseRole/one";
        public static final String api_getListNoPage="/baseRole/listNoPage";

        public static final String message_success_create = "baseRole create success";
        public static final String message_success_update = "baseRole update success";
        public static final String message_success_find = "baseRole find success";
        public static final String message_success_delete = "baseRole delete success";

        public static final String message_fail_name_exist = "baseRole name exist";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_saasid_is_null = "saasId is null";
        public static final String message_fail_name_is_null = "baseRole name is null";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_saasid_no_exist = "saasId no exist";
        public static final String message_fail_role_no_exist = "baseRole no exist";

        public static final String message_param_id_is_null = "baseRole id param cannot be null";
        public static final String message_param_saasid_is_null = "baseRole saasId param cannot be null";
        public static final String message_param_name_is_null = "baseRole name param cannot be null";
    }

    /**
     * 用户
     */
    public class Employee {
        public static final String api_create = "/employee";
        public static final String api_update = "/employee";
        public static final String api_delete = "/employee/{ids}";
        public static final String api_getById = "/employee/{id}";
        public static final String api_getByUserId = "/employeeById/{userId}";
        public static final String api_getList="/employee/list";
        public static final String api_getListNoPage="/employee/listNoPage";

        public static final String message_success_create="Employee create success";
        public static final String message_success_update="update Employee success";
        public static final String message_success_delete="Employee delete success";
        public static final String message_success_find="Employee find success";

        public static final String message_fail_name_exist = "baseRole name exist";
        public static final String message_fail_name_is_null = "baseRole is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_no_exist = "id no exist";

    }
}
