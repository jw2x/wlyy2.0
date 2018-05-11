package com.yihu.jw.util;/**
 * Created by nature of king on 2018/5/11.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author wangzhinan
 * @create 2018-05-11 14:02
 * @desc date  component
 **/
public  class DateUtils {
    private final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //获取当天的开始时间
      public static String getDayBegin() {
          Calendar cal = new GregorianCalendar();
          cal.set(Calendar.HOUR_OF_DAY, 0);
          cal.set(Calendar.MINUTE, 0);
          cal.set(Calendar.SECOND, 0);
          cal.set(Calendar.MILLISECOND, 0);
          return sdf.format(cal.getTime());
      }
      //获取当天的结束时间
      public static String getDayEnd() {
          Calendar cal = new GregorianCalendar();
          cal.set(Calendar.HOUR_OF_DAY, 23);
          cal.set(Calendar.MINUTE, 59);
          cal.set(Calendar.SECOND, 59);
          return sdf.format(cal.getTime());
      }
}
