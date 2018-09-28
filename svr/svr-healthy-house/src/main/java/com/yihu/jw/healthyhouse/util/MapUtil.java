package com.yihu.jw.healthyhouse.util;

/**
 *
 *  经纬度距离计算 - 摘抄网络
 * @author HZY
 * @created 2018/9/26 21:02
 */
public class MapUtil {

    private static double EARTH_RADIUS = 6371.393;

    public static double getDistance(double lng1,double lat1,double lng2,double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.abs(Math.pow(Math.sin(a/2),2) + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }

    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    public static void main(String[] args) {
        double distance = getDistance(118.191839, 24.49555, 118.15639977090478000000, 24.48613312327105300000);
        System.out.println(distance);
    }

}
