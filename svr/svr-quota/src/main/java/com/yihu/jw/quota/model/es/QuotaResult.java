package com.yihu.jw.quota.model.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

/**
 * QuotaResult entity. @author MyEclipse Persistence Tools
 */
@Document(indexName = "index", type = "user", shards = 5, replicas = 0, refreshInterval = "-1")
public class QuotaResult implements java.io.Serializable {
    @Id
    private String id;
    private String quotaDate;//统计时间
    private String quatoCode;//指标code
    private String quatoName;//指标name
    private String result;//统计结果
    private String del;//1: 正常 0： 删除
    private String level1Type;//等级 1:团队 2社区机构 3区级 4市级
    private String level2Type;//如果有二级维度那么这个存的是二级维度的code 例如二级维度是性别 那么这个存 1
    private String level2TypeName;//如果有二级维度那么这个存的是二级维度的code 例如二级维度是性别 那么这个存 男
    private String level3Type;//如果有三级维度那么这个存的是三级维度的code 例如三级维度是疾病 那么这个存 1
    private String level3TypeName;//如果有三级维度那么这个存的是三级维度的code 例如三级维度是疾病 那么这个存 高血压
    private String city;//市
    private String cityName;//市级名称
    private String town;//区级
    private String townName;//区级名称
    private String orgCode;//机构code
    private String orgName;//机构名称
    private String qkdoctorName;//全科医生名称 --现在是团队名称
    private String qkdoctorCode;//全科医生code --现在是团队code
    private String qkdoctorJobName;//全科医生职称   用于检验统计线程判断指标是否需要重新生成  等于 1 的时候是不需要

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time, index = FieldIndex.not_analyzed)
    @CreatedDate
    private Date createTime;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuotaDate() {
        return quotaDate;
    }

    public void setQuotaDate(String quotaDate) {
        this.quotaDate = quotaDate;
    }

    public String getQuatoCode() {
        return quatoCode;
    }

    public void setQuatoCode(String quatoCode) {
        this.quatoCode = quatoCode;
    }

    public String getQuatoName() {
        return quatoName;
    }

    public void setQuatoName(String quatoName) {
        this.quatoName = quatoName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getLevel1Type() {
        return level1Type;
    }

    public void setLevel1Type(String level1Type) {
        this.level1Type = level1Type;
    }

    public String getLevel2Type() {
        return level2Type;
    }

    public void setLevel2Type(String level2Type) {
        this.level2Type = level2Type;
    }

    public String getLevel2TypeName() {
        return level2TypeName;
    }

    public void setLevel2TypeName(String level2TypeName) {
        this.level2TypeName = level2TypeName;
    }

    public String getLevel3Type() {
        return level3Type;
    }

    public void setLevel3Type(String level3Type) {
        this.level3Type = level3Type;
    }

    public String getLevel3TypeName() {
        return level3TypeName;
    }

    public void setLevel3TypeName(String level3TypeName) {
        this.level3TypeName = level3TypeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getQkdoctorName() {
        return qkdoctorName;
    }

    public void setQkdoctorName(String qkdoctorName) {
        this.qkdoctorName = qkdoctorName;
    }

    public String getQkdoctorCode() {
        return qkdoctorCode;
    }

    public void setQkdoctorCode(String qkdoctorCode) {
        this.qkdoctorCode = qkdoctorCode;
    }

    public String getQkdoctorJobName() {
        return qkdoctorJobName;
    }

    public void setQkdoctorJobName(String qkdoctorJobName) {
        this.qkdoctorJobName = qkdoctorJobName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}