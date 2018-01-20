package com.yihu.jw.rm.iot;

/**
 * @author yeshijie on 2017/12/5.
 */
public class IotRequestMapping {

    public static final String api_iot_common = "svr-iot";
    public static final Integer api_iot_fail = -1;

    /**
     * 公共模块
     */
    public static class Common{
        public static final String company = api_iot_common + "/company";
        public static final String system_dict = api_iot_common + "/systemDict";
        public static final String file_upload = api_iot_common + "/fileUpload";
        public static final String product = api_iot_common + "/product";
        public static final String order = api_iot_common + "/order";
        public static final String device = api_iot_common + "/device";


        public static final String message_success_update = "update success";
        public static final String message_success_delete = "delete success";
        public static final String message_success_find = "find success";
        public static final String message_success_create = "create success";
        public static final String message_success_find_functions = "find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_is_no_exist = "is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    public static class System{
        public static final String findDictByCode = "findDictByCode";
    }

    /**
     * 文件上传模块常量
     */
    public static class FileUpload{
        public static final String api_upload_stream = "uploadStream";//文件流上传
        public static final String api_upload_stream_img = "uploadImg";//文件流上传图片
        public static final String api_upload_stream_attachment = "uploadattAchment";//文件流上传附件
        public static final String api_upload_string = "uploadString";//base64字符串上传

        public static final String message_success_upload = "上传成功";
        public static final String message_fail_upload = "上传失败";
        public static final String message_fail_upload_format = "不支持该文件格式上传";
        public static final String message_fail_jsonData_is_null = "jsonData is null";
    }

    /**
     * 企业模块
     */
    public static class Company{
        public static final String findCompanyPage = "findCompanyPage";
        public static final String addCompany = "addCompany";
        public static final String findCompanyById = "findCompanyById";
        public static final String findByBusinessLicense = "findByBusinessLicense";
        public static final String updCompany = "updCompany";
        public static final String delCompany = "delCompany";

        public static final String findCompanyCertPage = "findCompanyCertPage";
        public static final String findCompanyCertById = "findCompanyCertById";
        public static final String findCompanyCertByCompanyId = "findCompanyCertByCompanyId";
        public static final String addCompanyCert = "addCompanyCert";

        public static final String api_create = "company";
        public static final String api_delete = "company";
        public static final String api_getById = "getById";
        public static final String api_update = "company";
        public static final String api_queryPage = "queryPage";
        public static final String api_getList = "getList";

        public static final String message_success_update = "update success";
        public static final String message_success_delete = "delete success";
        public static final String message_success_find = "find success";
        public static final String message_success_create = "create success";
        public static final String message_success_find_functions = "find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_is_no_exist = "is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    /**
     * 产品模块常量
     */
    public static class Product {
        public static final String findProductPage = "findProductPage";
        public static final String findProductPageByCompanyId = "findProductPageByCompanyId";
        public static final String addProduct = "addProduct";
        public static final String findProductById = "findProductById";
        public static final String updProduct= "updProduct";
        public static final String delProduct= "delProduct";

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
        public static final String api_create = "createDevice";
        public static final String api_delete = "device";
        public static final String api_getById = "getDeviceById";
        public static final String api_update = "device";
        public static final String api_queryPage = "queryDevicePage";
        public static final String api_getList = "getDeviceList";

        public static final String isSnExist = "isSnExist";
        public static final String isSimExist = "isSimExist";
        public static final String importDevice = "importDevice";

        public static final String queryImportRecordPage = "queryImportRecordPage";

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
        public static final String findPage = "findPage";
        public static final String findById = "findById";
        public static final String delOrder = "delOrder";
        public static final String updOrder = "updOrder";
        public static final String createOrder = "createOrder";

        public static final String findPurcharsePage = "findPurcharsePage";

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
