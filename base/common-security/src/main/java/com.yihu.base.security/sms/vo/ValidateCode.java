/**
 *
 */
package com.yihu.base.security.sms.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author chenweida
 */
public class ValidateCode implements Serializable {


    private String code;

    private LocalDateTime expireTime;

    public ValidateCode() {
    }

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }


    public String getExpireTimeString() {
        return expireTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public void setExpireTimeString(String strTime) {
        expireTime = LocalDateTime.parse(strTime, DateTimeFormatter.ISO_DATE_TIME);
    }
}
