package com.yihu.jw.healthyhouse.model.user;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户评价
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
@Entity
@Table(name = "navigation_service_evaluation")
public class NavigationServiceEvaluation extends UuidIdentityEntityWithOperator {
    //设施编码
    @Column(name = "facilitie_code", nullable = false)
    private String facilitieCode;
    //设施名称
    @Column(name = "facilitie_name")
    private String facilitieName;
    //服务编码
    @Column(name = "service_code")
    private String serviceCode;
    //服务名称
    @Column(name = "service_name")
    private String serviceName;
    //用户
    @Column(name = "user_id")
    private String userId;
    //分数:星星数量
    @Column(name = "score")
    private String score;
    //备注、意见或建议
    @Column(name = "remark")
    private String remark;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
