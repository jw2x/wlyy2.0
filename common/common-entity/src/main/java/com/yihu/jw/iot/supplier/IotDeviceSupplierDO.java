package com.yihu.jw.iot.supplier;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yeshijie on 2017/12/1.
 */
@Entity
@Table(name = "iot_device_supplier")
public class IotDeviceSupplierDO extends IdEntityWithOperation implements Serializable{

    private static final long serialVersionUID = 41357656279822210L;

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "supplier_name")
    private String supplierName;//供应商名称

    @Column(name = "organization_code")
    private String organizationCode;//组织机构代码/统一社会信用代码

    @Column(name = "juridical_person_name")
    private String juridicalPersonName;//法定代表人/负责人姓名

    @Column(name = "organization_address")
    private String organizationAddress;//机构地址

    @Column(name = "office_phone")
    private String officePhone;//办公电话

    @Column(name = "contacts_name")
    private String contactsName;//联系人姓名

    @Column(name = "contacts_mobile")
    private String contactsMobile;//联系人手机号码

    @Column(name = "contacts_phone")
    private String contactsPhone;//联系人座机

    @Column(name = "contacts_idcard")
    private String contactsIdcard;//联系人身份证号

    @Column(name = "contacts_email")
    private String contactsEmail;//联系人邮件

    @Column(name = "type")
    private String type;//类型 1、供应商，2、厂商

    @Column(name = "organization_code_img")
    private String organizationCodeImg;//组织机构代码证照片

    @Column(name = "contacts_idcard_img")
    private String contactsIdcardImg;//联系人身份证照片

    @Column(name = "data_transmission_mode")
    private String dataTransmissionMode;//数据传输方式

    @Column(name = "del")
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
