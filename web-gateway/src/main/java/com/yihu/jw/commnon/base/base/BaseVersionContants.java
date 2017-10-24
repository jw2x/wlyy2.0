package com.yihu.jw.commnon.base.base;

/**
 * Created by chenweida on 2017/6/16.
 */
public class BaseVersionContants {

    public static final String api_common = "/version";

    public static class BaseServerVersion {
        public static final String api_create = "/serverVersion";
        public static final String api_update = "/serverVersion";
        public static final String api_delete = "/serverVersion/{codes}";
        public static final String api_getByCode = "/serverVersion/{code}";
        public static final String api_getList="/serverVersion/list";
        public static final String api_getListNoPage="/serverVersion/listNoPage";

    }

    public static class BaseServerUrlVersion {
        public static final String api_create = "/serverUrl";
        public static final String api_update = "/serverUrl";
        public static final String api_delete = "/serverUrl/{codes}";
        public static final String api_getByCode = "/serverUrl/{code}";
        public static final String api_getList="/serverUrl/list";
        public static final String api_getListNoPage="/serverUrl/listNoPage";

    }

    public static class BaseServerVersionLog {
        public static final String api_create = "/serverVersionLog";
        public static final String api_update = "/serverVersionLog";
        public static final String api_delete = "/serverVersionLog/{codes}";
        public static final String api_getByCode = "/serverVersionLog/{code}";
        public static final String api_getList="/serverVersionLog/list";
        public static final String api_getListNoPage="/serverVersionLog/listNoPage";

    }


    public static class WlyyVersion {

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

                ;
    }
}
