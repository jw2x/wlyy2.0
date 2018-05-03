package com.yihu.jw.util.common;


import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class LatitudeUtils {


    /**
     * 没有ak有的服务请求获取不到地址
     */
    public static final String ak = "wm9Cih17l010vL91nOfTbu8M";




    /**
     * 返回输入地址的经纬度坐标
     * key lng(经度),lat(纬度)
     */
    public static Map<String,String> getGeocoderLatitude(String address){
        BufferedReader in = null;
        try {
            //将地址转换成utf-8的16进制
            address = URLEncoder.encode(address, "UTF-8");
            URL tirc = new URL("http://api.map.baidu.com/geocoder/v2/?address="+ address +"&output=json&key="+ UUID.randomUUID().toString().replace("-","")+"&ak="+ak);


            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while((res = in.readLine())!=null){
                sb.append(res.trim());
            }
            String str = sb.toString();
            Map<String,String> map = null;
            if(!StringUtils.isEmpty(str)){
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if(lngStart > 0 && lngEnd > 0 && latEnd > 0){
                    String lng = str.substring(lngStart+5, lngEnd);
                    String lat = str.substring(lngEnd+7, latEnd);
                    map = new HashMap<String,String>();
                    map.put("lng", lng);
                    map.put("lat", lat);
                    return map;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(in!=null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

//    public static void main(String args[]){
//        try {
//            Map<String, String> json = LatitudeUtils.getGeocoderLatitude("浦东区张杨路1725号");
//            System.out.println("经度 : " + json.get("lng"));
//            System.out.println("纬度 : " + json.get("lat"));
//        }catch (Exception e ){
//            e.printStackTrace();
//        }
//    }


}
