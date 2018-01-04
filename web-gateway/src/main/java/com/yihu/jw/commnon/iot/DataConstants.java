package com.yihu.jw.commnon.iot;

public class DataConstants {
    public static final String iot = "iot";

    //数据标准转换
    public static class DataConvert{
        public static final String api_common = iot + "/dataConvert";
        public static final String api_convert = "convert";
    }

    //数据上传常量
    public static class DataInput {
        public static final String api_common = iot + "/dataInput";
        public static final String api_bind_user = "userBind";
        public static final String api_upload_data = "input";
    }
}
