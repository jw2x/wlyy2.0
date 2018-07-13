package com.yihu.jw.util.common;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016.08.17.
 * 身份证工具类
 */
public class IdCardUtil {
    //二级维度的性别
    public static String level_sex_1="1";
    public static String level_sex_2="2";
    public static String level_sex_3="3";
    public static String level_sex_1_name="男";
    public static String level_sex_2_name="女";
    public static String level_sex_3_name="未知";


//    /**
//     * 根据身份证的号码算出当前身份证持有者的年龄
//     *
//     * @param
//     * @throws Exception
//     */
//    public static int getAgeForIdcard(String card)
//            throws Exception {
//        int age = 0;
//        if (card.length() == 18) {
//            String year = card.substring(6).substring(0, 4);// 得到年份
//            String yue = card.substring(10).substring(0, 2);// 得到月份
//            // String day=CardCode.substring(12).substring(0,2);//得到日
//            Date date = new Date();// 得到当前的系统时间
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String fyear = format.format(date).substring(0, 4);// 当前年份
//            String fyue = format.format(date).substring(5, 7);// 月份
//            if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
//                age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
//            } else {// 当前用户还没过生
//                age = Integer.parseInt(fyear) - Integer.parseInt(year);
//            }
//        } else if (card.length() == 15) {
//            String uyear = "19" + card.substring(6, 8);// 年份
//            String uyue = card.substring(8, 10);// 月份
//            Date date = new Date();// 得到当前的系统时间
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String fyear = format.format(date).substring(0, 4);// 当前年份
//            String fyue = format.format(date).substring(5, 7);// 月份
//            // String fday=format.format(date).substring(8,10);
//            if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
//                age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
//            } else {// 当前用户还没过生
//                age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
//            }
//
//        }
//        return age;
//    }

    /**
     * 根据身份证的号码算出当前身份证持有者的年龄
     *
     * @param
     * @throws Exception
     */
    public static int getAgeForIdcard(String idcard) {
        try {
            int age = 0;

            if (StringUtils.isEmpty(idcard)) {
                return age;
            }

            String birth = "";

            if (idcard.length() == 18) {
                birth = idcard.substring(6, 14);
            } else if (idcard.length() == 15) {
                birth = "19" + idcard.substring(6, 12);
            }

            int year = Integer.valueOf(birth.substring(0, 4));
            int month = Integer.valueOf(birth.substring(4, 6));
            int day = Integer.valueOf(birth.substring(6));
            Calendar cal = Calendar.getInstance();
            age = cal.get(Calendar.YEAR) - year;
            //周岁计算
            if (cal.get(Calendar.MONTH) < (month - 1) || (cal.get(Calendar.MONTH) == (month - 1) && cal.get(Calendar.DATE) < day)) {
                age--;
            }

            return age;
        } catch (Exception e) {

        }
        return -1;
    }

    /**
     * 身份证提取出身日期
     *
     * @param card
     * @return
     * @throws Exception
     */
    public static Date getBirthdayForIdcard(String card)
            throws Exception {
        Date b = null;
        if (card.length() == 18) {
            String year = card.substring(6).substring(0, 4);// 得到年份
            String yue = card.substring(10).substring(0, 2);// 得到月份
            String ri = card.substring(12).substring(0, 2);// 得到日
            // String day=CardCode.substring(12).substring(0,2);//得到日
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            b = format.parse(year + "-" + yue + "-" + ri);
        } else if (card.length() == 15) {
            String uyear = "19" + card.substring(6, 8);// 年份
            String uyue = card.substring(8, 10);// 月份
            String uri = card.substring(10, 12);// 得到日
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            b = format.parse(uyear + "-" + uyue + "-" + uri);
        }
        return b;
    }

//    public static
//    if (cardId.length() == 15 || cardId.length() == 18) {
//        if (!this.cardCodeVerifySimple(cardId)) {
//            error.put("cardId", "15位或18位身份证号码不正确");
//        } else {
//            if (cardId.length() == 18 && !this.cardCodeVerify(cardId)) {
//                error.put("cardId", "18位身份证号码不符合国家规范");
//            }
//        }
//    } else {
//        error.put("cardId", "身份证号码长度必须等于15或18位");
//    }

    private boolean cardCodeVerifySimple(String cardcode) {
        //第一代身份证正则表达式(15位)
        String isIDCard1 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        //第二代身份证正则表达式(18位)
        String isIDCard2 ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$";

        //验证身份证
        if (cardcode.matches(isIDCard1) || cardcode.matches(isIDCard2)) {
            return true;
        }
        return false;
    }

    /**
     * 根据身份证的号码算出当前身份证持有者的性别
     * 1 男 2 女 3未知
     *
     * @return
     * @throws Exception
     */
    public static String getSexForIdcard_new(String CardCode)
            throws Exception {
        String sex = level_sex_3;
        try {
            if (CardCode.length() == 18) {
                if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
                    // modifid by lyr 2016-09-29
                    sex = level_sex_2;
                    // modifid by lyr 2016-09-29
                } else {
                    // modifid by lyr 2016-09-29
                    sex = level_sex_1;
                    // modifid by lyr 2016-09-29
                }
            } else if (CardCode.length() == 15) {
                String usex = CardCode.substring(14, 15);// 用户的性别
                if (Integer.parseInt(usex) % 2 == 0) {
                    sex = level_sex_2;
                } else {
                    sex = level_sex_1;
                }
            }
            return sex;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sex;
    }
}
