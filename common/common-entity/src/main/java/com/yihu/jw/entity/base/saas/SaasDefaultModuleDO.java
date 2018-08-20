package com.yihu.jw.entity.base.saas;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_saas_default_module")
public class SaasDefaultModuleDO extends IntegerIdentityEntity {

    private SaasDO.Type type;
    private Integer moduleId;

    @Column(name = "type", nullable = false)
    public SaasDO.Type getType() {
        return type;
    }

    public void setType(SaasDO.Type type) {
        this.type = type;
    }

    @Column(name = "module_id", nullable = false)
    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}
