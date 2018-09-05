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
    private Long adminTeamCode;//行政团队id
    @Column(name = "del")
    private Integer del;//是否删除（0是，1否）

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "hospital")
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Column(name = "hospital_name")
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Column(name = "admin_team_code")
    public Long getAdminTeamCode() {
        return adminTeamCode;
    }

    public void setAdminTeamCode(Long adminTeamCode) {
        this.adminTeamCode = adminTeamCode;
    }

    @Column(name = "del")
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
