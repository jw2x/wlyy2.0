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
        public static final String api_data_input = "dataInput";//数据上传
        public static final String api_user_bind = "userBind";//设备注册绑定

        public static final String message_success = "上传成功";
        public static final String message_fail = "上传失败";
        public static final String message_fail_jsonData_is_null = "jsonData is null";
    }

}
