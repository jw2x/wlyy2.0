package com.yihu.jw.restmodel.iot.supplier;

import java.io.Serializable;

/**
 * @author yeshijie on 2017/12/1.
 */
public class IotDeviceSupplierVO implements Serializable{

    private String saasId;
    private String supplierName;//供应商名称
    private String organizationCode;//组织机构代码/统一社会信用代码
    private String juridicalPersonName;//法定代表人/负责人姓名
    private String organizationAddress;//机构地址
    private String officePhone;//办公电话
    private String contactsName;//联系人姓名
    private String contactsMobile;//联系人手机号码
    private String contactsPhone;//联系人座机
    private String contactsIdcard;//联系人身份证号
    private String contactsEmail;//联系人邮件
    private String type;//类型 1、供应商，2、厂商
    private String organizationCodeImg;//组织机构代码证照片
    private String contactsIdcardImg;//联系人身份证照片
    private String dataTransmissionMode;//数据传输方式
    private Integer del;//删除标志(1有效，0删除)

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getJuridicalPersonName() {
        return juridicalPersonName;
    }

    public void setJuridicalPersonName(String juridicalPersonName) {
        this.juridicalPersonName = juridicalPersonName;
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

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganizationCodeImg() {
        return organizationCodeImg;
    }

    public void setOrganizationCodeImg(String organizationCodeImg) {
        this.organizationCodeImg = organizationCodeImg;
    }

    public String getContactsIdcardImg() {
        return contactsIdcardImg;
    }

    public void setContactsIdcardImg(String contactsIdcardImg) {
        this.contactsIdcardImg = contactsIdcardImg;
    }

    public String getDataTransmissionMode() {
        return dataTransmissionMode;
    }

    public void setDataTransmissionMode(String dataTransmissionMode) {
        this.dataTransmissionMode = dataTransmissionMode;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
