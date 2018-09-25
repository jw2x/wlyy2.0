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
    }


}
