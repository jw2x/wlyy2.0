package com.yihu.jw.restmodel.iot.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 产品扩展信息表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "产品基本信息表")
public class IotProductExtendInfoVO extends BaseVO implements Serializable {

    @ApiModelProperty("产品图片")
    private String productImg;
    @ApiModelProperty("产品说明")
    private String description;
    @ApiModelProperty("适用范围")
    private String useRange;
    @ApiModelProperty("版本")
    private String version;
    @ApiModelProperty("产品标准")
    private String standard;
    @ApiModelProperty("禁忌症")
    private String contraindication;
    @ApiModelProperty("产品说明书")
    private String specification;
    @ApiModelProperty("附件列表")
    private List<IotProductAttachmentVO> attachmentVOList;

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUseRange() {
        return useRange;
    }

    public void setUseRange(String useRange) {
        this.useRange = useRange;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getContraindication() {
        return contraindication;
    }

    public void setContraindication(String contraindication) {
        this.contraindication = contraindication;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public List<IotProductAttachmentVO> getAttachmentVOList() {
        return attachmentVOList;
    }

    public void setAttachmentVOList(List<IotProductAttachmentVO> attachmentVOList) {
        this.attachmentVOList = attachmentVOList;
    }
}
