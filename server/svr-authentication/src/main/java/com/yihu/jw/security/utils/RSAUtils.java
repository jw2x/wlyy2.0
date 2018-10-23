package com.yihu.jw.security.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


public class RSAUtils {

//    private static final KeyPair keyPair = initKey();

    public static KeyPair getKey(){
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            SecureRandom random = new SecureRandom();
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
            generator.initialize(1024, random);
            return generator.generateKeyPair();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成public key
     * @return
     */
    public static String generateBase64PublicKey(KeyPair keyPair){
        RSAPublicKey key = (RSAPublicKey)keyPair.getPublic();
        return new String(Base64.encodeBase64(key.getEncoded()));
    }

    /**
     * 解密
     * @param string
     * @return
     */
    public static String decryptBase64(String string,KeyPair keyPair) {
        return new String(decrypt(Base64.decodeBase64(string),keyPair));
    }

    private static byte[] decrypt(byte[] string,KeyPair keyPair) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
            RSAPrivateKey pbk = (RSAPrivateKey)keyPair.getPrivate();
            cipher.init(Cipher.DECRYPT_MODE, pbk);
            byte[] plainText = cipher.doFinal(string);
            return plainText;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//
//        KeyPair keyPair =  initKey();
//        // 生成public key
//        System.out.println(generateBase64PublicKey(keyPair));
//
//        // 解密
//        System.out.println(decryptBase64("wAfY9JkoKay9SxcPIs1FcG+t6sR+wYwAs/mh9DpfcBraxzqoZdb9LyaAigzFQ0EKck9OyHL0dhv+Uxuw5hHw6CPT0B2Z0i1gwrjDUNaL1gWvqt1pDJVGrIYPLJSjs9xktFhY1jbxQgXGjyCt06Rwid5sJknw90AUO0CyQulfipg=",keyPair));
//    }

}
