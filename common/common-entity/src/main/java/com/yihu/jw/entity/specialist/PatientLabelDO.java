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

    private String saasId;
    private String labelName;//标签名称',
    private String labelCode;//标签code',
    private String labelType;//标签类型:1.装病类型；2.健康情况；3.自定义',
    private Integer sort;//排序',
    private String teamCode;//团队code',
    private String del;//1：有效；0：删除',

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "label_name")
    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Column(name = "label_code")
    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    @Column(name = "label_type")
    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "team_code")
    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    @Column(name = "del")
    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
