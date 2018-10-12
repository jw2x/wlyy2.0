package com.yihu.jw.rm.health.house;


/**
 * Created by Trick on 2018/2/7.
 */
public class HealthyHouseMapping {

    public static final String api_healthyHouse_common = "svr-healthy-house";
    public static final String api_success = "succes";


    public static class HealthyHouse {

        //系统字典
        public static class SystemDict {
            public static final String CREATE = "/create/systemDict";
            public static final String DELETE = "/delete/systemDict";
            public static final String UPDATE = "/update/systemDict";
            public static final String PAGE = "/page/systemDicts";
            public static final String LIST = "/list/systemDicts";
            public static final String GETDICTBYID = "/getDictionaryById";
            public static final String GETDICTBYPHONETICCODE = "/getDictionaryByPhoneticCode";
            public static final String ISDICTNAMEEXISTS = "/isDictNameExists";
            public static final String ISDICTCODEEXISTS = "/isDictCodeExists";
        }

        //系统字典项
        public static class SystemDictEntry {
            public static final String CREATE = "/create/systemDictEntry";
            public static final String DELETE = "/delete/systemDictEntry";
            public static final String UPDATE = "/update/systemDictEntry";
            public static final String PAGE = "/page/systemDictEntrys";
            public static final String LIST = "/list/systemDictEntrys";
            public static final String GETDICTENTRYBYDICTIDANDCODE = "/getDictEntryByDictIdAndCode";
            public static final String GETDICTENTRYBYDICTIDANDNAME = "/getDictEntryByDictIdAndName";
            public static final String DELETEBYDICTIDANDCODE = "/deleteByDictIdAndCode";
            public static final String ISEXISTSDICTENTRYBYDICTIDANDCODE = "/isExistsDictEntryByDictIdAndCode";
        }

        //设施
        public static class Facilities {
            public static final String CREATE = "/create/facilities";
            public static final String DELETE = "/delete/facilities";
            public static final String UPDATE = "/update/facilities";
            public static final String PAGE = "/page/facilities";
            public static final String LIST = "/list/facilities";
            public static final String GET_FACILITIES_BY_ID = "/getFacilitiesById";
            public static final String GET_FACILITIES_BY_FIELD = "/getFacilitiesByField";
            public static final String COUNT_FACILITIES = "/count/facilities";
            public static final String UPDATE_FACILITIE_STATE = "/update/facilitieState";
            public static final String COUNT_FACILITIES_BY_TIME = "/count/facilitiesByTime";
            public static final String NEARBY_FACILITY = "/nearbyFacility";

            public static final String GET_FACILITIELIST = "/list/getAppFacilities";
            public static final String GET_ALL_FACILITIELISTS_COUNT = "/list/getAllFacilitiesCount";

            public static final String UPDATE_FACILITIES_AND_RELATION = "/update/facilitieAndRelations";

            public static final String GET_ALL_FACILITIELISTS_BY_CATEGORY_AND_SERVERCODE = "/list/getAllFacilitiesByCategoryAndServerCode";

        }

        //设施服务
        public static class FacilitiesServer {
            public static final String CREATE = "/create/facilitieServers";
            public static final String DELETE = "/delete/facilitieServers";
            public static final String UPDATE = "/update/facilitieServers";
            public static final String PAGE = "/page/facilitieServers";
            public static final String LIST = "/list/facilitieServers";
            public static final String GET_FACILITIESERVERS_BY_ID = "/getFacilitieServersById";
            public static final String GET_FACILITIESERVERS_BY_FIELD = "/getFacilitieServersByField";
            public static final String LIST_FACILITIESERVERS = "/list/listFacilitieServers";
            public static final String LIST_FACILITIESERVERS_BY_TYPE = "/list/listFacilitieServersByType";
        }

