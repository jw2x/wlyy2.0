package com.yihu.jw.restmodel.iot.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 企业三证变更记录表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "企业三证变更记录")
public class IotCompanyCertificateChangeRecordVO extends BaseVO implements Serializable {

    @ApiModelProperty("企业名称")
    private String companyName;
    @ApiModelProperty("企业id")
    private String companyId;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("原证书号码")
    private String licenseOld;
    @ApiModelProperty("原证书扫描件")
    private String certificateOld;
    @ApiModelProperty("新证书号码")
    private String licenseNew;
    @ApiModelProperty("新证书扫描件")
    private String certificateNew;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicenseOld() {
        return licenseOld;
    }

    public void setLicenseOld(String licenseOld) {
        this.licenseOld = licenseOld;
    }

    public String getCertificateOld() {
        return certificateOld;
    }

    public void setCertificateOld(String certificateOld) {
        this.certificateOld = certificateOld;
    }

    public String getLicenseNew() {
        return licenseNew;
    }

    public void setLicenseNew(String licenseNew) {
        this.licenseNew = licenseNew;
    }

    public String getCertificateNew() {
        return certificateNew;
    }

    public void setCertificateNew(String certificateNew) {
        this.certificateNew = certificateNew;
    }
}
