package com.yihu.jw.restmodel.base.team;

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
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseTeamVO", description = "团队信息")
public class BaseTeamVO extends UuidIdentityVOWithOperator {

    /**
	 * 机构代码
	 */
	@ApiModelProperty(value = "机构代码", example = "")
    private String orgCode;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称", example = "")
    private String orgName;

    /**
	 * 团队名称
	 */
	@ApiModelProperty(value = "团队名称", example = "")
    private String name;

    /**
	 * 领导医生标识
	 */
	@ApiModelProperty(value = "领导医生标识", example = "")
    private String leaderId;

    /**
	 * 团队人数
	 */
	@ApiModelProperty(value = "团队人数", example = "自然数")
    private String teamNum;

    /**
	 * 团队二维码
	 */
	@ApiModelProperty(value = "团队二维码", example = "")
    private String qrcode;

    /**
	 * 作废标识，1正常，0作废
	 */
	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "1")
    private String del;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLeaderId() {
        return leaderId;
    }
    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getTeamNum() {
        return teamNum;
    }
    public void setTeamNum(String teamNum) {
        this.teamNum = teamNum;
    }

    public String getQrcode() {
        return qrcode;
    }
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }


}