package com.yihu.jw.restmodel.base.doctor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 医生执业信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseDoctorHospitalVO", description = "医生执业信息")
public class BaseDoctorHospitalVO extends IntegerIdentityVO{

    /**
	 * 医院标识
	 */
	@ApiModelProperty(value = "医院标识", example = "")
    private String hospCode;

    /**
	 * 医院名称
	 */
	@ApiModelProperty(value = "医院名称", example = "厦门市中医院")
    private String hospName;

    /**
	 * 职称代码
	 */
	@ApiModelProperty(value = "职称代码", example = "")
    private String jobTitleCode;

    /**
	 * 职称名称
	 */
	@ApiModelProperty(value = "职称名称", example = "副主任医师")
    private String jobTitleName;

    /**
     * 部门标识
     */
    @ApiModelProperty(value = "部门标识", example = "")
    private String deptCode;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", example = "骨科专业")
    private String deptName;

    /**
	 * 作废标识，1正常，0作废
	 */
	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "1")
    private String del;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "", example = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public String getHospCode() {
        return hospCode;
    }
    public void setHospCode(String hospCode) {
        this.hospCode = hospCode;
    }

    public String getHospName() {
        return hospName;
    }
    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public String getJobTitleCode() {
        return jobTitleCode;
    }
    public void setJobTitleCode(String jobTitleCode) {
        this.jobTitleCode = jobTitleCode;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }
    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}