package com.yihu.jw.common.base.user;
/**
 * Created by LiTaohong on 2017/11/28.
 */
public class BaseUserContants {
    public static final String api_common = "/bases";
    public static final String api_baseRole = api_common+"/baseRole";
    public static final String api_employ = api_common+"/employee";
    public static final String api_baseMenu= api_common+"/baseMenu";
    public static final String api_baseRoleMenu= api_common+"/baseRoleMenu";
    public static final String api_employRole= api_common+"/employeeRole";

    //角色常量
    public static class BaseRole{
        public static final String api_create="/";
        public static final String api_update="/update";
        public static final String api_delete="/delete/{id}";
        public static final String api_getById="/getById/{id}";
        public static final String api_getListNoPage="/listNoPage";
        public static final String api_getList="/list";
    }


    //角色菜单常量
    public static class BaseRoleMenu{
        public static final String api_create=api_baseRoleMenu ;
        public static final String api_update=api_baseRoleMenu + "/update";
        public static final String api_delete=api_baseRoleMenu + "/delete/{id}";
        public static final String api_getListNoPage=api_baseRoleMenu + "/listNoPage";
    }


    //用户常量
    public static class Employee{
        public static final String api_create="/";
        public static final String api_update="/update";
        public static final String api_delete="/delete/{id}";
        public static final String api_getById="/getById/{id}";
        public static final String api_getListNoPage="/listNoPage";
        public static final String api_getList="/list";
    }

    //用户角色常量
    public static class EmployeeRole{
        public static final String api_create= api_employRole;
        public static final String api_update= api_employRole + "/update";
        public static final String api_delete= api_employRole +"/delete/{id}";
        public static final String api_getListNoPage= api_employRole +"/listNoPage";
    }

    //菜单常量
    public static class BaseMenu{
        public static final String api_create="/";
        public static final String api_update="/update";
        public static final String api_delete="/delete/{id}";
        public static final String api_getById="/getById/{id}";
        public static final String api_children="/{saasId}/{parentId}";
        public static final String api_getListNoPage="/listNoPage";
        public static final String api_getList="/list";
    }

}
