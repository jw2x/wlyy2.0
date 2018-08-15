package com.yihu.jw.entity.iot.product;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 产品扩展信息表
 * @author yeshijie on 2018/1/16.
 */
@Entity
@Table(name = "iot_product_extend_info")
public class IotProductExtendInfoDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "product_id")
    private String productId;//产品ID
    @Column(name = "product_img")
    private String productImg;//产品图片
    @Column(name = "description")
    private String description;//产品说明
    @Column(name = "use_range")
    private String useRange;//适用范围
    @Column(name = "version")
    private String version;//版本
    @Column(name = "standard")
    private String standard;//产品标准
    @Column(name = "contraindication")
    private String contraindication;//禁忌症
    @Column(name = "specification")
    private String specification;//产品说明书
    @Column(name = "del")
    private Integer del;//删除标志

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
