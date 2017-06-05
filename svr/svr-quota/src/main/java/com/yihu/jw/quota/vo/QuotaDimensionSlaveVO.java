package com.yihu.jw.quota.vo;

/**
 * Created by chenweida on 2017/6/5.
 */
public class QuotaDimensionSlaveVO {
    private Integer id;
    private String quotaCode;
    private String slaveCode;
    private String dictSql;//字典的sql
    private String convertClazz;//转换的类
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuotaCode() {
        return quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    public String getSlaveCode() {
        return slaveCode;
    }

    public void setSlaveCode(String slaveCode) {
        this.slaveCode = slaveCode;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
