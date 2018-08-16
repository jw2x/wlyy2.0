package com.yihu.health.util.encode;


import com.yihu.health.util.operator.DateUtil;
import com.yihu.health.util.operator.StringUtil;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @created Air 2015/6/2.
 */
public class DES {

    public static String encrypt(String data, String passWord) throws Exception {
        if (StringUtil.isEmpty(data)) {
            return "";
        }

        DESKeySpec desKey = new DESKeySpec(passWord.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.encode(cipher.doFinal(data.getBytes("UTF-8")));
    }

    public static String decrypt(String data, String passWord) throws Exception {
        if (StringUtil.isEmpty(data)) {
            return "";
        }

        DESKeySpec desKey = new DESKeySpec(passWord.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.decode(data)), "UTF-8");
    }


    /**
     * 生成长度为16倍数的DES密钥，不足则末位补0
     *  格式：conten+YYYYMMDD时间+ 补位符0
     * @param content 密钥
     * @return 长度为16倍数的密钥
     */
    public static String genDesPass(String content) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DEFAULT_CHAR_DATE_YMD_FORMAT);
        StringBuffer passWordBuffer = new StringBuffer();
        if (StringUtil.isEmpty(content)) {
            return "";
        }
        passWordBuffer = passWordBuffer.append(content).append(sdf.format(new Date()));
        Integer remainder = passWordBuffer.length() % 16;
        if (remainder != 0) {
            for (int i = 0; i < 16-remainder; i++) {
                passWordBuffer.append(0);
            }
        }
        return passWordBuffer.toString();
    }
}
