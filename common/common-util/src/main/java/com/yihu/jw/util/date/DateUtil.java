package com.yihu.jw.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenweida on 2017/5/19.
 */
public class DateUtil {
    public static final String yyyy_MM_dd_HH_mm_ss="yyyy-MM-dd HH:mm:ss";

    public static Date dateTimeParse(String date) throws ParseException {
        return new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss).parse(date);
    }
}
