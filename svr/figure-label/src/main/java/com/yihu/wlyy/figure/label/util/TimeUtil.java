package com.yihu.wlyy.figure.label.util;

import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author litaohong on 2018/5/3
 * @project patient-co-management
 * 计时器工具类
 */
public class TimeUtil{

    public static void start(Logger logger,String title,long start){
        StringBuilder sb = new StringBuilder();
        sb.append(title).append(" start at ")
                .append(DateFormatUtils.format(start, ConstantUtil.date_format));
        logger.info(sb.toString());
    }

    public static void finish(Logger logger,String title,long start,long finishTime){
        StringBuilder sb = new StringBuilder();
        long delta = finishTime - start;
        sb.append(title).append(" finish at ")
                .append(DateFormatUtils.format(finishTime, ConstantUtil.date_format)).append(",coast: ");
        long day = 1000 * 60 * 60 * 24;
        long hour = 1000 * 60 * 60;
        long min = 1000 * 60;
        if(delta > day){
            sb.append(TimeUnit.MILLISECONDS.toSeconds(delta) % 60).append("天");
        }
        if(delta > hour){
            sb.append(TimeUnit.MILLISECONDS.toHours(delta) % 24).append("时");
        }
        if(delta > min){
            sb.append(TimeUnit.MILLISECONDS.toMinutes(delta) % 60).append("分");
        }
        sb.append(TimeUnit.MILLISECONDS.toSeconds(delta) % 60).append("秒");
        logger.info(sb.toString());
    }


    /**
     * 转换为Solr时间格式
     * @param datetime
     * @return
     */
    public static String toSolrTime(String datetime){
        if(StringUtils.isEmpty(datetime)){
            return "";
        }
        String solrTime = "";
        String[] timeArr = datetime.split(" ");
        solrTime = timeArr[0] + "T" + timeArr[1]+"Z";
        return solrTime;
    }


    /**
     * 转换为elasticsearch时间格式
     * @param datetime
     * @return
     */
    public static String toEsTime(String datetime){
        if(StringUtils.isEmpty(datetime)){
            return "";
        }
        String EsTime = "";
        String[] timeArr = datetime.split(" ");
        EsTime = timeArr[0] + "T" + timeArr[1]+"+0800";
        return EsTime;
    }
    /**
     * 将Solr时间格式转为java时间格式
     * @param solrTime 2017-12-18T08:53:36Z
     * @return
     */
    public static String toJavaTime(String solrTime){
        if(StringUtils.isEmpty(solrTime)){
            return "";
        }
        String date = solrTime.substring(0,10);
        String time = solrTime.substring(11,solrTime.length()-1);
        return date+" "+time;
    }

}
