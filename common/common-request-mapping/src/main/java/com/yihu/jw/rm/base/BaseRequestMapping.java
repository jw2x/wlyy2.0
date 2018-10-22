package com.yihu.jw.rm.base;

/**
 * Created by chenweida on 2017/5/19.
 * 基础模块的静态变量
 */
public class BaseRequestMapping {

    @Deprecated
    public static final String api_base_common = "/svr-bases";

    /**
     * 基础请求地址
     */
    private abstract static class Basic {
        public static final String CREATE =  "/create";
        public static final String DELETE = "/delete";
        public static final String UPDATE = "/update";
        public static final String PAGE = "/page";
        public static final String LIST = "/list";
        public static final String FINDBYID = "/findById";
        public static final String FIND_MODULE_BY_SAASID = "/findModuleBySaasId";
        public static final String STATUS = "/status";
    }

    /**
     * SAAS
     */
    public static class Saas extends Basic {
        public static final String PREFIX  = "/saas";
        public static final String SYSTEM_CONFIGURATION  = "/system_configuration";
        public static final String THEME_STYLE  = "/theme_style";
        public static final String RESET_SECRET  = "/reset_secret";
        public static final String AUDIT  = "/audit";
    }

    /**
     * 注册saas
     */
    public static class RegisterSaas extends Basic {
        public static final String PREFIX  = "/open/saas";
        public static final String REGISTER  = "/register";
        public static final String SEND_EMAIL  = "/sendEmail";
        public static final String NAME_IS_EXIST  = "/nameIsExist";
        public static final String FIND_BY_ID  = "/findById";
        public static final String SAAS_TYPE_DICT  = "/saasTypeDict";

    }

    /**
     * SAAS默认模块
     */
    public static class SaasDefaultModuleFunction extends Basic {
        public static final String PREFIX  = "/saas_default_module_function";
    }

    /**
     * Saas模块功能
     */
    public static class SaasModuleFunction extends Basic {
        public static final String PREFIX  = "/saas_module_function";
    }

    /**
     * SAAS名片
     */
    public static class SaasBusinessCard extends Basic {
        public static final String PREFIX  = "/saas_business_card";
        public static final String GENERATE = "/generate";
    }

    /**
     * SAAS类型
     */
    public static class SaasTypeDict extends Basic {
        public static final String PREFIX  = "/saas_type_dict";
        public static final String STATUS  = "/status";
        public static final String FIND_ALL_EXIST_CHECKED  = "/findAllExistChecked";
    }

    /**
     * 模块
     */
    public static class Module extends Basic {
        public static final String PREFIX  = "/module";
        public static final String STATUS  = "/status";
        public static final String IS_NAME_EXIST  = "/isNameExist";
        public static final String FIND_ALL  = "/findAll";

    }

    /**
     * 接口
     */
    public static class Interface extends Basic {
        public static final String PREFIX  = "/interface";
        public static final String STATUS  = "/status";
        public static final String IS_NAME_EXIST  = "/isNameExist";
        public static final String FIND_ALL  = "/findAll";
    }

    /**
     * 租户接口
     */
    public static class SaasInterface extends Basic {
        public static final String PREFIX  = "/saasInterface";
        public static final String STATUS  = "/status";
        public static final String IS_NAME_EXIST  = "/isNameExist";
        public static final String FIND_ALL  = "/findAll";
    }

    /**
     * 功能
     */
    public static class Function extends Basic {
        public static final String PREFIX  = "/function";
    }

    /**
     * 模块功能
     */
    public static class ModuleFunction extends Basic {
        public static final String PREFIX  = "/module_function";
    }

    /**
     * 主题
     */
    public static class Theme extends Basic {
        public static final String PREFIX  = "/theme";
        public static final String CHECK_STYLE = "/check_style";
    }

