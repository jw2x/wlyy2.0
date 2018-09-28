package com.yihu.jw.healthyhouse.model.facility;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.*;

/**
 * 设施与服务关联关系
 * @author zdm
 * @version 1.0
 * @created 2018.09.20
 */
@Entity
@Table(name = "facility_server_relation")
public class FacilityServerRelation extends UuidIdentityEntityWithOperator {
    //设施编码
    @Column(name = "facilitie_code", nullable = false)
    private String facilitieCode;
    //设施名称
    @Column(name = "facilitie_name")
    private String facilitieName;
    //设施服务编码
    @Column(name = "service_code", nullable = false )
    private String serviceCode ;
    //设施服务名称
    @Column(name = "service_name")
    private String serviceName;

    public String getFacilitieCode() {
        return facilitieCode;
    }

    public void setFacilitieCode(String facilitieCode) {
        this.facilitieCode = facilitieCode;
    }

    public String getFacilitieName() {
        return facilitieName;
    }

    public void setFacilitieName(String facilitieName) {
        this.facilitieName = facilitieName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
