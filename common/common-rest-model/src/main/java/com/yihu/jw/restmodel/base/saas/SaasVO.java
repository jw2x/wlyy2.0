package com.yihu.jw.restmodel.base.saas;// default package


import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VO - SAAS
 * Created by progr1mmer on 2018/8/14.
 */
@ApiModel(value = "SaasVO", description = "SAAS")
public class SaasVO extends UuidIdentityVOWithOperator {

	@ApiModelProperty(value = "机构编码", example = "495323X")
	private String orgCode;
	@ApiModelProperty(value = "系统名称", example = "厦门医疗服务")
	private String name;
	@ApiModelProperty(value = "状态", example = "auditPassed")
	private SaasDO.Status status;
	@ApiModelProperty(value = "备注", example = "我是备注")
	private String remark;
	@ApiModelProperty(value = "远程fastDFS文件地址", example = "group1:M00/97/E9/wKgyJltp1i-AHHf6AAAvRXBaR18423.zip")
	private String logo;
	@ApiModelProperty(value = "类型", example = "familyDoctor")
    private SaasDO.Type type;
	@ApiModelProperty(value = "管理员 - 关联user表id字段", example = "402303ee656498890165649ad2wa00sd")
	private String manager;
	@ApiModelProperty(value = "管理员邮箱", example = "admin@jkzl.com")
	private String email;
	@ApiModelProperty(value = "管理员手机号码", example = "18888888888")
	private String mobile;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public SaasDO.Status getStatus() {
        return status;
    }

    public void setStatus(SaasDO.Status status) {
        this.status = status;
    }

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public SaasDO.Type getType() {
		return type;
	}

	public void setType(SaasDO.Type type) {
		this.type = type;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}