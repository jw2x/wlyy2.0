package com.yihu.jw.entity.base.patient;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Trick on 2018/8/31.
 */
@Entity
@Table(name = "base_patient_family_member")
public class BasePatientFamilyMemberDO extends UuidIdentityEntityWithOperator {

    private String patient;//居民code',
    private String familyMember;//家庭成员code',
    private String familyRelation;//家庭关系',
    private Integer isAuthorize;//是否授权0:未授权,1:已授权，默认为1',

    @Column(name = "patient")
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Column(name = "family_member")
    public String getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    @Column(name = "family_relation")
    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    @Column(name = "is_authorize")
    public Integer getIsAuthorize() {
        return isAuthorize;
    }

    public void setIsAuthorize(Integer isAuthorize) {
        this.isAuthorize = isAuthorize;
    }
}
