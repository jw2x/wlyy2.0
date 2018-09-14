package com.yihu.jw.entity.base.saas;


import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 *     field - 必须和对应实体的字段名相同
 * </p>
 *
 * Entity - SAAS名片
 * @author progr1mmer
 * @date Created on 2018/9/7.
 */
@Entity
@Table(name = "base_saas_business_card")
public class SaasBusinessCardDO extends IntegerIdentityEntity {

    public enum Type {
        /**
         * 医生
         */
        doctor,
        /**
         * 患者
         */
        patient
    }

    //SAAS ID
    private String saasId;
    //字段
    private String field;
    //类型
    private Type type;

    @Column(name = "saas_id", nullable = false)
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "field", nullable = false)
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Column(name = "type", nullable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
