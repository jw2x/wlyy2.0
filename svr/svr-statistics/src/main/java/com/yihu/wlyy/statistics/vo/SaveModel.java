package com.yihu.wlyy.statistics.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.searchbox.annotations.JestId;
import org.elasticsearch.index.analysis.AnalysisSettingsRequired;
import org.springframework.data.annotation.CreatedDate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/1.
 */
public class SaveModel {
    private final static String teamKey = "team";
    private final static String OrgKey = "hospital";
    private final static String townKey = "town";
    private final static String cityKey = "city";

    public final static String interval_day = "1";
    public final static String interval_week = "2";
    public final static String interval_month = "3";

    public final static String teamLevel = "5";
    public final static String OrgLevel = "4";
    public final static String townLevel = "3";
    public final static String cityLevel = "2";
    private static final Map<String, String> fieldsSithch = new HashMap<>();

    @JestId
    private String id;

    private String saasId;//saasId

    private String quotaCode;//指标code

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX")
    @CreatedDate
    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ssXX")
    private Date quotaDate;//统计时间

    private String city;//城市代码 350200

    private String cityName;//

    private String town;//区代码 350206

    private String townName;//

    private String hospital;//机构code

    private String hospitalName;//

    private String team;//团队的code

    private String teamName;//

    private String slaveKey1;//从维度  1级维度

    private String slaveKey1Name;

    private String slaveKey2;//从维度  2级维度

    private String slaveKey2Name;

    private String slaveKey3;//从维度  3级维度

    private String slaveKey3Name;

    private String slaveKey4;//从维度  4级维度

    private String slaveKey4Name;

    private Double result1 = 0.0;//统计结果  总的累加结果 如果是平均分 那就是总分 其他指标都是和result2一样

    private Double result2 = 0.0;//统计结果  次数

    private String areaLevel;// 1 省 2 市 3 区县 4 机构 5团队
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX")
    @CreatedDate
    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ssXX")
    private Date createTime;//创建时间

    private String timeLevel;// 1增量 2到达量

    public Date getQuotaDate() {
        return quotaDate;
    }

    public void setQuotaDate(Date quotaDate) {
        this.quotaDate = quotaDate;
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

    //@Field(type = FieldType.String, analyzer="ngram_analyzer")//使用ngram进行单字分词
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

    public Double getResult1() {
        return result1;
    }

    public void setResult1(Double result1) {
        this.result1 = result1;
    }

    public Double getResult2() {
        return result2;
    }

    public void setResult2(Double result2) {
        this.result2 = result2;
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

    public String getTimeLevel() {
        return timeLevel;
    }

    public void setTimeLevel(String timeLevel) {
        this.timeLevel = timeLevel;
    }

    /**
     * 根据级别得到key
     * 1 省 2 市 3 区县 4 机构 5团队
     *
     * @param level
     * @return
     */
    public static String getAreaLevelKey(String level) {
        switch (level) {
            case teamLevel: {
                return teamKey;
            }
            case OrgLevel: {
                return OrgKey;
            }
            case townLevel: {
                return townKey;
            }
            case cityLevel: {
                return cityKey;
            }
            default: {
                return teamKey;
            }
        }
    }

}
