package com.yihu.jw.entity.base.servicePackage;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 服务包明细表
 * @author yeshijie on 2018/8/17.
 */
@Entity
@Table(name = "base_service_package_details")
public class ServicePackageDetailsDO extends UuidIdentityEntity implements Serializable {

    private String saasId;
    private String servicePackageId;//服务包id',
    private String code;//服务项目编码',
    private String name;//服务项目名称',
    private Date createTime;//创建时间',
    private Date executionTime;//执行时间',
    private String executionType;//执行类型（1固定时间，2固定次数（不固定时间），3长期）',
    private Integer executionNum;//最少执行次数',

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "service_package_id")
    public String getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(String servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "execution_type")
    public String getExecutionType() {
        return executionType;
    }

    public void setExecutionType(String executionType) {
        this.executionType = executionType;
    }

    @Column(name = "execution_num")
    public Integer getExecutionNum() {
        return executionNum;
    }

    public void setExecutionNum(Integer executionNum) {
        this.executionNum = executionNum;
    }

    @Column(name = "execution_time")
    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }
}