    /**
     * 系统字典
     */
    public static class SystemDict extends Basic {
        public static final String PREFIX  = "/system_dict";
        public static final String ALL  = "/query_all";
        public static final String QUERY_BY_SAASID = "/queryBySaasId";
        public static final String QUERY_BY_TYPE  = "/query_by_type";
    }

    /**
     * 系统字典项
     */
    public static class SystemDictEntry extends Basic {
        public static final String PREFIX  = "/system_dict_entry";
        public static final String PAGE_SAASID  = "/pageSaasId";
    }

    /**
     * 用户
     */
    public static class User extends Basic {
        public static final String PREFIX  = "/user";
        public static final String CHECK_USERNAME = "/check_username";
        public static final String CHECK_MOBILE = "/check_mobile";
    }

    /**
     * 用户角色
     */
    public static class UserRole extends Basic {
        public static final String PREFIX  = "/user_role";
    }

    /**
     * 用户取消的模块或者功能
     */
    public static class UserHideModuleFunction extends Basic {
        public static final String PREFIX  = "/user_hide_module_function";
    }

    /**
     * 角色
     */
    public static class Role extends Basic {
        public static final String PREFIX  = "/role";
    }

    /**
     * 菜单
     */
    public static class Menu extends Basic {
        public static final String PREFIX  = "/menu";
        public static final String STATUS  = "/status";
        public static final String IS_NAME_EXIST  = "/isNameExist";
        public static final String MOVE_UP  = "/moveUp";
        public static final String MOVE_DOWN  = "/moveDown";
        public static final String FIND_ALL  = "/findAll";
    }

    /**
     * 错误码
     */
    public static class ErrorCode extends Basic {
        public static final String PREFIX  = "/error";
        public static final String INIT_WITHOUT_CONDITION  = "/initWithoutCondition";
    }

    /**
     * 通知公告
     */
    public static class Notice extends Basic {
        public static final String PREFIX  = "/notice";
        public static final String RELEASE  = "/release";
    }

    /**
     * 用户通知公告
     */
    public static class UserNotice extends Basic {
        public static final String PREFIX  = "/userNotice";
    }

    /**
     * 文件上传
     */
    public static class FileUpload extends Basic {
        public static final String PREFIX  = "/open/fileUpload";
        public static final String UPLOAD_STREAM_IMG  = "/upload_stream_img";
        public static final String UPLOAD_STREAM  = "/upload_stream";
        public static final String UPLOAD_STRING  = "/upload_string";
        public static final String UPLOAD_STREAM_ATTACHMENT  = "/upload_stream_attachment";
    }

    /**
     * 角色权限
     */
    public static class RoleAuthority extends Basic {
        public static final String PREFIX  = "/role_authority";
    }

    /**
     * 微信
     */
    public static class WeChat extends Basic {
        public static final String PREFIX  = "/wechat";
        public static final String wechat_base ="/wechatBase";
        public static final String api_success ="success";

        public static final String getWechatInfos ="/getWechatInfos";
        public static final String saveWxAndSaas ="/saveWxAndSaas";
        public static final String updateWxAndSaas ="/updateWxAndSaas";
        public static final String findWxWechatSingle ="/findWxWechatSingle";
        public static final String findWxWechatExist ="/findWxWechatExist";
        public static final String findWechatCombo ="/findWechatCombo";

        public static final String findWechatImgGroup ="/findWechatImgGroup";
        public static final String createImgGroup ="/createImgGroup";
        public static final String findImgGroupExist ="/findImgGroupExist";
        public static final String updateImgGroup ="/updateImgGroup";
        public static final String deleteImgGroup ="/deleteImgGroup";
        public static final String saveImg ="/saveImg";
        public static final String findImg ="/findImg";
        public static final String findGraphicMessageSingle ="/findGraphicMessageSingle";
        public static final String saveImgGroup ="/saveImgGroup";
        public static final String deleteImgGroupRelation ="/deleteImgGroupRelation";

