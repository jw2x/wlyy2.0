package com.yihu.jw.entity.iot.product;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 设备测量数据表
 * @author yeshijie on 2018/1/16.
 */
@Entity
@Table(name = "iot_product_measured_data")
public class IotProductMeasuredDataDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "product_id")
    private String productId;//产品ID
    @Column(name = "company_id")
    private String companyId;//企业id
    @Column(name = "data_code")
    private String dataCode;//编码
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;//类型
    @Column(name = "instruction")
    private String instruction;//说明
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
