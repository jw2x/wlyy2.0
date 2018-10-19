package com.yihu.jw.entity.base.team;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
* 团队成员实体
*
* @author litaohong on  2018年08月31日
*
*/
@Entity
@Table(name = "base_team_member")
public class BaseTeamMemberDO extends UuidIdentityEntityWithOperator {

    /**
	* 团队ID
	*/
    private String teamCode;

    /**
	* 机构标识
	*/
    private String orgCode;

    /**
	* 医生标识，多个医生以逗号分开
	*/
    private String doctorCode;

    /**
	* 作废标识，1正常，0作废
	*/
    private String del;


	@Column(name = "team_id")
    public String getTeamCode() {
        return teamCode;
    }
    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

	@Column(name = "org_code")
    public String getOrgCode() {
        return orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

	@Column(name = "doctor_id")
    public String getDoctorCode() {
        return doctorCode;
    }
    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

	@Column(name = "del")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}