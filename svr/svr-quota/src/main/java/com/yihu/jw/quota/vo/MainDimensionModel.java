package com.yihu.jw.quota.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
public class MainDimensionModel {
    private String quotaCode;//指标code
    private String province;//省级代码 350000
    private String provinceName;//省名字
    private String city;//城市代码 350200
    private String cityName;//
    private String town;//区代码 350206
    private String townName;//
    private String hospital;//机构code
    private String hospitalName;//
    private String team;//团队的code
    private String teamName;//
    private String areaLevel;// 1 省 2 市 3 区县 4 机构 5团队
    //用来计算从维度的字段--------------
    private String slaveKey1;//从维度  1级维度
    private String slaveKey1Name;
    private String slaveKey2;//从维度  2级维度
    private String slaveKey2Name;
    private String slaveKey3;//从维度  3级维度
    private String slaveKey3Name;
    private String slaveKey4;//从维度  4级维度
    private String slaveKey4Name;
    //用来计算从维度的字段--------------

    List<SlaveDimensionModel> slaveDimensionModels=new ArrayList<>();

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getQuotaCode() {
        return quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<SlaveDimensionModel> getSlaveDimensionModels() {
        return slaveDimensionModels;
    }

    public void setSlaveDimensionModels(List<SlaveDimensionModel> slaveDimensionModels) {
        this.slaveDimensionModels = slaveDimensionModels;
    }

    public String getSlaveKey1() {
        return slaveKey1;
    }

    public void setSlaveKey1(String slaveKey1) {
        this.slaveKey1 = slaveKey1;
    }

    public String getSlaveKey1Name() {
        return slaveKey1Name;
    }

    public void setSlaveKey1Name(String slaveKey1Name) {
        this.slaveKey1Name = slaveKey1Name;
    }

    public String getSlaveKey2() {
        return slaveKey2;
    }

    public void setSlaveKey2(String slaveKey2) {
        this.slaveKey2 = slaveKey2;
    }

    public String getSlaveKey2Name() {
        return slaveKey2Name;
    }

    public void setSlaveKey2Name(String slaveKey2Name) {
        this.slaveKey2Name = slaveKey2Name;
    }

    public String getSlaveKey3() {
        return slaveKey3;
    }

    public void setSlaveKey3(String slaveKey3) {
        this.slaveKey3 = slaveKey3;
    }

    public String getSlaveKey3Name() {
        return slaveKey3Name;
    }

    public void setSlaveKey3Name(String slaveKey3Name) {
        this.slaveKey3Name = slaveKey3Name;
    }

    public String getSlaveKey4() {
        return slaveKey4;
    }

    public void setSlaveKey4(String slaveKey4) {
        this.slaveKey4 = slaveKey4;
    }

    public String getSlaveKey4Name() {
        return slaveKey4Name;
    }

    public void setSlaveKey4Name(String slaveKey4Name) {
        this.slaveKey4Name = slaveKey4Name;
    }
}
