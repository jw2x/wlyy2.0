package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 团队成员vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseTeamMemberVO", description = "团队成员")
public class BaseTeamMemberVO extends UuidIdentityVOWithOperator {

    /**
	团队ID	*/
    private String teamId;
    /**
	机构标识	*/
    private String orgId;
    /**
	医生标识，多个医生以逗号分开	*/
    private String doctorId;
    /**
	作废标识，1正常，0作废	*/
    private String del;

	@ApiModelProperty(value = "团队ID", example = "模块1")
    public String getTeamId() {
        return teamId;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

	@ApiModelProperty(value = "机构标识", example = "模块1")
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

	@ApiModelProperty(value = "医生标识，多个医生以逗号分开", example = "模块1")
    public String getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "模块1")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}