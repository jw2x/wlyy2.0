package com.yihu.jw.rm.iot;

/**
 * @author yeshijie on 2017/12/5.
 */
public class IotRequestMapping {

    public static final String api_iot_common = "svr-iot";

    //协议模块常量
    public static class DeviceSupplier {
        public static final String api_create = "deviceSupplier";
        public static final String api_delete = "deviceSupplier";
        public static final String api_getById = "getDeviceSupplierById";
        public static final String api_update = "deviceSupplier";
        public static final String api_queryPage = "queryDeviceSupplierPage";
        public static final String api_getList = "getDeviceSupplierList";

        public static final String message_success_update = "deviceSupplier update success";
        public static final String message_success_delete = "deviceSupplier delete success";
        public static final String message_success_find = "deviceSupplier find success";
        public static final String message_success_create = "deviceSupplier create success";
        public static final String message_success_find_functions = "deviceSupplier find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_price_is_null = "price is null";
        public static final String message_fail_iotDeviceSupplier_is_no_exist = "iotDeviceSupplier is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }
}
