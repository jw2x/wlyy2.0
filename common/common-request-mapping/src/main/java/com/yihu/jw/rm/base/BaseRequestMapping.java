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
    }

    /**
     * SAAS
     */
    public static class Saas extends Basic {
        public static final String PREFIX  = "/saas";
        public static final String AUDIT  = "/audit";
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
     * 模块
     */
    public static class Module extends Basic {
        public static final String PREFIX  = "/module";
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
    }

    /**
     * 系统字典项
     */
    public static class SystemDictEntry extends Basic {
        public static final String PREFIX  = "/system_dict_entry";
    }

    /**
     * 用户
     */
    public static class User extends Basic {
        public static final String PREFIX  = "/user";
        public static final String CHECK_USERNAME = "/check_username";
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
    }


    /**
     * 患者信息
     */
    public static class BasePatient extends Basic {
        public static final String PREFIX  = "/basePatient";
        public static final String getPatientById  = "/getPatientById";
    }

    /**
     * 基础人口基数
     */
    public static class BasePeopleNum extends Basic {
        public static final String PREFIX  = "/basePeopleNum";
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
