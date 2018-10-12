package com.yihu.jw.entity.base.saas;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zdm on 2018/10/10.
 */
@Entity
@Table(name = "base_saas_type_dict")
public class SaasTypeDictDO extends UuidIdentityEntityWithOperator{

    public enum  Status{
        //失效
        invalid,
        //生效
        effective
    }
    //Saas类型编码
    private Integer code;
    //Saas类型名称
    private String name;

    //状态（1生效中，0已失效）
    private Status status;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
