package com.yihu.jw.iot.product;

import com.yihu.jw.IdEntityWithOperation;

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
public class IotProductMeasuredDataDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saas_id;//
    @Column(name = "product_id")
    private String product_id;//产品ID
    @Column(name = "company_id")
    private String company_id;//企业id
    @Column(name = "data_code")
    private String data_code;//编码
    @Column(name = "type")
    private String type;//类型
    @Column(name = "instruction")
    private String instruction;//说明
    @Column(name = "del")
    private Integer del;//删除标志

    public String getSaas_id() {
        return saas_id;
    }

    public void setSaas_id(String saas_id) {
        this.saas_id = saas_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getData_code() {
        return data_code;
    }

    public void setData_code(String data_code) {
        this.data_code = data_code;
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

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
