package com.yihu.jw.entity.iot.company;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 企业类型表
 * @author yeshijie on 2018/1/16.
 */
@Entity
@Table(name = "iot_company_type")
public class IotCompanyTypeDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "company_id")
    private String companyId;//企业id
    @Column(name = "type")
    private String type;//企业类型code
    @Column(name = "type_name")
    private String typeName;//企业类型名称

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
