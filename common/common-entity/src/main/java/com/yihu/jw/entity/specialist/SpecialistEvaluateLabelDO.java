package com.yihu.jw.entity.specialist;/**
 * Created by nature of king on 2018/8/20.
 */

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wangzhinan
 * @create 2018-08-20 10:01
 * @desc 评论标签
 **/
@Entity
@Table(name = "wlyy_specialist_evaluate_label")
public class SpecialistEvaluateLabelDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "doctor")
    private String doctor;//被评论的医生

    @Column(name = "evaluate_type")
    private Integer evaluateType;//评论种类

    @Column(name = "relation_code")
    private String relationCode;//评论类型

    @Column(name = "content")
    private String content;//标签内容

    @Column(name = "patient")
    private String patient;//患者code

    @Column(name = "flag")
    private Integer flag;//标识标签


    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "doctor")
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Column(name = "evaluate_type")
    public Integer getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(Integer evaluateType) {
        this.evaluateType = evaluateType;
    }

    @Column(name = "relation_code")
    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "patient")
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Column(name = "flag")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
