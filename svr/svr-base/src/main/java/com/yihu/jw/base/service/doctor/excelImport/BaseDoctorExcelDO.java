package com.yihu.jw.base.service.doctor.excelImport;


import com.yihu.jw.util.date.DateUtil;
import com.yihu.jw.util.excel.ExcelUtil;
import com.yihu.jw.util.excel.Validation;
import com.yihu.jw.util.excel.annotation.Location;
import com.yihu.jw.util.excel.annotation.Row;
import com.yihu.jw.util.excel.annotation.Title;
import com.yihu.jw.util.excel.annotation.ValidRepeat;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 *  医生信息列表-excel实体类
 * @author lith
 * Created at 2018/10/22.
 */
@Row(start = 1)
@Title(names= "{'姓名', '状态(1生效，0失效)','性别（1男2女）', '身份证号', '联系方式', '机构/部门/职务', '职称','归属业务模块角色','是否名医','专长','简介'}")
public class BaseDoctorExcelDO extends ExcelUtil implements Validation {

    @Location(x=0)
    @ValidRepeat
    String name;//姓名
    @Location(x=1)
    @ValidRepeat
    String del;//状态(1生效，0失效)
    @Location(x=2)
    @ValidRepeat
    Integer sex;//性别（1男2女）
    @Location(x=3)
    @ValidRepeat
    String idcard;//身份证号
    @Location(x=4)
    @ValidRepeat
    String mobile;//联系方式
    @Location(x=5)
    @ValidRepeat
    String hospitalInfo;//机构/部门/职务
    @Location(x=6)
    @ValidRepeat
    String jobTitleName;//职称
    @Location(x=7)
    @ValidRepeat
    String roleInfo;//归属业务模块角色
    @Location(x=8)
    @ValidRepeat
    Integer isFamous;//是否名医
    @Location(x=9)
    @ValidRepeat
    String expertise;//专长
    @Location(x=10)
    @ValidRepeat
    String brief;//简介

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
        return rs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHospitalInfo() {
        return hospitalInfo;
    }

    public void setHospitalInfo(String hospitalInfo) {
        this.hospitalInfo = hospitalInfo;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }

    public Integer getIsFamous() {
        return isFamous;
    }

    public void setIsFamous(Integer isFamous) {
        this.isFamous = isFamous;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
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

    public int getYearNow() {
        return yearNow;
    }

    public void setYearNow(int yearNow) {
        this.yearNow = yearNow;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }
}
