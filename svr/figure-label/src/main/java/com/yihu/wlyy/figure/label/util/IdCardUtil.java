package com.yihu.wlyy.figure.label.util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/***
 * 根据居民身份证提取相对应的信息
 */
@Component
public class IdCardUtil {

    /**
     * 根据身份证的号码算出当前身份证持有者的性别
     * 1 女 2 男 3未知
     *
     * @return
     * @throws Exception
     */
    public static String getSexForIdcard(String idCard){
        if(StringUtils.isEmpty(idCard)){
            return "未知";
        }
        try {
            String sex = "2";
            if (idCard.length() == 18) {
                if (Integer.parseInt(idCard.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
                    sex = "1";
                } else {
                    sex = "2";
                }
            } else if (idCard.length() == 15) {
                String usex = idCard.substring(14, 15);// 用户的性别
                if (Integer.parseInt(usex) % 2 == 0) {
                    sex = "1";
                } else {
                    sex = "2";
                }
            }
            return sex;
        } catch (Exception e) {
            return "1";
        }
    }


    /**
     * 根据身份证的号码算出当前身份证持有者的年龄
     *
     * @param
     * @throws Exception
     */
    public static int getAgeForIdcard(String idCard) {
        int age = 0;
        if(StringUtils.isEmpty(idCard)){
            return age;
        }
        try {

            if (org.springframework.util.StringUtils.isEmpty(idCard)) {
                return age;
            }

            String birth = "";

            if (idCard.length() == 18) {
                birth = idCard.substring(6, 14);
            } else if (idCard.length() == 15) {
                birth = "19" + idCard.substring(6, 12);
            } else {
                return age;
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
            return 0;
        }
    }

    /**
     * 身份证提取出身日期
     * @param idCard
     * @return
     * @throws Exception
     */
    public static String getBirthdayForIdcard(String idCard) throws Exception {
        if(StringUtils.isEmpty(idCard)){
            return "";
        }
        String year = "";
        String month = "";
        String day = "";
        if (idCard.length() == 18) {
            year = idCard.substring(6,10);
            month = idCard.substring(10,12);
            day = idCard.substring(12,14);
        } else if (idCard.length() == 15) {
            year = "19" + idCard.substring(6,8);
            month = idCard.substring(8,10);
            day = idCard.substring(10,12);
        }
        StringBuilder birthday = new StringBuilder();
        birthday.append(year).append("-");
        birthday.append(month).append("-");
        birthday.append(day);
        return birthday.toString();
    }

    public static void main(String args[]) throws Exception {
        System.out.println(getBirthdayForIdcard("411321198004273615"));
        System.out.println(getBirthdayForIdcard("440253850213582"));
//        System.out.println(getSexForIdcard("411321198004273615"));
//        System.out.println(getAgeForIdcard("411321198004273615"));
    }
}
