package com.yihu.jw.entity.base.module;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 接口入参出参
 * @author yeshijie on 2018/10/11.
 */
@Entity
@Table(name = "base_saas_interface_param")
public class SaasInterfaceParamDO extends UuidIdentityEntity {

    private String saasId;//saas_id
    private String saasInterfaceId;//接口id
    private String name;//参数名
    private Integer paramType;//参数类型
    private Integer dataType;//数据类型
    private Integer isRequire;//是否必填(1是，0否)
    private Integer maxLength;//最大长度
    private String description;//描述
    private String example;//示例
    private Integer type;//类型（1入参，2出参）
    private Integer sort;//排序
    private Integer del;//删除标志

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "saas_interface_id")
    public String getSaasInterfaceId() {
        return saasInterfaceId;
    }

    public void setSaasInterfaceId(String saasInterfaceId) {
        this.saasInterfaceId = saasInterfaceId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "param_type")
    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    @Column(name = "data_type")
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    @Column(name = "is_require")
    public Integer getIsRequire() {
        return isRequire;
    }

    public void setIsRequire(Integer isRequire) {
        this.isRequire = isRequire;
    }

    @Column(name = "max_length")
    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "example")
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "del")
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
