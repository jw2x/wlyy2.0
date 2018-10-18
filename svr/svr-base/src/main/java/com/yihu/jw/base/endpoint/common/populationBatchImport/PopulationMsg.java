package com.yihu.jw.base.endpoint.common.populationBatchImport;


import com.yihu.jw.util.date.DateUtil;
import com.yihu.jw.util.excel.ExcelUtil;
import com.yihu.jw.util.excel.Validation;
import com.yihu.jw.util.excel.annotation.Location;
import com.yihu.jw.util.excel.annotation.Row;
import com.yihu.jw.util.excel.annotation.Title;
import com.yihu.jw.util.excel.annotation.ValidRepeat;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 *  基础人口信息列表-excel实体类
 *
 * @author zdm
 * @vsrsion 1.0
 * Created at 2018/10/15.
 */
@Row(start = 1)
@Title(names= "{'租户名称', '时间','常住人口', '户籍人口', '糖尿病人数', '高血压人数', '任务数'}")
public class PopulationMsg extends ExcelUtil implements Validation {

    @Location(x=0)
    @ValidRepeat
    String saasName;
    @Location(x=1)
    @ValidRepeat
    String year;
    @Location(x=2)
    @ValidRepeat
    Integer populationNum;
    @Location(x=3)
    @ValidRepeat
    Integer regisPopulationNum;
    @Location(x=4)
    @ValidRepeat
    Integer dmNum;
    @Location(x=5)
    @ValidRepeat
    Integer hbpNum;
    @Location(x=6)
    @ValidRepeat
    Integer taskNum;
    private String  saasId;
    //租户创建时间
    private Date saasCreateTime;
    //所属省代码
    private String  provinceCode;
    //省份名称
    private String  provinceName;
    //城市编码
    private String  cityCode;
    //城市名称
    private String  cityName;
    //所属区代码
    private String  districtCode;
    //区名
    private String  districtName;
    //慢病人数
    private Integer ncdNum;
    int yearNow = DateUtil.getNowYear();

    @Override
    public int validate(Map<String, Set> repeatMap) {
        int rs = 1;
        if(!repeatMap.get("saasName").add(saasName+year)){
            rs = 0;
            addErrorMsg("saasName", "已添加"+year+saasName+"的基础人口信息，请直接修改即可");
        }
        if(StringUtils.isBlank(year)){
            rs = 0;
            addErrorMsg("year", "年份不能为空！");
        }else if(year.compareTo(String.valueOf(yearNow))>0){
            rs = 0;
            addErrorMsg("year", "年份不能大于当前年份！");
        }
        if(populationNum<0){
            rs = 0;
            addErrorMsg("populationNum", "常住人口数不能小于0！");
        }
        if(regisPopulationNum<0){
            rs = 0;
            addErrorMsg("regisPopulationNum", "户籍人口数不能小于0！");
        }
        if(dmNum<0){
            rs = 0;
            addErrorMsg("dmNum", "糖尿病人口不能小于0！");
        }
        if(hbpNum<0){
            rs = 0;
            addErrorMsg("hbpNum", "高血压人口不能小于0！");
        }
        if(taskNum<0){
            rs = 0;
            addErrorMsg("taskNum", "任务数不能小于0！");
        }
        return rs;
    }

    public String getSaasName() {
        return saasName;
    }

    public void setSaasName(String saasName) {
        this.saasName = saasName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getPopulationNum() {
        return populationNum;
    }

    public void setPopulationNum(Integer populationNum) {
        this.populationNum = populationNum;
    }

    public Integer getRegisPopulationNum() {
        return regisPopulationNum;
    }

    public void setRegisPopulationNum(Integer regisPopulationNum) {
        this.regisPopulationNum = regisPopulationNum;
    }

    public Integer getDmNum() {
        return dmNum;
    }

    public void setDmNum(Integer dmNum) {
        this.dmNum = dmNum;
    }

    public Integer getHbpNum() {
        return hbpNum;
    }

    public void setHbpNum(Integer hbpNum) {
        this.hbpNum = hbpNum;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Date getSaasCreateTime() {
        return saasCreateTime;
    }

    public void setSaasCreateTime(Date saasCreateTime) {
        this.saasCreateTime = saasCreateTime;
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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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

    public Integer getNcdNum() {
        return ncdNum;
    }

    public void setNcdNum(Integer ncdNum) {
        this.ncdNum = ncdNum;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }
}
