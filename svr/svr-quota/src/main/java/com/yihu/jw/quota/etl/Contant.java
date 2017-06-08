package com.yihu.jw.quota.etl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
public class Contant {
    //抽取数据的开始时间
    public static String startTime = "${start_time}";
    //抽取数据的结束时间
    public static String endTime = "${end_time}";


    //数据库类型
    public static class db_type {
        public static String oracle = "oracle";
        public static String mysql = "mysql";
    }

    /**
     * DateModel的map的key常量
     */
    public static class extract {
        public static final String computeKey1 = "oneKey";
        public static final String computeKey2 = "senondKey";
    }

    /**
     * 运算常量
     */
    public static class compute {
        public static final String add = "1"; //累加
        public static final String division = "2"; //相除
    }

    /**
     * 主维度常量
     */
    public static class main_dimension {
        public static final String time_day = "1";//时间维度 日
        public static final String time_month = "2";//时间维度 周
        public static final String time_week = "3";//时间维度  月
        public static final String time_year = "4";//时间维度 年
        public static final String area_province = "5";//行政区划 省
        public static final String area_city = "6";//行政区划 市
        public static final String area_town = "7";//行政区划 区县
        public static final String area_org = "8";//行政区划 机构
        public static final String area_team = "9";//行政区划  团队
    }

    public static class quota{
        public static final String dataLeval_all="1";
        public static final String dataLeval_oneDay="2";

    }

    /**
     * areaLevel 具体的值
     */
    public static class main_dimension_areaLevel {
        public static final String area_province = "5";//行政区划 省
        public static final String area_city = "6";//行政区划 市
        public static final String area_town = "7";//行政区划 区县
        public static final String area_org = "8";//行政区划 机构
        public static final String area_team = "9";//行政区划  团队

        public static String getAreaLevelByMainDimension(String key) {
            switch (key) {
                case main_dimension.area_province: {
                    return area_province;
                }
                case main_dimension.area_city: {
                    return area_city;
                }
                case main_dimension.area_town: {
                    return area_town;
                }
                case main_dimension.area_org: {
                    return area_org;
                }
                case main_dimension.area_team: {
                    return area_team;
                }
            }
            return "";
        }
    }

    /**
     * 主维度 时间维度
     */
    public static class main_dimension_timeLevel {
        public static final String year = "1";
        public static final String month = "2";
        public static final String week = "3";
        public static final String day = "4";
    }

    /**
     * 从维度常量
     */
    public static class slave_dimension {
        public static final String sex = "1";//性别
        public static final String age = "2";//年龄段
    }

    public static class slave_dimension_key {
        public static final String one = "one";
        public static final String two = "two";
    }

    public static class save_status {
        public static final String success = "1";
        public static final String fail = "0";
    }

    /**
     * 数据过滤用到的参量
     */

    public static class role {
        public static final String not_null = "1";//非空
    }

    public static class save {
        public static final String es = "1";
        public static final String mysql = "2";
    }

    public static class convert {
        public static String level_age_1 = "1";
        public static String level_age_2 = "2";
        public static String level_age_3 = "3";
        public static String level_age_4 = "4";
        public static String level_age_5 = "5";
        public static String level_age_6 = "6";
        public static String level_age_1_name = "0~6";
        public static String level_age_2_name = "7~18";
        public static String level_age_3_name = "19~30";
        public static String level_age_4_name = "31~50";
        public static String level_age_5_name = "51~65";
        public static String level_age_6_name = ">65";
    }

}
