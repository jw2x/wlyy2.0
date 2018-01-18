package com.yihu.jw.restmodel.iot.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 企业表
 * @author yeshijie on 2018/1/15.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "企业表", description = "企业表")
public class IotCompanyVO extends BaseVO implements Serializable {

    @ApiModelProperty("审核状态（预留字段）")
    private String status;
    @ApiModelProperty("企业名称")
    private String name;
    @ApiModelProperty("是否三证合一（1是，0否）")
    private Integer isThreeInOne;
    @ApiModelProperty("统一社会信用代码/营业执照")
    private String businessLicense;
    @ApiModelProperty("营业开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date businessStartTime;
    @ApiModelProperty("营业结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date businessEndTime;
    @ApiModelProperty("机构地址")
    private String organizationAddress;
    @ApiModelProperty("办公电话")
    private String officePhone;
    @ApiModelProperty("联系人姓名")
    private String contactsName;
    @ApiModelProperty("联系人手机号码")
    private String contactsMobile;
    @ApiModelProperty("联系人身份证号")
    private String contactsIdcard;
    @ApiModelProperty("联系人邮件")
    private String contactsEmail;
    @ApiModelProperty("统一社会信用代码证照片")
    private String businessLicenseImg;
    @ApiModelProperty("组织机构代码证照片")
    private String organizationCodeImg;
    @ApiModelProperty("税务登记证照片")
    private String taxRegistrationImg;
    @ApiModelProperty("联系人身份证照片")
    private String contactsIdcardImg;
    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("类型")
    private List<IotCompanyTypeVO> typeList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsThreeInOne() {
        return isThreeInOne;
    }

    public void setIsThreeInOne(Integer isThreeInOne) {
        this.isThreeInOne = isThreeInOne;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public Date getBusinessStartTime() {
        return businessStartTime;
    }

    public void setBusinessStartTime(Date businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    public Date getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(Date businessEndTime) {
        this.businessEndTime = businessEndTime;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public String getContactsIdcard() {
        return contactsIdcard;
    }

    public void setContactsIdcard(String contactsIdcard) {
        this.contactsIdcard = contactsIdcard;
    }

    public String getContactsEmail() {
        return contactsEmail;
    }

    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg;
    }

    public String getOrganizationCodeImg() {
        return organizationCodeImg;
    }

    public void setOrganizationCodeImg(String organizationCodeImg) {
        this.organizationCodeImg = organizationCodeImg;
    }

    public String getTaxRegistrationImg() {
        return taxRegistrationImg;
    }

    public void setTaxRegistrationImg(String taxRegistrationImg) {
        this.taxRegistrationImg = taxRegistrationImg;
    }

    public String getContactsIdcardImg() {
        return contactsIdcardImg;
    }

    public void setContactsIdcardImg(String contactsIdcardImg) {
        this.contactsIdcardImg = contactsIdcardImg;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<IotCompanyTypeVO> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<IotCompanyTypeVO> typeList) {
        this.typeList = typeList;
    }
}
