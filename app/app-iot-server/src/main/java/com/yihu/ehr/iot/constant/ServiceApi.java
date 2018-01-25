package com.yihu.ehr.iot.constant;

/**
 * @author yeshijie on 2018/1/22.
 */
public class ServiceApi {
    public ServiceApi() {
    }

    public static class Company {
        public static final String Base = "wg/company/";
        public static final String FindCompanyPage = Base +"findCompanyPage";
        public static final String AddCompany = Base +"addCompany";
        public static final String FindCompanyById = Base +"findCompanyById";
        public static final String FindByBusinessLicense = Base +"findByBusinessLicense";
        public static final String UpdCompany = Base +"updCompany";
        public static final String DelCompany = Base +"delCompany";

        public static final String FindCompanyCertPage = Base +"findCompanyCertPage";
        public static final String FindCompanyCertById = Base +"findCompanyCertById";
        public static final String FindCompanyCertByCompanyId = Base +"findCompanyCertByCompanyId";
        public static final String AddCompanyCert = Base +"addCompanyCert";

        public Company() {
        }
    }

    public static class System{
        public static final String Base = "wg/systemDict/";
        public static final String FindDictByCode = Base +"findDictByCode";
    }

    public static class FileUpload{
        public static final String Base = "wg/fileUpload/";
        public static final String UploadStream = Base +"uploadStream";//文件流上传
        public static final String UploadImg = Base +"uploadImg";//文件流上传图片
        public static final String UploadattAchment = Base +"uploadattAchment";//文件流上传附件
        public static final String UploadString = Base +"uploadString";//base64字符串上传

    }


    public static class Product {
        public static final String Base = "wg/product/";
        public static final String FindProductPage = Base +"findProductPage";
        public static final String FindProductPageByCompanyId = Base +"findProductPageByCompanyId";
        public static final String AddProduct = Base +"addProduct";
        public static final String FindProductById = Base +"findProductById";
        public static final String UpdProduct= Base +"updProduct";
        public static final String DelProduct= Base + "delProduct";

        public Product() {
        }
    }

    public static class Device{
        public static final String Base = "wg/device/";
        public static final String CreateDevice = Base +"createDevice";
        public static final String GetDeviceById = Base +"getDeviceById";
        public static final String QueryDevicePage = Base +"queryDevicePage";
        public static final String GetDeviceList = Base +"getDeviceList";

        public static final String IsSnExist = Base +"isSnExist";
        public static final String IsSimExist = Base +"isSimExist";
        public static final String UpdSim = Base +"updSim";
        public static final String ImportDevice = Base +"importDevice";
        public static final String IsImportDevice = Base +"isImportDevice";

        public static final String QueryImportRecordPage = Base +"queryImportRecordPage";

        public Device() {
        }
    }

    public static class DeviceOrder{
        public static final String Base = "wg/order/";
        public static final String FindPage = Base +"findPage";
        public static final String FindById = Base +"findById";
        public static final String DelOrder = Base +"delOrder";
        public static final String UpdOrder = Base +"updOrder";
        public static final String CreateOrder = Base +"createOrder";

        public static final String FindPurcharsePage = Base +"findPurcharsePage";

        public DeviceOrder() {
        }
    }

    public static class Quality{
        public static final String Base = "wg/quality/";
        public static final String CreateDevice = Base +"createDevice";
        public static final String AddQualityPlan = Base +"addQualityPlan";
        public static final String DelQualityPlan = Base +"delQualityPlan";
        public static final String UpdQualityPlan = Base +"updQualityPlan";
        public static final String CompleteQualityPlan = Base +"completeQualityPlan";
        public static final String QueryQualityPlanPage = Base +"queryQualityPlanPage";
        public static final String FindById = Base +"findById";

        public Quality() {
        }
    }

}
