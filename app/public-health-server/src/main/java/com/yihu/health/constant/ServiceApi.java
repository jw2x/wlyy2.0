package com.yihu.health.constant;

/**
 * @author yeshijie on 2018/1/22.
 */
public class ServiceApi {
    public ServiceApi() {
    }


    public static class SystemDict{
        public static final String dictionariesWithEntry = "/dictionariesWithEntry";
        public SystemDict() {
        }
    }
    public static class Archives{
        public static final String basePath = "/wg/archives";
        public static final String findPatientArchives =basePath+ "/findPatientArchives";
        public static final String findPatientArchivesInfos =basePath+ "/findPatientArchivesInfos";
        public static final String createPatientArchives = basePath+ "/createPatientArchives";
        public static final String updatePatientArchives = basePath+ "/updatePatientArchives";
        public Archives(){}
    }
}
