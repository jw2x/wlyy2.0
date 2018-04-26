package com.yihu.jw.rm.rehabilitation;

/**
 * Created by humingfen on 2018/4/25 .
 */
public class RehabilitationRequestMapping {

    public static final String api_rehabilitation_common = "svr-rehabilitation";

    /**
     * 公共模块
     */
    public static class Common {
        public static final String information = api_rehabilitation_common + "/information";
    }

    public static class Information {
        public static final String api_create = "information";
        public static final String api_delete = "information";
        public static final String api_getById = "getInformationById";
        public static final String api_update = "information";

        public static final String findInformationById = "findInformationById";
        public static final String findInformationPage = "findInformationPage";

        public static final String message_success_update = "information update success";
        public static final String message_success_delete = "information delete success";
        public static final String message_success_find = "information find success";
        public static final String message_success_create = "information create success";

        public static final String message_success_find_functions = "message_success_find_functions";
    }
}