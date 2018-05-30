package com.yihu.jw.rm.rehabilitation;

/**
 * Created by humingfen on 2018/4/25 .
 */
public class RehabilitationRequestMapping {

    public static final String api_rehabilitation_common = "svr-rehabilitation";

    /**
     * 公共模块
     */
    public static class Common {
        public static final String message_success_update = "update success";
        public static final String message_success_delete = "delete success";
        public static final String message_success_find = "find success";
        public static final String message_success_create = "create success";

        public static final String message_success_find_functions = "message_success_find_functions";
    }

    public static class Information {
        public static final String information = api_rehabilitation_common + "/information";

        public static final String api_create = "createInformation";
        public static final String api_delete = "delInformation";
        public static final String api_getById = "getInformationById";
        public static final String api_update = "updateInformation";

        public static final String findInformationById = "findInformationById";
        public static final String findInformationByPatientId = "findInformationByPatientId";
        public static final String findInformationPage = "findInformationPage";

    }

    public static class TreatmentProgram {
        public static final String treatmentProgram = api_rehabilitation_common + "/treatmentProgram";

        public static final String findTreatmentProgramPage = "findTreatmentProgramPage";

        public static final String api_create = "createTreatmentProgram";
        public static final String api_delete = "delTreatmentProgram";
        public static final String api_getById = "getTreatmentProgramById";
        public static final String api_update = "updateTreatmentProgram";

        public static final String findTreatmentProgramById = "findTreatmentProgramById";
    }

    public static class Planning {
        public static final String planning = api_rehabilitation_common + "/planning";

        public static final String findPlanningPage = "findPlanningPage";
        public static final String findTreatmentByProgramId = "findTreatmentByProgramId";

        public static final String api_create = "createPlanning";
        public static final String api_delete = "delPlanning";
        public static final String api_getById = "getPlanningById";
        public static final String api_update = "updatePlanning";

        public static final String findPlanningById = "findPlanningById";
    }

    public static class Performance {
        public static final String performance = api_rehabilitation_common + "/performance";

        public static final String findPerformancePage = "findPerformancePage";

        public static final String api_create = "createPerformance";
        public static final String api_delete = "delPerformance";
        public static final String api_getById = "getPerformanceById";
        public static final String api_update = "updatePerformance";

        public static final String findPerformanceById = "findPerformanceById";
    }
}