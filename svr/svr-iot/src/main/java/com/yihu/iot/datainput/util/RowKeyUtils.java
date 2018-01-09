package com.yihu.iot.datainput.util;

import org.springframework.util.DigestUtils;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class RowKeyUtils {

    public static String makeRowKey(String accessToken,String deviceSn,String extCode,long measureTime) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append(accessToken+"_");
        sb.append(deviceSn+"_");
        sb.append(extCode+"_");
        sb.append(measureTime);
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes("utf-8"));
    }
}
