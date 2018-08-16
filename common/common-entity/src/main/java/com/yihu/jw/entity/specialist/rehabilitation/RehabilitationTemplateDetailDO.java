package com.yihu.jw.entity.specialist.rehabilitation;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * Created by humingfen on 2018/8/15.
 */
@Entity
@Table(name = "wlyy_rehabilitation_template_detail")
public class RehabilitationTemplateDetailDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "template_id")
    private String templateId;//康复套餐模板id
    @Column(name = "service_item_id")
    private String serviceId;//服务项目id

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

}
