package com.yihu.jw.entity.specialist;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Trick on 2018/5/29.
 */
@Entity
@Table(name = "wlyy_patient_label")
public class PatientLabelDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "label_name")
    private String labelName;//标签名称',
    @Column(name = "label_code")
    private String labelCode;//标签code',
    @Column(name = "label_type")
    private String labelType;//标签类型:1.装病类型；2.健康情况；3.自定义',
    @Column(name = "sort")
    private Integer sort;//排序',
    @Column(name = "team_code")
    private String teamCode;//团队code',
    @Column(name = "del")
    private String del;//1：有效；0：删除',


    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }


    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }


    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }


    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
