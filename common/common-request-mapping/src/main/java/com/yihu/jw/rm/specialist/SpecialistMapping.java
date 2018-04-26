package com.yihu.jw.rm.specialist;

/**
 * Created by Trick on 2018/4/25.
 */
public class SpecialistMapping {

    public static final String api_specialist_common = "svr-specialist";
    public static final Integer api_specialist_fail = -1;
    public static final String api_success ="succes";

    public static class specialist{

        public static final String createSpecialists = "/createSpecialists";
        public static final String createSpecialistRelation = "/createSpecialistRelation";
        public static final String createConsult = "/createConsult";
        public static final String createArticle = "/createArticle";
        public static final String findSpecialistPatientRelation = "/findSpecialistPatientRelation";
    }
}
