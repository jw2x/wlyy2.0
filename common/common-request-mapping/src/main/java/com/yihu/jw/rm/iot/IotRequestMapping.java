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
        public static final String quality = api_iot_common + "/quality";
        public static final String patientDevice = api_iot_common + "/patientDevice";
        public static final String wlyy = api_iot_common + "/wlyy";


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
        public static final String delCompanyCert = "delCompanyCert";
        public static final String changePassWord = "changePassWord";

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

    }

    /**
     * 产品模块常量
     */
    public static class Product {
        public static final String findProductPage = "findProductPage";
        public static final String data_sets = "data_sets";
        public static final String metadata = "metadata";
        public static final String findProductPageByCompanyId = "findProductPageByCompanyId";
        public static final String addProduct = "addProduct";
        public static final String findProductById = "findProductById";
        public static final String updProduct = "updProduct";
        public static final String delProduct = "delProduct";
        public static final String maintenanceUnitById = "maintenanceUnitById";

    }

    /**
     * 居民设备模块常量
     */
    public static class PatientDevice {
        public static final String findByPage = "findByPage";
        public static final String addPatientDevice = "addPatientDevice";
        public static final String findById = "findById";
        public static final String updPatientDevice= "updPatientDevice";
        public static final String delPatientDevice= "delPatientDevice";
        public static final String findByDeviceSnAndUserType= "findByDeviceSnAndUserType";
        public static final String findByPatient= "findByPatient";
        public static final String findByPatientAndDeviceSn= "findByPatientAndDeviceSn";
        public static final String findByDeviceSnAndCategoryCode= "findByDeviceSnAndCategoryCode";
        public static final String findByDeviceSnAndCategoryCodeAndUserType= "findByDeviceSnAndCategoryCodeAndUserType";
        public static final String updatePatientDevice= "updatePatientDevice";
        public static final String findListByPatient= "findListByPatient";

        public static final String findAllLocations= "findAllLocations";
        public static final String findLocationByIdCard= "findLocationByIdCard";
        public static final String findLocationBySn= "findLocationBySn";
        public static final String deleteLocation= "deteleLocation";
        public static final String updateLocation= "updateLocation";


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

    }

    /**
     * 设备模块常量
     */
    public static class Device{
        public static final String api_create = "createDevice";
        public static final String api_delete = "delDevice";
        public static final String api_getById = "getDeviceById";
        public static final String api_update = "updDevice";
        public static final String api_queryPage = "queryDevicePage";
        public static final String api_getList = "getDeviceList";

        public static final String isSnExist = "isSnExist";
        public static final String isSimExist = "isSimExist";
        public static final String updSim = "updSim";
        public static final String importDevice = "importDevice";
        public static final String isImportDevice = "isImportDevice";

        public static final String queryImportRecordPage = "queryImportRecordPage";

        public static final String message_success_update = "device update success";
        public static final String message_success_delete = "device delete success";
        public static final String message_success_find = "device find success";
        public static final String del_message_fail = "该设备已绑定居民，不允许删除";
        public static final String message_success_create = "device create success";
        public static final String message_success_find_functions = "device find success";

    }

    /**
     * 设备订单模块常量
     */
    public static class DeviceOrder{
        public static final String findPage = "findPage";
        public static final String findById = "findById";
        public static final String delOrder = "delOrder";
        public static final String delPurchase = "delPurchase";
        public static final String updOrder = "updOrder";
        public static final String createOrder = "createOrder";

        public static final String findPurcharsePage = "findPurcharsePage";
        public static final String findPurcharseById = "findPurcharseById";
        public static final String findQualityPage = "findQualityPage";

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
        public static final String delete_order_fail_message_device = "该订单有关联设备，不允许删除";
        public static final String delete_purchase_fail_message_device = "该采购清单有关联设备，不允许删除";

    }


    /**
     * 设备质检模块常量
     */
    public static class DeviceQuality{
        public static final String addQualityPlan = "addQualityPlan";
        public static final String delQualityPlan = "delQualityPlan";
        public static final String updQualityPlan = "updQualityPlan";
        public static final String completeQualityPlan = "completeQualityPlan";
        public static final String completePlanByPurchaseId = "completePlanByPurchaseId";
        public static final String queryQualityPlanPage = "queryQualityPlanPage";
        public static final String findById = "findById";

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

    }

}
