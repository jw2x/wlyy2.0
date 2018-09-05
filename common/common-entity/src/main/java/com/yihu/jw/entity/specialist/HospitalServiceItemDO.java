package com.yihu.jw.entity.specialist;/**
 * Created by nature of king on 2018/8/16.
 */

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author wangzhinan
 * @create 2018-08-16 14:49
 * @desc 机构服务
 **/
@Entity
@Table(name = "wlyy_hospital_service_item")
public class HospitalServiceItemDO extends UuidIdentityEntityWithOperator implements Serializable {


    @Column(name = "saas_id")
    private String saasId;//saasId

    @Column(name = "hospital")
    private String hospital; //医院code

    @Column(name = "hospital_name")
    private String hospitalName;//医院名称

    @Column(name = "service_item_id")
    private String serviceItemId;//服务项目id

    @Column(name = "service_item_name")
    private String serviceItemName;//服务项目名称

    @Column(name = "status")
    private Integer status;//状态

    @Column(name = "expense")
    private Integer expense;//价格

    @Transient
    private Integer flag;//标识（1：社区，2、医院，3、社区、医院）

    @Transient
    private SpecialistServiceItemDO specialistServiceItemDO;

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "hospital")
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Column(name = "hospital_name")
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Column(name = "service_item_id")
    public String getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(String serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    public SpecialistServiceItemDO getSpecialistServiceItemDO() {
        return specialistServiceItemDO;
    }

    public void setSpecialistServiceItemDO(SpecialistServiceItemDO specialistServiceItemDO) {
        this.specialistServiceItemDO = specialistServiceItemDO;
    }

    @Column(name = "expense")
    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    @Column(name = "service_item_name")
    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName;
    }

    @Transient
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
