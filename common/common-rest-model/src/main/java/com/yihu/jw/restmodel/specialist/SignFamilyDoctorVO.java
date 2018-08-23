package com.yihu.jw.restmodel.specialist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author huangwenjie
 * @date 2018/8/20 15:40
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "专病医生关联的家庭医生信息", description = "专病医生关联的家庭医生信息")
public class SignFamilyDoctorVO {
	@ApiModelProperty("医生Code")
	private String code;
	@ApiModelProperty("姓名")
	private String name;
	@ApiModelProperty("性别（1男，2女）")
	private Integer sex;
	@ApiModelProperty("生日")
	private Date birthday;
	@ApiModelProperty("头像http地址")
	private String photo;
	@ApiModelProperty("手机号")
	private String mobile;
	@ApiModelProperty("医院代码")
	private String hospital;
	@ApiModelProperty("医院名称")
	private String hospitalName;
	@ApiModelProperty("科室代码")
	private String dept;
	@ApiModelProperty("科室名称")
	private String deptName;
	@ApiModelProperty("职称代码")
	private String job;
	@ApiModelProperty("职称名称")
	private String jobName;
	@ApiModelProperty("类型：1专科医生，2全科医生，3健康管理师")
	private Integer level;
	@ApiModelProperty("类二维码")
	private String qrcode;
	@ApiModelProperty("更新时间")
	private Date czrq;
	@ApiModelProperty("状态（1正常，0删除）")
	private Integer del;
	@ApiModelProperty("身份证号")
	private String idcard;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSex() {
		return sex;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getHospital() {
		return hospital;
	}
	
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	public String getHospitalName() {
		return hospitalName;
	}
	
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	public String getDept() {
		return dept;
	}
	
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public String getJobName() {
		return jobName;
	}
	
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getQrcode() {
		return qrcode;
	}
	
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCzrq() {
		return czrq;
	}
	
	public void setCzrq(Date czrq) {
		this.czrq = czrq;
	}
	
	public Integer getDel() {
		return del;
	}
	
	public void setDel(Integer del) {
		this.del = del;
	}
	
	public String getIdcard() {
		return idcard;
	}
	
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
}
