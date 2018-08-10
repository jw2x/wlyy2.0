package com.yihu.jw.util.common;

import com.yihu.jw.vo.ValidateCode;

import java.util.Random;

/**
 * Created by 刘文彬 on 2018/4/23.
 */
public class SmsValidateCodeUtils {

    /**
     *
     * @param length 短信验证码长度
     * @param expireIn 短信验证码过期时间
     * @return
     */
    public static ValidateCode generate(int length,int expireIn) {
        String code = getFixLenthString(length);
        ValidateCode validateCode = new ValidateCode(code, expireIn);
        return validateCode;
    }


    /*
 * 返回长度为【strLength】的随机数，在前面补0
 */
    private static String getFixLenthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }
}
