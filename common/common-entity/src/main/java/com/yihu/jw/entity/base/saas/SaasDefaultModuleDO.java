package com.yihu.jw.entity.base.saas;

import com.yihu.jw.UuidIdentityEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_saas_default_module")
public class SaasDefaultModuleDO extends UuidIdentityEntity {

    private SaasDO.Type type;
    private Integer moduleId;

    public SaasDO.Type getType() {
        return type;
    }

    public void setType(SaasDO.Type type) {
        this.type = type;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}
