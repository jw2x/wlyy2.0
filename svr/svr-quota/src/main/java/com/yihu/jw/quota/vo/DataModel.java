package com.yihu.jw.quota.vo;

import com.yihu.jw.quota.etl.Contant;

/**
 * Created by chenweida on 2017/6/1.
 */
public class DataModel {
    private String businessId;//业务id
    private String province;//省级代码 350000
    private String city;//城市代码 350200
    private String town;//区代码 350206
    private String hospital;//机构code
    private String team;//团队的code
    private String slaveKey1;//从维度  1级维度
    private String slaveKey2;//从维度  2级维度
    private String slaveKey3;//从维度  3级维度
    private String slaveKey4;//从维度  4级维度

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getKey(String leveltype){
        switch (leveltype){
            case Contant.main_dimension_areaLevel.area_province:{return getProvince();}
            case Contant.main_dimension_areaLevel.area_city:{return getCity();}
            case Contant.main_dimension_areaLevel.area_town:{return getTown();}
            case Contant.main_dimension_areaLevel.area_org:{return getHospital();}
            case Contant.main_dimension_areaLevel.area_team:{return getTeam();}
        }
        return "";
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "businessId='" + businessId + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", hospital='" + hospital + '\'' +
                ", team='" + team + '\'' +
                ", slaveKey1='" + slaveKey1 + '\'' +
                ", slaveKey2='" + slaveKey2 + '\'' +
                ", slaveKey3='" + slaveKey3 + '\'' +
                ", slaveKey4='" + slaveKey4 + '\'' +
                '}';
    }
}
