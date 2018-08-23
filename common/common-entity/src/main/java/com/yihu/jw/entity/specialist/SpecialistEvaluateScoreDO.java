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
 * @create 2018-08-20 10:28
 * @desc 被评论人积分
 **/
@Entity
@Table(name = "wlyy_specialist_evaluate_score")
public class SpecialistEvaluateScoreDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "doctor")
    private String doctor;//医生

    @Column(name = "evaluate_type")
    private String evaluateType;//评论种类

    @Column(name = "relation_code")
    private String relationCode;//关联code

    @Column(name = "score")
    private String score;//评论得分


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
    public String getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(String evaluateType) {
        this.evaluateType = evaluateType;
    }

    @Column(name = "relation_code")
    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    @Column(name = "score")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
