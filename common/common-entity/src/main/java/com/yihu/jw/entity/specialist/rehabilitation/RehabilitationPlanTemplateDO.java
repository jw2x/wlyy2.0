package com.yihu.jw.entity.specialist.rehabilitation;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * Created by humingfen on 2018/8/15.
 */
@Entity
@Table(name = "wlyy_rehabilitation_plan_template")
public class RehabilitationPlanTemplateDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "title")
    private String title;//模板名称
    @Column(name = "hospital")
    private String hospital;//医院标识
    @Column(name = "hospital_name")
    private String hospitalName;//医院名称
    @Column(name = "admin_team_code")
    private Integer adminTeamCode;//行政团队id

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getAdminTeamCode() {
        return adminTeamCode;
    }

    public void setAdminTeamCode(Integer adminTeamCode) {
        this.adminTeamCode = adminTeamCode;
    }
}
