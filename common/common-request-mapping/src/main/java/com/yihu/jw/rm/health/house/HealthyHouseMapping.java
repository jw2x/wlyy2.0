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
    }


}
