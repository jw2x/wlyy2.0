package com.yihu.jw.restmodel.base.team;

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
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseTeamMemberVO", description = "团队成员")
public class BaseTeamMemberVO extends UuidIdentityVOWithOperator {

    /**
	 * 团队ID
	 */
	@ApiModelProperty(value = "团队ID", example = "")
    private String teamId;

    /**
	 * 机构标识
	 */
	@ApiModelProperty(value = "机构标识", example = "")
    private String orgId;

    /**
	 * 医生标识，多个医生以逗号分开
	 */
	@ApiModelProperty(value = "医生标识，多个医生以逗号分开", example = "")
    private String doctorId;

    /**
	 * 作废标识，1正常，0作废
	 */
	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "1")
    private String del;


    public String getTeamId() {
        return teamId;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }


}