package com.yihu.jw.entity.base.servicePackage;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 服务包明细执行时间表
 * @author yeshijie on 2018/8/17.
 */
@Entity
@Table(name = "base_service_package_detail_time")
public class ServicePackageDetailTimeDO extends UuidIdentityEntity implements Serializable {

    private String saasId;
    private String detailId;//服务包明细id
    private Date excutionTime;//执行时间

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "detail_id")
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    @Column(name = "excution_time")
    public Date getExcutionTime() {
        return excutionTime;
    }

    public void setExcutionTime(Date excutionTime) {
        this.excutionTime = excutionTime;
    }
}