        public static final String saveWxReplyScene ="/saveWxReplyScene";
        public static final String findDefaultReply ="/findDefaultReply";
        public static final String findWxReplySceneExist ="/findWxReplySceneExist";
        public static final String findWxReplyScene ="/findWxReplyScene";
        public static final String saveWxTemp ="/saveWxTemp";
        public static final String findWxtemp ="/findWxtemp";
        public static final String saveWxTempConfig ="/saveWxTempConfig";
        public static final String findWxTempConfigList ="/findWxTempConfigList";
        public static final String findWxTemplateConfig ="/findWxTemplateConfig";
        public static final String getAllTemp ="/getAllTemp";
        public static final String getusersummary ="/getusersummary";
        public static final String getusercumulate ="/getusercumulate";

    }

    /**
     * 短信网关
     */
    public static class SmsGateway extends Basic {
        public static final String PREFIX  = "/sms_gateway";
        public static final String SEND  = "/send";
    }

    /**
     * 短信模板
     */
    public static class SmsTemplate extends Basic {
        public static final String PREFIX  = "/sms_template";
    }

    /**
     * 短信模板
     */
    public static class Sms extends Basic {
        public static final String PREFIX  = "/sms";
    }

    /**
     * 服务包
     */
    public static class ServicePackage extends Basic{
        public static final String PREFIX  = "/service_package";
    }

    /**
     * 服务包类目
     */
    public static class ServicePackageNormcat extends Basic{
        public static final String PREFIX  = "/service_package_normcat";
    }

    /**
     * 康复计划
     */
    public static class BaseRehabilitation extends Basic{
        public static final String PREFIX  = "/base_rehabilitation";
        public static final String CREATELOG  = "/createlog";
        public static final String FINDBYID  = "/findFinishById";
    }

    /**
     * 统计指标
     */
    public static class Quota extends Basic{
        public static final String PREFIX  = "/quota";
    }

    /**
     * 统计维度
     */
    public static class Dimension extends Basic{
        public static final String PREFIX  = "/dimension";
    }

    /**
     * job
     */
    public static class JobConfig extends Basic{
        public static final String PREFIX  = "/job_config";
    }

    /**
     * im
     */
    public static class ImGetuiConfig extends Basic{
        public static final String PREFIX  = "/im_getui";
    }
    /**
     * 家庭成员管理
     */
    public static class PatientMember extends Basic{
        public static final String PREFIX = "/patient_member";
    }

    /**
     * 医生评价
     */
    public static class Score extends Basic{
        public static final String PREFIX = "/score";
    }

    /**
     * 团队
     */
    public static class team extends Basic{
        public static final String PREFIX = "/team";
    }


    /**
     * 城市字典
     */
    public static class BaseCity extends Basic {
        public static final String PREFIX  = "/baseCity";
    }


    /**
     * 医生基础信息
     */
    public static class BaseDoctor extends Basic {
        public static final String PREFIX  = "/baseDoctor";
        public static final String DOCINFO  = "/docInfo";
        public static final String docFullInfo  = "/docFullInfo";
        public static final String docOrgTreeInfo  = "/docOrgTreeInfo";
    }


    /**
     * 医生职业信息
     */
    public static class BaseDoctorHospital extends Basic {
        public static final String PREFIX  = "/baseDoctorHospital";
    }


    /**
     * 医生角色字典
     */
    public static class BaseDoctorRoleDict extends Basic {
        public static final String PREFIX  = "/baseDoctorRoleDict";
    }


    /**
     * 机构信息
     */
    public static class BaseOrg extends Basic {
        public static final String PREFIX  = "/baseOrg";
        public static final String baseInfoList  = "/baseInfoList";
        public static final String check_code  = "/check_code";
        public static final String getOrgAreaTree  = "/getOrgAreaTree";
    }


    /**
     * 患者信息
     */
    public static class BasePatient extends Basic {
        public static final String PREFIX  = "/basePatient";
        public static final String getPatientById  = "/getPatientById";
        public static final String getBaseInfo  = "/getBaseInfo";
    }

