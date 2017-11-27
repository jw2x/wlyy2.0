package com.yihu.jw.rm.wlyy;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class WlyyRequestMapping {

    public static final String api_common = "svr-wlyy";

    //协议模块常量
    public static class Agreement {
        public static final String api_create = "agreement";
        public static final String api_delete = "agreement";
        public static final String api_getById = "getAgreementById";
        public static final String api_update = "agreement";
        public static final String api_queryPage = "queryAgreementPage";
        public static final String api_getList = "getAgreementList";

        public static final String message_success_update = "agreement update success";
        public static final String message_success_delete = "agreement delete success";
        public static final String message_success_find = "agreement find success";
        public static final String message_success_create = "agreement create success";
        public static final String message_success_find_functions = "agreement find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_price_is_null = "price is null";
        public static final String message_fail_wlyyAgreement_is_no_exist = "wlyyAgreement is no exist";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasId_is_null = "saasId is null";
    }

    //协议模块常量
    public static class AgreementKpi {
        public static final String api_create = "agreementKpi";
        public static final String api_delete = "agreementKpi";
        public static final String api_getById = "getaAreementKpiById";
        public static final String api_update = "agreementKpi";
        public static final String api_queryPage = "queryAgreementKpiPage";
        public static final String api_getList = "getAagreementKpiList";

        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_wlyyAgreementKpi_is_no_exist = "wlyyAgreementKpi is no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_kpiName_is_null = "kpiName is null";
        public static final String message_fail_type_is_null = "type is null";
        public static final String message_fail_patientId_is_null = "patientId is null";
        public static final String message_fail_signId_is_null = "signId is null";
        public static final String message_fail_kpiId_is_null = "kpiId is null";
        public static final String message_fail_agreementId_is_null = "agreementId is null";
        public static final String message_fail_agreement_is_null = "agreement is null";

        public static final String message_success_update = "agreementKpi update success";
        public static final String message_success_delete = "agreementKpi delete success";
        public static final String message_success_find = "agreementKpi find success";
        public static final String message_success_create = "agreementKpi create success";
        public static final String message_success_find_functions = "agreementKpi find success";
    }

    //协议模块常量
    public static class AgreementKpiLog {
        public static final String api_create = "agreementKpiLog";
        public static final String api_delete = "agreementKpiLog";
        public static final String api_getById = "getAgreementKpiLogById";
        public static final String api_update = "agreementKpiLog";
        public static final String api_queryPage = "queryAgreementKpiLogPage";
        public static final String api_getList = "getAgreementKpiLogList";

        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_id_no_exist = "id no exist";

        public static final String message_success_update = "agreementKpiLog update success";
        public static final String message_success_delete = "agreementKpiLog delete success";
        public static final String message_success_find = "agreementKpiLog find success";
        public static final String message_success_create = "agreementKpiLog create success";
        public static final String message_success_find_functions = "agreementKpiLog find success";
    }

    //协议模块常量
    public static class SignFamily {
        public static final String api_create = "signFamily";
        public static final String api_delete = "signFamily";
        public static final String api_getById = "getSignFamilyById";
        public static final String api_update = "signFamily";
        public static final String api_queryPage = "querySignFamilyPage";
        public static final String api_getList = "getSignFamilyList";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_type_is_null = "type is null";
        public static final String message_fail_idCard_is_null = "idCard is null";
        public static final String message_fail_ssc_is_null = "ssc is null";
        public static final String message_fail_hospital_is_null = "hospital is null";
        public static final String message_fail_hospitalName_is_null = "hospitalName is null";
        public static final String message_fail_expense_is_null = "expense is null";
        public static final String message_fail_expenseStatus_is_null = "expenseStatus is null";
        public static final String message_fail_agreementId_is_null = "agreementId is null";

        public static final String message_success_update = "signFamily update success";
        public static final String message_success_delete = "signFamily delete success";
        public static final String message_success_find = "signFamily find success";
        public static final String message_success_create = "signFamily create success";
        public static final String message_success_find_functions = "signFamily find success";

    }

    /*
    广告相关操作
     */
    public static class Advertisement {
        public static final String api_common = "advertisement";
        public static final String api_create = "create";
        public static final String api_delete = "delete";
        public static final String api_getById = "getById";
        public static final String api_update = "update";
        public static final String api_queryPage = "queryPage";
        public static final String api_getList = "getList";
        public static final String api_getListByPatientId = "getListByPatientId";
        public static final String api_getListByHttp = "getListByHttp";
        public static final String api_getListByIp = "getListByIp";

        public static final String message_success_update = "advertisement update success";
        public static final String message_success_delete = "advertisement delete success";
        public static final String message_success_find = "advertisement find success";
        public static final String message_success_create = "advertisement create success";
        public static final String message_success_find_functions = "advertisement find success";

        public static final String message_fail_name_is_null = "name is null";
        public static final String message_fail_id_no_exist = "id no exist";
        public static final String message_fail_status_is_null = "status is null";
        public static final String message_fail_id_is_null = "id is null";
        public static final String message_fail_id_exist = "id exist";
        public static final String message_fail_saasid_is_null = "saasid is null";
        public static final String message_fail_picture_is_null = "picture is null";
        public static final String message_fail_wlyyAdvertisement_is_not_exist = "WlyyAdvertisement is not exist";
    }
}
