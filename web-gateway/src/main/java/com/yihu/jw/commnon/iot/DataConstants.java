package com.yihu.jw.commnon.iot;

public class DataConstants {
    public static final String api_iot_common = "/svr-iot";

    //数据标准转换
    public static class DataConvert{
        public static final String api_common = api_iot_common + "/dataConvert";
        public static final String api_convert = "/convert";
    }

    //数据上传常量
    public static class DataInput {
        public static final String api_common = api_iot_common + "/dataInput";
        public static final String api_bind_user = "/userBind";
        public static final String api_upload_data = "/input";
    }


    /**
     * 数据查询
     */
    public static class DataSearch{
        public static final String api_common = api_iot_common + "/dataSearch";
        public static final String api_data_search_one = "/getById";
        public static final String api_user_search_list = "/searchList";
        public static final String api_user_search_list_page = "/listPage";
        public static final String api_user_search_recent5 = "/recent5";
        public static final String api_user_search_recent1 = "/recent1";
        public static final String api_user_abnormal_times_a_week = "/abnormalTimes";
        public static final String api_user_search_list_code_del = "/searchListByCodeAndDel";
    }

}
