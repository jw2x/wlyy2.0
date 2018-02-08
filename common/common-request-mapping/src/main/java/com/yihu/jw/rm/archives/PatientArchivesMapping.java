package com.yihu.jw.rm.archives;

/**
 * Created by Trick on 2018/2/7.
 */
public class PatientArchivesMapping {

    public static final String api_archives_common = "svr-archives";
    public static final Integer api_archives_fail = -1;
    public static final String api_success ="succes";

    public static class Archives{
        public static final String findPatientArchives = "/findPatientArchives";
        public static final String findPatientArchivesInfos = "/findPatientArchivesInfos";
        public static final String createPatientArchives = "/createPatientArchives";
        public static final String updatePatientArchives = "/updatePatientArchives";
    }
}
