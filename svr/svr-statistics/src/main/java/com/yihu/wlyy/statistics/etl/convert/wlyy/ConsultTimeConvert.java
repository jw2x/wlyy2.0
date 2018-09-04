package com.yihu.wlyy.statistics.etl.convert.wlyy;


import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.util.DateUtil;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by chenweida on 2017/6/5.
 */

public class ConsultTimeConvert implements Convert {
    /**
     * @param oneList    数据
     * @param slaveLevel 从1开始
     * @return
     */
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        oneList.stream().forEach(one -> {
            try {

                String value =one.getSlaveKey1();
                String valueStr = "1";
                if (!StringUtils.isEmpty(value)) {
                    try {
                        Date time= DateUtil.strToDate(one.getSlaveKey1(),"yyyy-MM-dd HH:mm:ss");
                        valueStr= paserReplyTime(time);
                    } catch (Exception e) {

                    }
                }
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, valueStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return oneList;
    }
    public String paserReplyTime(Date date){
        if(date!=null){
            Calendar calendar  =  new GregorianCalendar();
            calendar.setTime(date);
            //获取小时
            int hour = calendar.get(calendar.HOUR_OF_DAY);
            if(hour>=0&&hour<8){
                return "1";
            }else if(hour>=8&&hour<12){
                return "2";
            }else if(hour>=12&&hour<13){
                return "3";
            }else if(hour>=13&&hour<18){
                return "4";
            }else if(hour>=18&&hour<24){
                return"5";
            }else{
                return "";
            }
        }
        return "";
    }
}
