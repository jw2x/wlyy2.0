package com.yihu.jw.rm.iot;

public class DataRequestMapping {

    public static final String api_iot_common = "svr-iot";
    public static final Integer api_iot_fail = -1;

    /**
     * 数据标准转换
     */
    public static class DataStandardConvert{
        public static final String api_convert = "dataConvert";//文件流上传

        public static final String message_success_convert = "转换成功";
        public static final String message_fail_convert = "转换失败";
        public static final String message_fail_jsonData_is_null = "jsonData is null";
    }

    /**
     * 数据上传
     */
    public static class DataInput{
        public static final String api_data_input = "input";//数据上传
        public static final String api_user_bind = "userBind";//设备注册绑定
        public static final String api_weRunData_input = "inputWeRunData";//微信运动数据上传


        public static final String message_success = "upload success";
        public static final String message_fail = "upload fail";
        public static final String message_fail_jsonData_is_null = "jsonData is null";
    }

    /**
     * 数据查询
     */
    public static class DataSearch{
        public static final String api_data_search_one = "getById";
        public static final String api_user_search_list = "searchList";
        public static final String api_user_search_list_page = "listPage";
        public static final String api_user_search_recent5 = "recent5";
        public static final String api_user_search_recent1 = "recent1";
        public static final String api_user_abnormal_times_a_week = "abnormalTimes";
        public static final String api_user_search_list_code_del = "searchListByCodeAndDel";
        public static final String api_user_search_werun_datas = "getWeRunDataListById";

        public static final String api_user_delete = "delete";
        public static final String api_user_update = "update";

        public static final String search_success = "search success";
        public static final String update_success = "update success";
        public static final String delete_success = "delete success";
        public static final String message_fail = "failed";
        public static final String message_fail_jsonData_is_null = "param is null";
    }

}
