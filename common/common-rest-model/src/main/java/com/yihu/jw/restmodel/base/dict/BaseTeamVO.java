package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 团队信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseTeamVO", description = "团队信息")
public class BaseTeamVO extends UuidIdentityVOWithOperator {

    /**
	机构id	*/
    private String orgId;
    /**
	团队名称	*/
    private String name;
    /**
	领导医生标识	*/
    private String leaderId;
    /**
	团队人数	*/
    private String teamNum;
    /**
	团队二维码	*/
    private String qrcode;
    /**
	作废标识，1正常，0作废	*/
    private String del;

	@ApiModelProperty(value = "机构id", example = "模块1")
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

	@ApiModelProperty(value = "团队名称", example = "模块1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@ApiModelProperty(value = "领导医生标识", example = "模块1")
    public String getLeaderId() {
        return leaderId;
    }
    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

	@ApiModelProperty(value = "团队人数", example = "模块1")
    public String getTeamNum() {
        return teamNum;
    }
    public void setTeamNum(String teamNum) {
        this.teamNum = teamNum;
    }

	@ApiModelProperty(value = "团队二维码", example = "模块1")
    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "模块1")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}