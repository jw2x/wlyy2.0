package com.yihu.jw.restmodel.base.statistics;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author yeshijie on 2018/8/31.
 */
@ApiModel(value = "DimensionQuotaVO", description = "统计维度指标")
public class DimensionQuotaVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saasId")
    private String saasId;
    @ApiModelProperty(value = "关联base_quota的code")
    private String quotaCode;//
    @ApiModelProperty(value = "关联base_dimension的code")
    private String dimensionCode;//
    @ApiModelProperty(value = "查询字典的sql语句")
    private String dictSql;//
    @ApiModelProperty(value = "数据转换的类")
    private String convertClazz;//
    @ApiModelProperty(value = "维度顺序")
    private Integer sort;//
    @ApiModelProperty(value = "查询出来的key")
    private String key;//

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getQuotaCode() {
        return quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    public String getDimensionCode() {
        return dimensionCode;
    }

    public void setDimensionCode(String dimensionCode) {
        this.dimensionCode = dimensionCode;
    }

    public String getDictSql() {
        return dictSql;
    }

    public void setDictSql(String dictSql) {
        this.dictSql = dictSql;
    }

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