    /**
     * 基础人口基数
     */
    public static class BasePopulation extends Basic {
        public static final String PREFIX  = "/basePopulation";
        public static final String CHECK_POPULATION_IS_EXIST  = "/checkBasePopulationIsExist";
        public static final String POPULATION_BATCH_IMPORT  = "/basePopulationBatchImport";
        public static final String POPULATION_BATCH_ERROR_DOENLOAD  = "/basePopulationBatchErrorDownLoad";
        public static final String POPULATION_BODY_BATCH_IMPORT  = "/basePopulationBodyBatchImport";

    }

    /**
     * 年份
     */
    public static class BaseYear extends Basic {
        public static final String PREFIX  = "/baseYear";
    }


    /**
     * 省份字典
     */
    public static class BaseProvince extends Basic {
        public static final String PREFIX  = "/baseProvince";
    }

    /**
     * 团队信息
     */
    public static class BaseTeam extends Basic {
        public static final String PREFIX  = "/baseTeam";
    }


    /**
     * 街道字典
     */
    public static class BaseStreet extends Basic {
        public static final String PREFIX  = "/baseStreet";
    }

    /**
     * 居委会字典
     */
    public static class BaseCommittee extends Basic {
        public static final String PREFIX  = "/baseCommittee";
    }


    /**
     * 团队成员
     */
    public static class BaseTeamMember extends Basic {
        public static final String PREFIX  = "/baseTeamMember";
    }


    /**
     * 区县字典
     */
    public static class BaseTown extends Basic {
        public static final String PREFIX  = "/baseTown";
    }


    /**
     * 健康问题字典
     */
    public static class DictHealthProblem extends Basic {
        public static final String PREFIX  = "/dictHealthProblem";
    }

    /**
     * 健康问题字典
     */
    public static class DictDisease extends Basic {
        public static final String PREFIX  = "/dictDisease";
    }


    /**
     * 医院科室字典
     */
    public static class DictHospitalDept extends Basic {
        public static final String PREFIX  = "/dictHospitalDept";
        public static final String queryDeptByOrg  = "/queryDeptByOrg";
    }


    /**
     * ICD10字典
     */
    public static class DictIcd10 extends Basic {
        public static final String PREFIX  = "/dictIcd10";
    }


    /**
     * 职称字典
     */
    public static class DictJobTitle extends Basic {
        public static final String PREFIX  = "/dictJobTitle";
    }


    /**
     * 药品字典
     */
    public static class DictMedicine extends Basic {
        public static final String PREFIX  = "/dictMedicine";
    }
   /**
     * 药品剂型
     */
    public static class DictMedicineDosageForm extends Basic {
        public static final String PREFIX  = "/dictMedicineDosageForm";
    }
   /**
     * 药品字典科目类别
     */
    public static class DictMedicineSubject extends Basic {
        public static final String PREFIX  = "/dictMedicineSubject";
    }
   /**
     * 药品分发机构
     */
    public static class DictMedicineDistributeOrg extends Basic {
        public static final String PREFIX  = "/dictMedicineDistributeOrg";
    }

    /**
     * app版本升级
     */
    public static class AppVersion extends Basic {
        public static final String PREFIX  = "/appVersion";
    }

    /**
     * 基础消息
     */
    public static class BaseMessage extends Basic {
        public static final String PREFIX  = "/baseMessage";
    }

    /**
     * 基础消息类型
     */
    public static class BaseMessageType extends Basic {
        public static final String PREFIX  = "/baseMessageType";
    }

    /**
     * 基于MQ的消息推送
     */
    public static class MqMessage extends Basic {
        public static final String PREFIX  = "/mq_message";
    }

    /**
     * 基于MQ的消息推送订阅者
     */
    public static class MqMessageSubscriber extends Basic {
        public static final String PREFIX  = "/mq_message_subscriber";
    }

}
