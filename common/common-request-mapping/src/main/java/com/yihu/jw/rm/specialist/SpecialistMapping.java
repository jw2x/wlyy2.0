package com.yihu.jw.rm.specialist;

/**
 * Created by Trick on 2018/4/25.
 */
public class SpecialistMapping {

    public static final String api_specialist_common = "svr-specialist";
    public static final Integer api_specialist_fail = -1;
    public static final String api_success ="succes";
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





    }

    public static class screen{
        public static final String getScreenResult ="/getScreenResult";
        public static final String getScreenCount = "/getScreenCount";
        public static final String getScreenResultDetail = "/getScreenResultDetail";
    }

    public static class rehabilitation{
        public static final String findRehabilitationPlan = "/findRehabilitationPlan";
    }
}
