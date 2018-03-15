package com.yihu.jw.common.iot;

/**
 * @author yeshijie on 2018/1/20.
 */
public class IotCommonContants {

    public static final String api_iot_common = "svr-iot";
    public static final Integer api_iot_fail = -1;

    /**
     * 公共模块
     */
    public static class Common{
        public static final String company = api_iot_common + "/wg/company";
        public static final String system_dict = api_iot_common + "/wg/systemDict";
        public static final String file_upload = api_iot_common + "/wg/fileUpload";
        public static final String product = api_iot_common + "/wg/product";
        public static final String order = api_iot_common + "/wg/order";
        public static final String device = api_iot_common + "/wg/device";
        public static final String quality = api_iot_common + "/wg/quality";
        public static final String patientDevice = api_iot_common + "/wg/patientDevice";
    }

}
