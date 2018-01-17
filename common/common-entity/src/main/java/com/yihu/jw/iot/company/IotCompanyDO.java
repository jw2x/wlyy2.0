package com.yihu.jw.iot.company;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 企业表
 * @author yeshijie on 2018/1/15.
 */
@Entity
@Table(name = "iot_company")
public class IotCompanyDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "status")
    private String status;//审核状态（预留字段）
    @Column(name = "name")
    private String name;//企业名称
    @Column(name = "is_three_in_one")
    private Integer isThreeInOne;//是否三证合一（1是，0否）
    @Column(name = "business_license")
    private String businessLicense;//统一社会信用代码/营业执照
    @Column(name = "business_start_time")
    private Date businessStartTime;//营业开始时间
    @Column(name = "business_end_time")
    private Date businessEndTime;//营业结束时间
    @Column(name = "organization_address")
    private String organizationAddress;//机构地址
    @Column(name = "office_phone")
    private String officePhone;//办公电话
    @Column(name = "contacts_name")
    private String contactsName;//联系人姓名
    @Column(name = "contacts_mobile")
    private String contactsMobile;//联系人手机号码
    @Column(name = "contacts_idcard")
    private String contactsIdcard;//联系人身份证号
    @Column(name = "contacts_email")
    private String contactsEmail;//联系人邮件
    @Column(name = "business_license_img")
    private String businessLicenseImg;//统一社会信用代码证照片
    @Column(name = "organization_code_img")
    private String organizationCodeImg;//组织机构代码证照片
    @Column(name = "tax_registration_img")
    private String taxRegistrationImg;//税务登记证照片
    @Column(name = "contacts_idcard_img")
    private String contactsIdcardImg;//联系人身份证照片
    @Column(name = "account")
    private String account;//账号
    @Column(name = "del")
    private Integer del;//删除标志(1有效，0删除)

    @Transient
    private List<IotCompanyTypeDO> typeList;//类型

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public List<IotCompanyTypeDO> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<IotCompanyTypeDO> typeList) {
        this.typeList = typeList;
    }
}
