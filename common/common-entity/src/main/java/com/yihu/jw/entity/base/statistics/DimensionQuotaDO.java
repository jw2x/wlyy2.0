package com.yihu.jw.entity.base.statistics;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 统计维度指标表
 * @author yeshijie on 2018/8/31.
 */
@Entity
@Table(name = "base_dimension_quota")
public class DimensionQuotaDO extends UuidIdentityEntity implements Serializable {

    private String saasId;
    private String quotaCode;//关联base_quota的code
    private String dimensionCode;//关联base_dimension的code
    private String dictSql;//查询字典的sql语句
    private String convertClazz;//数据转换的类
    private Integer sort;//维度顺序
    private String key;//查询出来的key

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "quota_code")
    public String getQuotaCode() {
        return quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    @Column(name = "dimension_code")
    public String getDimensionCode() {
        return dimensionCode;
    }

    public void setDimensionCode(String dimensionCode) {
        this.dimensionCode = dimensionCode;
    }

    @Column(name = "dict_sql")
    public String getDictSql() {
        return dictSql;
    }

    public void setDictSql(String dictSql) {
        this.dictSql = dictSql;
    }

    @Column(name = "convert_clazz")
    public String getConvertClazz() {
        return convertClazz;
    }

    public void setConvertClazz(String convertClazz) {
        this.convertClazz = convertClazz;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
