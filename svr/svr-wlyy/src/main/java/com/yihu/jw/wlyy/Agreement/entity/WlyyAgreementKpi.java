package com.yihu.jw.wlyy.agreement.entity;



import com.yihu.jw.wlyy.common.entity.base.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Entity
@Table(name = "wlyy_agreement_kpi")
public class WlyyAgreementKpi extends IdEntity {
    private String code;//业务code
    private String agreementCode;//套餐代码
    private String kpiName;//服务项名称
    private String type;//服务项类型
    private String kpiTimes;//服务次数
    private Integer status;//状态  -1删除 0 冻结 1可用
    private String kpiContent;//服务内容描述
    private String keyword;//关键字
    private Date createTime;
    private String createUser;
    private Date updaateTime;

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "kpi_name")
    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "kpi_times")
    public String getKpiTimes() {
        return kpiTimes;
    }

    public void setKpiTimes(String kpiTimes) {
        this.kpiTimes = kpiTimes;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "kpi_content")
    public String getKpiContent() {
        return kpiContent;
    }

    public void setKpiContent(String kpiContent) {
        this.kpiContent = kpiContent;
    }

    @Column(name = "keyword")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "create_user")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(name = "updaate_time")
    public Date getUpdaateTime() {
        return updaateTime;
    }

    public void setUpdaateTime(Date updaateTime) {
        this.updaateTime = updaateTime;
    }
}
