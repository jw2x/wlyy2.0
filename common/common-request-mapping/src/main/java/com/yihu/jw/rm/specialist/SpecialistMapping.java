package com.yihu.jw.rm.specialist;

/**
 * Created by Trick on 2018/4/25.
 */
public class SpecialistMapping {

    public static final String api_specialist_common = "svr-specialist";
    public static final Integer api_specialist_fail = -1;
    public static final String api_success ="success";
    public static final String doctor_exist ="doctor_exist";
    public static final String team_exist ="team_exist";

    public static class specialist{

        public static final String createSpecialists = "/createSpecialists";
        public static final String createSpecialistRelation = "/createSpecialistRelation";
        public static final String createConsult = "/createConsult";
        public static final String createArticle = "/createArticle";
        public static final String findSpecialistPatientRelation = "/findSpecialistPatientRelation";
        public static final String findSpecialistPatientRelationCout = "/findSpecialistPatientRelationCout";
        public static final String findNoLabelPatientRelation ="/findNoLabelPatientRelation";
        public static final String saveHealthAssistant ="/saveHealthAssistant";
        public static final String findPatientRelatioByAssistant ="/findPatientRelatioByAssistant";
        public static final String getPatientByLabel ="/getPatientByLabel";
        public static final String getLabelpatientCount ="/getLabelpatientCount";
        public static final String getAssistantPatientCount ="/getAssistantPatientCount";
        public static final String getDoctorPatientByName ="/getDoctorPatientByName";
        public static final String findPatientNoAssistant ="/findPatientNoAssistant";
        public static final String signSpecialistTeam ="/signSpecialistTeam";
        public static final String agreeSpecialistTeam ="/agreeSpecialistTeam";
        public static final String findPatientSigninfo ="/findPatientSigninfo";
        public static final String findPatientTeamList ="/findPatientTeamList";
        public static final String findPatientSignSpecialist ="/findPatientSignSpecialist";
        public static final String findPatientSignSpecialistInfo ="/findPatientSignSpecialistInfo";
        public static final String findDoctorAndDoctorHealthBySpecialDoctor ="/findDoctorAndDoctorHealthBySpecialDoctor";
        public static final String findSpecialistSignFamilyPatientCout ="/findSpecialistSignFamilyPatientCout";
        public static final String getSpecialistSignFamilyPatientByName ="/getSpecialistSignFamilyPatientByName";
        public static final String getPatientAndDiseaseByDoctor ="/getPatientAndDiseaseByDoctor";



    }

    public static  class common{
        public static final String file_upload =  "/fileUpload";
    }

    public static class screen{
        public static final String getScreenResult ="/getScreenResult";
        public static final String getScreenCount = "/getScreenCount";
        public static final String getScreenResultDetail = "/getScreenResultDetail";
    }

    public static class rehabilitation{
        public static final String findRehabilitationPlan = "/findRehabilitationPlan";
        public static final String createRehabilitationPlanTemplate = "/createRehabilitationPlanTemplate";
        public static final String deleteRehabilitationPlanTemplate = "/deleteRehabilitationPlanTemplate";
        public static final String createRehabilitationTemplateDetail = "/createRehabilitationTemplateDetail";
        public static final String findRehabilitationPlanTemplate = "/findRehabilitationPlanTemplate";
        public static final String findTemplateDetailByTemplateId = "/findTemplateDetailByTemplateId";
        public static final String updateRehabilitationTemplateDetail = "/updateRehabilitationTemplateDetail";
        public static final String createPatientRehabilitationPlan = "/createPatientRehabilitationPlan";
        public static final String createServiceQrCode ="/createServiceQrCode";
        public static final String checkAfterQrCode = "checkAfterQrCode";
        public static final String findRehabilitationPlanList = "/findRehabilitationPlanList";
        public static final String findRehabilitationPlanDetailList = "/findRehabilitationPlanDetailList";
        public static final String calendarPlanDetail = "/calendarPlanDetail";
        public static final String calendarPlanDetailList = "/calendarPlanDetailList";
        public static final String saveGuidanceMessage = "/saveGuidanceMessage";
        public static final String serviceItemList = "/serviceItemList";
        public static final String serviceItem = "/serviceItem";
        public static final String updateStatusRehabilitationOperate = "/updateStatusRehabilitationOperate";
        public static final String patientRehabilitationDetail = "/patientRehabilitationDetail";
        public static final String recentPlanDetailRecord = "/recentPlanDetailRecord";
        public static final String saveRehabilitationOperateRecodr="/saveRehabilitationOperateRecodr";
        public static final String updateNoteAndImageRehabilitationOperate = "/updateNoteAndImageRehabilitationOperate";

        public static final String findServiceItemsByHospital = "/findServiceItemsByHospital";
        public static final String serviceDoctorList = "/serviceDoctorList";
        public static final String dailyJob = "/dailyJob";
        public static final String appCalendarPlanDetailList = "/appCalendarPlanDetailList";
        public static final String updatePlanDetailStatusById = "/updatePlanDetailStatusById";
        public static final String updatePlanStatusById = "/updatePlanStatusById";
    }

    public static class serviceItem{
        public static final String createServiceItem = "/createServiceItem";
        public static final String getServiceItem = "/getServiceItem";
        public static final String batchDelete = "/batchDelete";
        public static final String updateServiceItem = "/updateServiceItem";
        public static final String createEvaluate = "/createEvaluate";
        public static final String getEvaluate = "/getEvaluate";
        public static final String updateEvaluate = "/updateEvaluate";
        public static final String createHospitalServiceItem = "/createHospitalServiceItem";
        public static final String selectByHospital = "/selectByHospital";
        public static final String selectById="/selectById";
        public static final String selectItemByHospital= "/selectItemByHospital";
        public static final String selectByOperate = "/selectByOperate";
        public static final String selectByCondition="/selectByCondition";
        public static final String deleteHospitalItem = "/deleteHospitalItem";
        public static final String selectByHospital1 = "/selectByHospital1";
    }
}
