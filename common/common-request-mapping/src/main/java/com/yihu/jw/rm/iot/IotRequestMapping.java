package com.yihu.jw.rm.iot;

/**
 * @author yeshijie on 2017/12/5.
 */
public class IotRequestMapping {

    public static final String api_iot_common = "svr-iot";
    public static final Integer api_iot_fail = -1;


    /**
     * 文件上传模块常量
     */
    public static class FileUpload{
        public static final String api_upload_stream = "uploadStream";//文件流上传
        public static final String api_upload_string = "uploadString";//base64字符串上传

        public static final String message_success_upload = "上传成功";
        public static final String message_fail_upload = "上传失败";
        public static final String message_fail_jsonData_is_null = "jsonData is null";
    }

    /**
     * 供应商模块常量
     */
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
        public static final String message_fail_iotDeviceSupplier_is_no_exist = "iotDeviceSupplier is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";

        public static final String message_fail_supplierName_is_null = "供应商名称不能为空";
        public static final String message_fail_organizationCode_is_null = "组织机构代码/统一社会信用代码不能为空";
        public static final String message_fail_contactsName_is_null = "联系人姓名不能为空";
        public static final String message_fail_contactsMobile_is_null = "联系人手机号码不能为空";
        public static final String message_fail_contactsPhone_is_null = "联系人座机不能为空";
        public static final String message_fail_contactsIdcard_is_null = "联系人身份证号不能为空";
        public static final String message_fail_type_is_null = "请选择供应商类型";
        public static final String message_fail_organizationCodeImg_is_null = "请上传组织机构代码证照片";
        public static final String message_fail_contactsIdcardImg_is_null = "请上传联系人身份证照片";
    }

    /**
     * 供应设备常量模块
     */
    public static class SupplyDevice{
        public static final String api_create = "supplyDevice";
        public static final String api_delete = "supplyDevice";
        public static final String api_getById = "getSupplyDeviceById";
        public static final String api_update = "supplyDevice";
        public static final String api_queryPage = "querySupplyDevicePage";
        public static final String api_getList = "getSupplierDeviceList";

        public static final String message_success_update = "supplyDevice update success";
        public static final String message_success_delete = "supplyDevice delete success";
        public static final String message_success_find = "supplyDevice find success";
        public static final String message_success_create = "supplyDevice create success";
        public static final String message_success_find_functions = "supplyDevice find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_iotSupplyDevice_is_no_exist = "iotSupplyDevice is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    /**
     * 供应设备数据类型
     */
    public static class SupplyDeviceDataType{
        public static final String api_create = "supplyDeviceDataType";
        public static final String api_delete = "supplyDeviceDataType";
        public static final String api_getById = "getSupplyDeviceDataTypeById";
        public static final String api_update = "supplyDeviceDataType";
        public static final String api_queryPage = "querySupplyDeviceDataTypePage";
        public static final String api_getList = "getSupplyDeviceDataTypeList";

        public static final String message_success_update = "supplyDeviceDataType update success";
        public static final String message_success_delete = "supplyDeviceDataType delete success";
        public static final String message_success_find = "supplyDeviceDataType find success";
        public static final String message_success_create = "supplyDeviceDataType create success";
        public static final String message_success_find_functions = "supplyDeviceDataType find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_iotSupplyDeviceDataType_is_no_exist = "iotSupplyDeviceDataType is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    /**
     * 设备字典模块常量
     */
    public static class DeviceDict{
        public static final String api_create = "deviceDict";
        public static final String api_delete = "deviceDict";
        public static final String api_getById = "getDeviceDictById";
        public static final String api_update = "deviceDict";
        public static final String api_queryPage = "queryDeviceDictPage";
        public static final String api_getList = "getDeviceDictList";

        public static final String message_success_update = "deviceDict update success";
        public static final String message_success_delete = "deviceDict delete success";
        public static final String message_success_find = "deviceDict find success";
        public static final String message_success_create = "deviceDict create success";
        public static final String message_success_find_functions = "deviceDict find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_iotDeviceDict_is_no_exist = "iotDeviceDict is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    /**
     * 设备模块常量
     */
    public static class Device{
        public static final String api_create = "device";
        public static final String api_delete = "device";
        public static final String api_getById = "getDeviceById";
        public static final String api_update = "device";
        public static final String api_queryPage = "queryDevicePage";
        public static final String api_getList = "getDeviceList";

        public static final String message_success_update = "device update success";
        public static final String message_success_delete = "device delete success";
        public static final String message_success_find = "device find success";
        public static final String message_success_create = "device create success";
        public static final String message_success_find_functions = "device find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_iotDevice_is_no_exist = "iotDevice is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    /**
     * 设备订单模块常量
     */
    public static class DeviceOrder{
        public static final String api_create = "deviceOrder";
        public static final String api_delete = "deviceOrder";
        public static final String api_getById = "getDeviceOrderById";
        public static final String api_update = "deviceOrder";
        public static final String api_queryPage = "queryDeviceOrderPage";
        public static final String api_getList = "getDeviceOrderList";

        public static final String message_success_update = "deviceOrder update success";
        public static final String message_success_delete = "deviceOrder delete success";
        public static final String message_success_find = "deviceOrder find success";
        public static final String message_success_create = "deviceOrder create success";
        public static final String message_success_find_functions = "deviceOrder find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_iotDeviceOrder_is_no_exist = "iotDeviceOrder is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    /**
     * 订单采购模块常量
     */
    public static class DevicePurchase{
        public static final String api_create = "devicePurchase";
        public static final String api_delete = "devicePurchase";
        public static final String api_getById = "getDevicePurchaseById";
        public static final String api_update = "devicePurchase";
        public static final String api_queryPage = "queryDevicePurchasePage";
        public static final String api_getList = "getDevicePurchaseList";

        public static final String message_success_update = "devicePurchase update success";
        public static final String message_success_delete = "devicePurchase delete success";
        public static final String message_success_find = "devicePurchase find success";
        public static final String message_success_create = "devicePurchase create success";
        public static final String message_success_find_functions = "devicePurchase find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_iotDevicePurchase_is_no_exist = "iotDevicePurchase is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    /**
     * 设备质检模块常量
     */
    public static class DeviceQualityInspectionPlan{
        public static final String api_create = "deviceQualityInspectionPlan";
        public static final String api_delete = "deviceQualityInspectionPlan";
        public static final String api_getById = "getDeviceQualityInspectionPlanById";
        public static final String api_update = "deviceQualityInspectionPlan";
        public static final String api_queryPage = "queryDeviceQualityInspectionPlanPage";
        public static final String api_getList = "getDeviceQualityInspectionPlanList";

        public static final String message_success_update = "deviceQualityInspectionPlan update success";
        public static final String message_success_delete = "deviceQualityInspectionPlan delete success";
        public static final String message_success_find = "deviceQualityInspectionPlan find success";
        public static final String message_success_create = "deviceQualityInspectionPlan create success";
        public static final String message_success_find_functions = "deviceQualityInspectionPlan find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_iotDeviceQualityInspectionPlan_is_no_exist = "iotDeviceQualityInspectionPlan is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    /**
     * 设备标签模块常量
     */
    public static class DeviceLabel{
        public static final String api_create = "deviceLabel";
        public static final String api_delete = "deviceLabel";
        public static final String api_getById = "getDeviceLabelById";
        public static final String api_update = "deviceLabel";
        public static final String api_queryPage = "queryDeviceLabelPage";
        public static final String api_getList = "getDeviceLabelList";

        public static final String message_success_update = "deviceLabel update success";
        public static final String message_success_delete = "deviceLabel delete success";
        public static final String message_success_find = "deviceLabel find success";
        public static final String message_success_create = "deviceLabel create success";
        public static final String message_success_find_functions = "deviceLabel find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_iotDeviceLabel_is_no_exist = "iotDeviceLabel is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    /**
     * 设备标签信息模块常量
     */
    public static class DeviceLabelInfo{
        public static final String api_create = "deviceLabelInfo";
        public static final String api_delete = "deviceLabelInfo";
        public static final String api_getById = "getDeviceLabelInfoById";
        public static final String api_update = "deviceLabelInfo";
        public static final String api_queryPage = "queryDeviceLabelInfoPage";
        public static final String api_getList = "getDeviceLabelInfoList";

        public static final String message_success_update = "deviceLabelInfo update success";
        public static final String message_success_delete = "deviceLabelInfo delete success";
        public static final String message_success_find = "deviceLabelInfo find success";
        public static final String message_success_create = "deviceLabelInfo create success";
        public static final String message_success_find_functions = "deviceLabelInfo find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_iotDeviceLabelInfo_is_no_exist = "iotDeviceLabelInfo is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

}
