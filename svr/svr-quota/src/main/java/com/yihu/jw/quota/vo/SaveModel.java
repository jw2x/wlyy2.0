package com.yihu.jw.quota.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

/**
 * Created by chenweida on 2017/6/1.
 */
@Document(indexName = "index_sex", type = "sex", shards = 2)
public class SaveModel {
    @Id
    private String id;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String saasId;//saasId

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String quotaCode;//指标code

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String quotaDate;//统计时间

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String province;//省级代码 350000

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String provinceName;//省名字

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String city;//城市代码 350200

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String cityName;//

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String town;//区代码 350206

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String townName;//

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String hospital;//机构code

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String hospitalName;//

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String team;//团队的code

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String teamName;//

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String slaveKey1;//从维度  1级维度

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String slaveKey1Name;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String slaveKey2;//从维度  2级维度

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String slaveKey2Name;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String slaveKey3;//从维度  3级维度

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String slaveKey3Name;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String slaveKey4;//从维度  4级维度

    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String slaveKey4Name;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String result;//统计结果

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String timeLevel;// 1 日 2 周 3 月 4 年

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)

    private String areaLevel;// 1 省 2 市 3 区县 4 机构 5团队
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time, index = FieldIndex.not_analyzed)
    @CreatedDate
    private Date createTime;//创建时间

    public String getQuotaDate() {
        return quotaDate;
    }

    public void setQuotaDate(String quotaDate) {
        this.quotaDate = quotaDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getSlaveKey1() {
        return slaveKey1;
    }

    public void setSlaveKey1(String slaveKey1) {
        this.slaveKey1 = slaveKey1;
    }

    public String getSlaveKey2() {
        return slaveKey2;
    }

    public void setSlaveKey2(String slaveKey2) {
        this.slaveKey2 = slaveKey2;
    }

    public String getSlaveKey3() {
        return slaveKey3;
    }

    public void setSlaveKey3(String slaveKey3) {
        this.slaveKey3 = slaveKey3;
    }

    public String getSlaveKey4() {
        return slaveKey4;
    }

    public void setSlaveKey4(String slaveKey4) {
        this.slaveKey4 = slaveKey4;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSlaveKey1Name() {
        return slaveKey1Name;
    }

    public void setSlaveKey1Name(String slaveKey1Name) {
        this.slaveKey1Name = slaveKey1Name;
    }

    public String getSlaveKey2Name() {
        return slaveKey2Name;
    }

    public void setSlaveKey2Name(String slaveKey2Name) {
        this.slaveKey2Name = slaveKey2Name;
    }

    public String getSlaveKey3Name() {
        return slaveKey3Name;
    }

    public void setSlaveKey3Name(String slaveKey3Name) {
        this.slaveKey3Name = slaveKey3Name;
    }

    public String getSlaveKey4Name() {
        return slaveKey4Name;
    }

    public void setSlaveKey4Name(String slaveKey4Name) {
        this.slaveKey4Name = slaveKey4Name;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getQuotaCode() {
        return quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    public String getTimeLevel() {
        return timeLevel;
    }

    public void setTimeLevel(String timeLevel) {
        this.timeLevel = timeLevel;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
