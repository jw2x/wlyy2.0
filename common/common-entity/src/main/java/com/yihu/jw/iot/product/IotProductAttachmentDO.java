package com.yihu.jw.iot.product;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 产品附件表
 * @author yeshijie on 2018/1/16.
 */
@Entity
@Table(name = "iot_product_attachment")
public class IotProductAttachmentDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "product_id")
    private String productId;//产品ID
    @Column(name = "product_extend_id")
    private String productExtendId;//产品扩展信息ID
    @Column(name = "type")
    private String type;//附件类型（1产品说明书，2其他附件）
    @Column(name = "attachment")
    private String attachment;//附件
    @Column(name = "name")
    private String name;//附件名称
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

    public String getProductExtendId() {
        return productExtendId;
    }

    public void setProductExtendId(String productExtendId) {
        this.productExtendId = productExtendId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
