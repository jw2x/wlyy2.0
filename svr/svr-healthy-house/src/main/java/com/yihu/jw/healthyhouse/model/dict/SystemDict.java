package com.yihu.jw.healthyhouse.model.dict;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import com.yihu.jw.healthyhouse.util.PinyinUtil;

import javax.persistence.*;

/**
 * 系统字典
 * @author zdm
 * @version 1.0
 * @created 2018.09.18
 */
@Entity
@Table(name = "system_dicts")
@Access(value = AccessType.PROPERTY)
public class SystemDict extends UuidIdentityEntityWithOperator {
    /** 名称*/
    private String name;
    /** 英文首字母*/
    private String phoneticCode;
    /** 编码*/
    private String code;

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name = name;
        this.phoneticCode = PinyinUtil.getPinYinHeadChar(name, true);
    }

    @Column(name = "phonetic_code", nullable = false)
    public String getPhoneticCode() {
        return phoneticCode;
    }
    public void setPhoneticCode(String phoneticCode) {
        this.phoneticCode = phoneticCode;
    }

    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
