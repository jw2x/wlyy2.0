package com.yihu.jw.security.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Model 公钥
 * Created by progr1mmer on 2018/9/3.
 */
public class PublicKey implements Serializable {

    //模
    private String modulus;
    //指数
    private String exponent;

    public String getModulus() {
        return modulus;
    }

    public void setModulus(String modulus) {
        this.modulus = modulus;
    }

    public String getExponent() {
        return exponent;
    }

    public void setExponent(String exponent) {
        this.exponent = exponent;
    }
}
