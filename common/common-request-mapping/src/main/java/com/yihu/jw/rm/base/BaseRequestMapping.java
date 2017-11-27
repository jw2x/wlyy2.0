package com.yihu.jw.rm.base;

/**
 * Created by chenweida on 2017/5/19.
 * 基础模块的静态变量
 */
public class BaseRequestMapping {

    public static final String api_common = "/svr-bases";

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

    //模块常量
    public static class Module{
        public static final String message_success_create="Module create success";
        public static final String message_success_update="Module update success";
        public static final String message_success_find="Module find success";
        public static final String message_success_find_Modules="Module find success";
        public static final String message_success_delete="Module delete success";
        public static final String message_success_assign_module=" assign Module create success";


        public static final String message_fail_name_exist=" name exist";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_saasid_is_null="saasid is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_id_no_exist="id no exist";


        public static final String api_create="/module";
        public static final String api_update="/module";
        public static final String api_delete="/module/{ids}";
        public static final String api_getById="/module/{id}";
        public static final String api_getListNoPage="/module/listNoPage";
        public static final String api_getList="/module/list";
        public static final String api_getChildren="/module/children/{id}";

    }

    public static class ModuleFun{
        public static final String api_getExistFun = "/moduleFun/existFunc/{id}";
        public static final String api_changeFun="/moduleFun/changeFun";


        public static final String moduleId_is_null="moduleId is null";
        public static final String funIds_is_null="funIds is null";
    }

    //saas常量
    public static class Saas{
        public static final String message_success_create="saas create success";
        public static final String message_success_update="saas update success";
        public static final String message_success_find="saas find success";
        public static final String message_success_find_saass="saas find success";
        public static final String message_success_delete="saas delete success";


        public static final String message_fail_name_exist="saas name exist";
        public static final String message_fail_name_is_null="name is null";
        public static final String message_fail_id_is_null="id is null";
        public static final String message_fail_id_no_exist="id no exist";


        public static final String api_create="/saas";
        public static final String api_update="/saas";
        public static final String api_delete="/saas";
        public static final String api_getById="/getById";
        public static final String api_getSaass="/getSaass";
        public static final String api_getSaassNoPage="/getSaassNoPage";

    }
}
