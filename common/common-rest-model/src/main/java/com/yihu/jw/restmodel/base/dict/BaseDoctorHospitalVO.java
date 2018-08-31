package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 医生职业信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseDoctorHospitalVO", description = "医生职业信息")
public class BaseDoctorHospitalVO extends IntegerIdentityVO{

    /**
	医院标识	*/
    private String hospCode;
    /**
	医院名称	*/
    private String hospName;
    /**
	医生角色标识	*/
    private String roleCode;
    /**
	医院角色名称	*/
    private String roleName;
    /**
	职称代码	*/
    private String jobTitleCode;
    /**
	职称名称	*/
    private String jobTitleName;
    /**
	作废标识，1正常，0作废	*/
    private String del;
    /**
		*/
    private Date createTime;

	@ApiModelProperty(value = "医院标识", example = "模块1")
    public String getHospCode() {
        return hospCode;
    }
    public void setHospCode(String hospCode) {
        this.hospCode = hospCode;
    }

	@ApiModelProperty(value = "医院名称", example = "模块1")
    public String getHospName() {
        return hospName;
    }
    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

	@ApiModelProperty(value = "医生角色标识", example = "模块1")
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

	@ApiModelProperty(value = "医院角色名称", example = "模块1")
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	@ApiModelProperty(value = "职称代码", example = "模块1")
    public String getJobTitleCode() {
        return jobTitleCode;
    }
    public void setJobTitleCode(String jobTitleCode) {
        this.jobTitleCode = jobTitleCode;
    }

	@ApiModelProperty(value = "职称名称", example = "模块1")
    public String getJobTitleName() {
        return jobTitleName;
    }
    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "模块1")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

	@ApiModelProperty(value = "", example = "模块1")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}