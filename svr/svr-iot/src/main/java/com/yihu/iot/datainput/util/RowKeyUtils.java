package com.yihu.iot.datainput.util;

import com.yihu.jw.util.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.bouncycastle.jcajce.provider.symmetric.DES;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;

public class RowKeyUtils {

    private static EncryptUtil encryptUtil = EncryptUtil.getInstance();


    public static String  makeRowKey(String accessToken,String deviceSn,String extCode,long measureTime) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(accessToken+",");
        sb.append(deviceSn+",");
        sb.append(extCode+",");
        sb.append(measureTime);
        return Base64Utils.encodeToString(sb.toString().getBytes()).toString();

    }

    /**
     * 将rowkey里的信息还原回去
     */
    public static String getMessageFromRowKey(String rowkey) throws Exception {
        return encryptUtil.decode(rowkey);
    }


    static class EncryptUtil {

        private final byte[] DESIV = new byte[] { 0x12, 0x34, 0x56, 120, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef };// 向量

        private AlgorithmParameterSpec iv = null;// 加密算法的参数接口
        private Key key = null;

        private static String charset = "utf-8";
        private static String deskey = "9ba45bfd500642328ec03ad8ef1b6e75";// 自定义密钥
        private static EncryptUtil encryptUtils = null;

        public static synchronized EncryptUtil getInstance()  {
            try {
                if(null == encryptUtils){
                    encryptUtils = new EncryptUtil(deskey,charset);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return encryptUtils;
        }

        /**
         * 初始化
         * @param deSkey    密钥
         * @throws Exception
         */
        private EncryptUtil(String deSkey, String charset) throws Exception {
            if (StringUtils.isNotBlank(charset)) {
                this.charset = charset;
            }
            DESKeySpec keySpec = new DESKeySpec(deSkey.getBytes(this.charset));// 设置密钥参数
            iv = new IvParameterSpec(DESIV);// 设置向量
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
            key = keyFactory.generateSecret(keySpec);// 得到密钥对象
        }

        /**
         * 加密
         */
        public String encode(String data) throws Exception {
            Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
            enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
            byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(pasByte);
        }

        /**
         * 解密
         */
        public String decode(String data) throws Exception {
            Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            deCipher.init(Cipher.DECRYPT_MODE, key, iv);
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
            return new String(pasByte, "UTF-8");
        }
    }

}
