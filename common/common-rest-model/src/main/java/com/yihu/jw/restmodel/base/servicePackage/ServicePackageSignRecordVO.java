package com.yihu.jw.restmodel.base.servicePackage;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *
 * @author yeshijie on 2018/8/17.
 */
@ApiModel(value = "ServicePackageSignRecordVO", description = "服务包签约记录表")
public class ServicePackageSignRecordVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saasId", example = "EwC0iRSrcS")
    private String saasId;
    @ApiModelProperty(value = "服务包id", example = "EwC0iRSrcS")
    private String servicePackageId;//服务包id
    @ApiModelProperty(value = "服务包名称", example = "康复计划")
    private String servicePackageName;//服务包名称
    @ApiModelProperty(value = "居民code", example = "EwC0iRSrcS")
    private String patient;//居民code
    @ApiModelProperty(value = "居民姓名", example = "李四")
    private String name;//居民姓名
    @ApiModelProperty(value = "居民身份证", example = "350124199584724")
    private String idcard;//居民身份证
    @ApiModelProperty(value = "社保卡号", example = "568241")
    private String ssc;//社保卡号
    @ApiModelProperty(value = "签约医生code", example = "EwC0iRSrcS")
    private String signDoctor;//签约医生code
    @ApiModelProperty(value = "签约医生名称", example = "张飒")
    private String signDoctorName;//签约医生名称
    @ApiModelProperty(value = "医院code", example = "EwC0iRSrcS")
    private String hospital;//医院code
    @ApiModelProperty(value = "医院名称", example = "第一医院")
    private String hospitalName;//医院名称
    @ApiModelProperty(value = "行政团队id", example = "EwC0iRSrcS")
    private Long adminTeamCode;//行政团队id
    @ApiModelProperty(value = "服务总价", example = "445554")
    private Long price;//服务总价
    @ApiModelProperty(value = "服务开始时间")
    private Date startTime;//服务开始时间
    @ApiModelProperty(value = "服务结束时间")
    private Date endTime;//服务结束时间
    @ApiModelProperty(value = "状态（1新建，2已完成）")
    private String status;//状态（1新建，2已完成）
    @ApiModelProperty(value = "医生服务团队code", example = "EwC0iRSrcS")
    private String doctorTeamCode;//医生服务团队code
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(String servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSsc() {
        return ssc;
    }

    public void setSsc(String ssc) {
        this.ssc = ssc;
    }

    public String getSignDoctor() {
        return signDoctor;
    }

    public void setSignDoctor(String signDoctor) {
        this.signDoctor = signDoctor;
    }

    public String getSignDoctorName() {
        return signDoctorName;
    }

    public void setSignDoctorName(String signDoctorName) {
        this.signDoctorName = signDoctorName;
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

    public Long getAdminTeamCode() {
        return adminTeamCode;
    }

    public void setAdminTeamCode(Long adminTeamCode) {
        this.adminTeamCode = adminTeamCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorTeamCode() {
        return doctorTeamCode;
    }

    public void setDoctorTeamCode(String doctorTeamCode) {
        this.doctorTeamCode = doctorTeamCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
