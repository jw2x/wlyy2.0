package com.yihu.jw.restmodel.base.saas;// default package


import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import com.yihu.jw.restmodel.base.module.SaasModuleVO;
import com.yihu.jw.restmodel.base.org.BaseOrgVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * VO - SAAS
 * Created by progr1mmer on 2018/8/14.
 */
@ApiModel(value = "SaasVO", description = "租户")
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
	@ApiModelProperty(value = "类型（租户类型编码，从1开始自增）", example = "1")
    private Integer type;
	@ApiModelProperty(value = "管理员 - 关联user表id字段", example = "402303ee656498890165649ad2wa00sd")
	private String manager;
	@ApiModelProperty(value = "管理员邮箱", example = "admin@jkzl.com")
	private String email;
	@ApiModelProperty(value = "管理员手机号码", example = "18888888888")
	private String mobile;
	@ApiModelProperty(value = "审核不通过的原因", example = "信息不准确")
	private String auditFailedReason;
	@ApiModelProperty(value = "租户类型名称", example = "家医型")
	private String typeName;
	@ApiModelProperty(value = "管理员姓名", example = "张三")
	private String managerName;
	@ApiModelProperty(value = "组织机构代码", example = "46542")
	private String organizationCode;
	@ApiModelProperty(value = "营业执照url", example = "../img/")
	private String businessLicense;
	@ApiModelProperty(value = "系统名称", example = "i健康")
	private String systemName;
	@ApiModelProperty(value = "行政区划代码", example = "361000")
	private String areaNumber;
	@ApiModelProperty(value = "主题色", example = "主题色")
	private String themeColor;

	@ApiModelProperty(value = "机构", example = "机构")
	List<BaseOrgVO> orgList;
	@ApiModelProperty(value = "业务模块", example = "业务模块")
	List<SaasModuleVO> saasModuleList;
	@ApiModelProperty(value = "主题风格", example = "主题风格")
	List<SaasThemeVO> saasThemeList;


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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
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

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getAreaNumber() {
		return areaNumber;
	}

	public void setAreaNumber(String areaNumber) {
		this.areaNumber = areaNumber;
	}

	public List<BaseOrgVO> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<BaseOrgVO> orgList) {
		this.orgList = orgList;
	}

	public List<SaasModuleVO> getSaasModuleList() {
		return saasModuleList;
	}

	public void setSaasModuleList(List<SaasModuleVO> saasModuleVOList) {
		this.saasModuleList = saasModuleList;
	}

    public String getAuditFailedReason() {
        return auditFailedReason;
    }

    public void setAuditFailedReason(String auditFailedReason) {
        this.auditFailedReason = auditFailedReason;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

	public String getThemeColor() {
		return themeColor;
	}

	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}

	public List<SaasThemeVO> getSaasThemeList() {
		return saasThemeList;
	}

	public void setSaasThemeList(List<SaasThemeVO> saasThemeList) {
		this.saasThemeList = saasThemeList;
	}
}