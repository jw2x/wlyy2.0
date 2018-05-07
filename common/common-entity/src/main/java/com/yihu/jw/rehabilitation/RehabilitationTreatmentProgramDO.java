package com.yihu.jw.rehabilitation;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 治疗方案表
 * @author humingfen on 2018/4/25.
 */
@Entity
@Table(name = "rehabilitation_treatment_program")
public class RehabilitationTreatmentProgramDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "name")
    private String name;
    @Column(name =  "frequency")
    private String frequency;
    @Column(name = "times_daily")
    private Integer timesDaily;
    @Column(name = "description")
    private String description;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getTimesDaily() {
        return timesDaily;
    }

    public void setTimesDaily(Integer timesDaily) {
        this.timesDaily = timesDaily;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
