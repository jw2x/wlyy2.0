package com.yihu.jw.entity.base.score;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Trick on 2018/8/31.
 */
@Entity
@Table(name = "base_evaluate")
public class BaseEvaluateDO extends UuidIdentityEntityWithOperator {

    private String relationCode;//关联业务CODE（关联主表evlute_score）',
    private String scoreType;//评价类型：1、专业能力，2、服务态度，3、回复速度 (可拓展字段)',
    private String score;//单项得分',

    @Column(name = "relation_code")
    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    @Column(name = "score_type")
    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    @Column(name = "score")
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
