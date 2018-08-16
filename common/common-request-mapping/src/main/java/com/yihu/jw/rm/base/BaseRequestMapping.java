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
     * 模块
     */
    public static class Module extends Basic {
        public static final String PREFIX  = "/module";
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


    //功能模块常量
    public static class Function{
        public static final String message_success_create="function create success";
        public static final String message_success_update="function update success";
        public static final String message_success_find="function find success";
        public static final String message_success_find_functions="functions find success";
        public static final String message_success_delete="function delete success";
        public static final String message_success_assign_function=" assign function create success";
        public static final String message_success_find_functions_module="moduleFunctions find success";
        public static final String message_fail_name_exist="function name exist";
        public static final String message_fail_name_is_null="function is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_id_no_exist=" no exist";
        public static final String api_create="/function";
        public static final String api_update="/function";
        public static final String api_delete="/function/{ids}";
        public static final String api_getById="/function/{id}";
        public static final String api_getListNoPage="/function/listNoPage";
        public static final String api_getList="/function/list";
        public static final String api_assignFunction="/assignFunction";
        public static final String api_getModuleFunctions="/api_getModuleFunctions";
        public static final String api_getChildren="/function/children/{id}";
    }

    public static class ModuleFun{
        public static final String api_getExistFun = "/moduleFun/existFunc/{id}";
        public static final String api_changeFun="/moduleFun/changeFun";
        public static final String moduleId_is_null="moduleId is null";
        public static final String funIds_is_null="funIds is null";
    }

    //SystemDictList常量
    public static class SystemDictList{
        public static final String message_success_create="SystemDictList create success";
        public static final String message_success_update="SystemDictList update success";
        public static final String message_success_find="SystemDictList find success";
        public static final String message_success_find_saass="SystemDictList find success";
        public static final String message_success_delete="SystemDictList delete success";


        public static final String message_fail_name_exist="SystemDictList name exist";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_id_no_exist="id no exist";


        public static final String api_create="/systemDictList";
        public static final String api_update="/systemDictList";
        public static final String api_delete="/systemDictList";
        public static final String api_getById="/systemDictList/getById";
        public static final String api_getList="/systemDictList/list";
        public static final String api_getListNoPage="/systemDictList/listNoPage";
        public static final String api_getChildren="/systemDictList/children/{id}";

    }




/*
    //d行政人员模块
    public static class Employee{
        public static final String message_success_create="employee create success";
        public static final String message_success_update="employee update success";
        public static final String message_success_find="employee find success";
        public static final String message_success_find_saass="employee find success";
        public static final String message_success_delete="employee delete success";


        public static final String message_fail_name_exist="employee name exist";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_id_no_exist="id no exist";


        public static final String api_create="/employee";
        public static final String api_update="/employee";
        public static final String api_delete="/employee";
        public static final String api_getById="/getById";
        public static final String api_getEmployee="/getEmployee";
        public static final String api_getEmployeeByAccount="/getEmployeeByAccount";
        public static final String api_getEmployeeNoPage="/getEmployeeNoPage";

    }*/
}
