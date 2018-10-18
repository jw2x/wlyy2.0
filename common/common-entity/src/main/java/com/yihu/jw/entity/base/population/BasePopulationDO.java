package com.yihu.jw.entity.base.population;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;


/**
* 基础人口基数信息实体
*
* @author litaohong on  2018年09月26日
*
*/
@Entity
@Table(name = "base_population")
public class BasePopulationDO extends UuidIdentityEntityWithOperator {
    //saas化的id
    @Column(name = "saas_id",nullable = false)
    private String  saasId;
    //租户名称
    @Column(name = "saas_name")
    private String  saasName;
    //租户创建时间
    @Column(name = "saas_create_time")
    private Date  saasCreateTime;
    //所属省代码
    @Column(name = "province_code")
    private String  provinceCode;
    //省份名称
    @Column(name = "province_name")
    private String  provinceName;
    //城市编码
    @Column(name = "city_code")
    private String  cityCode;
    //城市名称
    @Column(name = "city_name")
    private String  cityName;
    //所属区代码
    @Column(name = "district_code")
    private String  districtCode;
    //区名
    @Column(name = "district_name")
    private String  districtName;
    //户籍人口数
    @Column(name = "regis_population_num")
    private Integer  regisPopulationNum ;
    //常住人口数
    @Column(name = "population_num")
    private Integer  populationNum;
    //类别 0是省，1是市，2是区，3是机构
    @Column(name = "type")
    private String  type;
    //时间（年份）
    @Column(name = "year")
    private String  year;
    // 高血压人口数,HBP为医学简称
    @Column(name = "hbp_num")
    private Integer  hbpNum;
    // 糖尿病人口数,DM为医学简称
    @Column(name = "dm_num")
    private Integer  dmNum;
    //高血压任务数
    @Column(name = "hbp_task_num")
    private Integer  hbpTaskNum;
    //糖尿病任务数
    @Column(name = "dm_task_num")
    private Integer  dmTaskNum;
    //任务数
    @Column(name = "task_num")
    private Integer  taskNum;
    //慢病人数
    @Column(name = "ncd_num")
    private Integer ncdNum;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getSaasName() {
        return saasName;
    }

    public void setSaasName(String saasName) {
        this.saasName = saasName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getRegisPopulationNum() {
        return regisPopulationNum;
    }

    public void setRegisPopulationNum(Integer regisPopulationNum) {
        this.regisPopulationNum = regisPopulationNum;
    }

    public Integer getPopulationNum() {
        return populationNum;
    }

    public void setPopulationNum(Integer populationNum) {
        this.populationNum = populationNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getHbpNum() {
        return hbpNum;
    }

    public void setHbpNum(Integer hbpNum) {
        this.hbpNum = hbpNum;
    }

    public Integer getDmNum() {
        return dmNum;
    }

    public void setDmNum(Integer dmNum) {
        this.dmNum = dmNum;
    }

    public Integer getHbpTaskNum() {
        return hbpTaskNum;
    }

    public void setHbpTaskNum(Integer hbpTaskNum) {
        this.hbpTaskNum = hbpTaskNum;
    }

    public Integer getDmTaskNum() {
        return dmTaskNum;
    }

    public void setDmTaskNum(Integer dmTaskNum) {
        this.dmTaskNum = dmTaskNum;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Integer getNcdNum() {
        return ncdNum;
    }

    public void setNcdNum(Integer ncdNum) {
        this.ncdNum = ncdNum;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Date getSaasCreateTime() {
        return saasCreateTime;
    }

    public void setSaasCreateTime(Date saasCreateTime) {
        this.saasCreateTime = saasCreateTime;
    }

    @Transient
    public String getAddress() {
        String address = "";
        if (this.getProvinceName() != null) {
            address += this.getProvinceName();
        }
        if (this.getCityName() != null) {
            address += this.getCityName();
        }
        if (this.getDistrictName()!= null) {
            address += this.getDistrictName();
        }
        return address;
    }
}