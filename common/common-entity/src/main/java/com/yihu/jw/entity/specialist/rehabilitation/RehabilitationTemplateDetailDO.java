package com.yihu.jw.entity.specialist.rehabilitation;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * Created by humingfen on 2018/8/15.
 */
@Entity
@Table(name = "wlyy_rehabilitation_template_detail")
public class RehabilitationTemplateDetailDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "template_id")
    private String templateId;//康复套餐模板id
    @Column(name = "hospital_service_item_id")
    private String hospitalServiceItemId;//机构服务项目id

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "template_id")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Column(name = "hospital_service_item_id")
    public String getHospitalServiceItemId() {
        return hospitalServiceItemId;
    }

    public void setHospitalServiceItemId(String hospitalServiceItemId) {
        this.hospitalServiceItemId = hospitalServiceItemId;
    }
}
