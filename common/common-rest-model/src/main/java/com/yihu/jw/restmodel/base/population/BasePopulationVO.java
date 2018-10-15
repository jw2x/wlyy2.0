package com.yihu.jw.restmodel.base.population;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.util.Date;


/**
 * 
 * 基础人口基数信息vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年09月26日 update
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BasePopulationVO", description = "基础人口基数信息")
public class BasePopulationVO extends UuidIdentityVOWithOperator {

    //saas化的id
    @ApiModelProperty(value = "saasId", example = "自然数")
    private String  saasId;
    //租户名称
    @ApiModelProperty(value = "租户名称", example = "i健康")
    private String  saasName;
    //所属省代码
    @ApiModelProperty(value = "所属省代码", example = "350000")
    private String  provinceCode;
    //省份名称
    @ApiModelProperty(value = "省份名称", example = "福建省")
    private String  provinceName;
    //城市名称
    @ApiModelProperty(value = "城市名称", example = "厦门市")
    private String  cityName;
    //城市名称
    @ApiModelProperty(value = "城市编码", example = "350200")
    private String  cityCode;
    //所属区代码
    @ApiModelProperty(value = "所属区代码", example = "350203")
    private String  districtCode;
    //区名
    @ApiModelProperty(value = "所属区名称", example = "思明区")
    private String  districtName;
    //户籍人口数
    @ApiModelProperty(value = "户籍人口数", example = "自然数")
    private Integer  regisPopulationNum ;
    //常住人口数
    @ApiModelProperty(value = "常住人口数", example = "自然数")
    private Integer  populationNum;
    //类别 0是省，1是市，2是区，3是机构
    @ApiModelProperty(value = "类别 0是省，1是市，2是区，3是机构", example = "自然数")
    private String  type;
    //时间（年份）
    @ApiModelProperty(value = "时间", example = "2018年")
    private String  year;
    // 高血压人口数,HBP为医学简称
    @ApiModelProperty(value = "高血压人口数", example = "自然数")
    private Integer  hbpNum;
    // 糖尿病人口数,DM为医学简称
    @ApiModelProperty(value = "糖尿病人口数", example = "自然数")
    private Integer  dmNum;
    //高血压任务数
    @ApiModelProperty(value = "高血压任务数", example = "自然数")
    private Integer  hbpTaskNum;
    //糖尿病任务数
    @ApiModelProperty(value = "糖尿病任务数", example = "自然数")
    private Integer  dmTaskNum;
    //任务数
    @ApiModelProperty(value = "任务数", example = "自然数")
    private Integer  taskNum;
    @ApiModelProperty(value = "慢病人数（高血压人口数+糖尿病人口数）", example = "自然数")
    private Integer ncdNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @ApiModelProperty(value = "租户创建时间", example = "yyyy-MM-dd HH:mm:ss")
    private Date  saasCreateTime;

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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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

    public Date getSaasCreateTime() {
        return saasCreateTime;
    }

    public void setSaasCreateTime(Date saasCreateTime) {
        this.saasCreateTime = saasCreateTime;
    }
}