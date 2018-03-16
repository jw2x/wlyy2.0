package com.yihu.iot.datainput.util;

public class ConstantUtils {

    //体征数据es索引
    public static String esIndex = "body_health_data";
    //体征数据es类型
    public static String esType = "signs_data";
    //体征数据hbase表名
    public static String tableName = "body_health_data";
    public static String familyA = "column_signs_header";
    public static String familyB = "column_signs_data";

    //微信运动数据es索引
    public static String weRunDataIndex = "wechat_run_data";
    //微信运动数据es类型
    public static String weRunDataType = "step_data";

    //设备坐标es索引
    public static String deviceLocationIndex = "device_location_index";
    //设备坐标es类型
    public static String deviceLocationType = "device_location_type";

    //居民标签es索引
    public static String figureLabelIndex = "figure_label_index";
    //居民标签es类型
    public static String figureLabelType = "figure_label_type";
}
