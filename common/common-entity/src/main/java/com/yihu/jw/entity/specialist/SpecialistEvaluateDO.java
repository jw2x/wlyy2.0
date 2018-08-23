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
 * @create 2018-08-20 9:47
 * @desc 评论表
 **/
@Entity
@Table(name = "wlyy_specialist_evaluate")
public class SpecialistEvaluateDO extends UuidIdentityEntityWithOperator implements Serializable {
    @Column(name = "saas_id")
    private String saasId; //saasId

    @Column(name = "doctor")
    private String doctor;//被评论的医生

    @Column(name = "patient")
    private String patient;//评论人

    @Column(name = "evaluate_type")
    private Integer evaluateType;//评论种类

    @Column(name = "relation_type")
    private Integer relationType;//评价类型

    @Column(name = "relation_code")
    private Integer relationCode;//所属服务code

    @Column(name = "score")
    private Integer score;//得分

    @Column(name = "type")
    private Integer type;//评论方式

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

    @Column(name = "patient")
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Column(name = "evaluate_type")
    public Integer getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(Integer evaluateType) {
        this.evaluateType = evaluateType;
    }

    @Column(name = "relation_type")
    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    @Column(name = "relation_code")
    public Integer getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(Integer relationCode) {
        this.relationCode = relationCode;
    }

    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
