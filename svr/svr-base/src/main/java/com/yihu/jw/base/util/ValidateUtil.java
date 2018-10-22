package com.yihu.jw.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yeshijie on 2018/10/18.
 */
public class ValidateUtil {
    /**
     * 验证是否为正确的邮箱号
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        // 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
        // 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
        // {1,3}表示可以出现一次或两次或者三次.
        String reg = "\\w+@(\\w+\\.){1,3}\\w+";
        Pattern pattern = Pattern.compile(reg);
        boolean flag = false;
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            flag = matcher.matches();
        }
        return flag;
    }

    /**
     * 验证是否为手机号
     *
     * @param mobileNo
     * @return
     */
    public static boolean isValidMobileNo(String mobileNo) {
        // 1、1开头 11位数字
        boolean flag = false;
        String reg = "^1\\d{10}$";
        Pattern pattern = Pattern.compile(reg);
        Matcher match = pattern.matcher(mobileNo);
        if (mobileNo != null) {
            flag = match.matches();
        }
        return flag;
    }

}
