package com.yihu.jw.restmodel.base.patient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Trick on 2018/8/31.
 */
@ApiModel(value = "PatientFamilyMemberVO", description = "家庭成员")
public class PatientFamilyMemberVO {
    @ApiModelProperty(value = "id")
    private String id;//主键id
    @ApiModelProperty(value = "居民code")
    private String patient;//居民code',
    @ApiModelProperty(value = "家庭成员code")
    private String familyMember;//家庭成员code',
    @ApiModelProperty(value = "家庭关系 1父亲 2母亲 3老公 4老婆 5儿子 6女儿 7其他")
    private String familyRelation;//家庭关系',
    @ApiModelProperty(value = "是否授权0")
    private Integer isAuthorize;//是否授权0:未授权,1:已授权，默认为1',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    public Integer getIsAuthorize() {
        return isAuthorize;
    }

    public void setIsAuthorize(Integer isAuthorize) {
        this.isAuthorize = isAuthorize;
    }
}