        //意见反馈
        public static class FeedBack {
            public static final String CREATE = "/create/feedBacks";
            public static final String DELETE = "/delete/feedBacks";
            public static final String UPDATE = "/update/feedBacks";
            public static final String PAGE = "/page/feedBacks";
            public static final String LIST = "/list/feedBacks";
            public static final String GET_FEEDBACK_BY_ID = "/getFeedBackById";
            public static final String GET_FEEDBACKS_BY_FIELD = "/getFeedBacksByField";
            public static final String UPDATE_FEEDBACKS_BY_ID = "/updateFeedBacksById";
            public static final String EXPORT_EXCEL = "/exportExcel/feedBacks";
            public static final String GET_APP_FEEDBACK_BY_ID = "/getAppFeedBackById";
            public static final String GET_APP_FEEDBACK_BY_USER_ID_AND_READFLAG = "/getAppFeedBackByUserIdAndReadFlag";
        }

        //用户使用导航记录
        public static class FacilityUsedRecord {
            public static final String CREATE = "/create/facilityUsedRecords";
            public static final String DELETE = "/delete/facilityUsedRecords";
            public static final String UPDATE = "/update/facilityUsedRecords";
            public static final String PAGE = "/page/facilityUsedRecords";
            public static final String LIST = "/list/facilityUsedRecords";
            public static final String GET_FACILITY_USED_RECORD_BY_ID = "/getFacilityUsedRecordById";
            public static final String GET_FACILITY_USED_RECORD_BY_FIELD = "/getFacilityUsedRecordByField";
            public static final String UPDATE_FACILITY_USED_RECORD_BY_ID = "/updateFacilityUsedRecordById";
            public static final String GET_FACILITY_USED_RECORD_AND_COUNT_BY_ID = "/getFacilityUsedRecordAndCountById";
            public static final String COUNT_FACILITY_USED_RECORD_BY_USERID = "/countFacilityUsedRecordByUserId";
            public static final String GET_FACILITY_USED_RECORD_DETAIL = "/getUsedRecordDetail";

            public static final String PAGE_FACILITY_USED_RECORD_BY_USERID = "/page/facilityUsedRecordsByUserId";
        }

        //服务评价
        public static class NavigationServiceEvaluation {
            public static final String CREATE = "/create/navigationServiceEvaluation";
            public static final String DELETE = "/delete/navigationServiceEvaluation";
            public static final String UPDATE = "/update/navigationServiceEvaluation";
            public static final String PAGE = "/page/navigationServiceEvaluation";
            public static final String LIST = "/list/navigationServiceEvaluation";
            public static final String GET_NAVIGATION_SERVICE_EVALUATION_BY_ID = "/getNavigationServiceEvaluationById";
            public static final String GET_NAVIGATION_SERVICE_EVALUATION_BY_FIELD = "/getNavigationServiceEvaluationByField";
            public static final String UPDATE_NAVIGATION_SERVICE_EVALUATION_BY_ID = "/updateNavigationServiceEvaluationById";
        }

        /**
         * 城市
         */
        public static class Town {
            public static final String LIST = "/list/city";
        }

        public static class FastDFS{
            public static final String UPLOAD="/dfs/upload";
            public static final String UPLOADJSON="/dfs/jsonUpload";
            public static final String DOWN="/dfs/down";
            public static final String DELETE_BY_ID="/dfs/deleteById";
            public static final String DELETE_BY_PATH="/dfs/deleteByPath";
            public static final String GET_FILE_INFO ="/dfs/getFileInfo";
            public static final String DOWNLOAD_BY_ID="/dfs/downloadById";
            public static final String DOWNLOAD_BY_PATH="/dfs/downloadByPath";

        }

        //账号申诉
        public static class Appeal {
            public static final String CREATE = "/create/appeals";
            public static final String DELETE = "/delete/appeals";
            public static final String UPDATE = "/update/appeals";
            public static final String PAGE = "/page/appeals";
            public static final String LIST = "/list/appeals";
            public static final String GET_APPEAL_BY_ID = "/getAppealById";
            public static final String GET_APPEALS_BY_FIELD = "/getAppealsByField";
            public static final String UPDATE_APPEAL_BY_ID = "/updateAppealById";
        }
    }


}
