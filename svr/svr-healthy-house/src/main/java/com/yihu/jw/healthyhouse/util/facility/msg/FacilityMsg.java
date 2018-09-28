package com.yihu.jw.healthyhouse.util.facility.msg;


import com.yihu.jw.util.excel.ExcelUtil;
import com.yihu.jw.util.excel.Validation;
import com.yihu.jw.util.excel.annotation.Location;
import com.yihu.jw.util.excel.annotation.Row;
import com.yihu.jw.util.excel.annotation.ValidRepeat;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 *  设施列表-excel实体类
 *
 * @author HZY
 * @vsrsion 1.0
 * Created at 2018/9/25.
 */
@Row
public class FacilityMsg extends ExcelUtil implements Validation {

    @Location(x=1)
    @ValidRepeat
    String code;
    @Location(x=2)
    String category;
    @Location(x=3)
    String userName;
    @Location(x=4)
    String userTelePhone;
    @Location(x=5)
    String province;
    @Location(x=6)
    String city;
    @Location(x=7)
    String county;
    @Location(x=8)
    String street;
    @Location(x=9)
    String status;
    @Location(x=10)
    String orgName;
    @Location(x=11)
    String serviceDate;
    @Location(x=12)
    String serviceStartTime;
    @Location(x=13)
    String serviceEndTime;
    @Location(x=14)
    String longitude; //经度
    @Location(x=15)
    String latitude; //纬度
    @Location(x=16)
    String name; //设施名称
    int hashCode;


    @Override
    public int validate(Map<String, Set> repeatMap) {
        int rs = 1;
//        if(!RegUtil.regCode(innerCode)){
//            rs = 0;
//            addErrorMsg("innerCode", RegUtil.codeMsg);
//        } else if(!repeatMap.get("innerCode").add(innerCode)) {
//            rs = 0;
//            addErrorMsg("innerCode", "内部代码重复！");
//        }
//
//        if(!RegUtil.regCode(code)){
//            rs = 0;
//            addErrorMsg("code", RegUtil.codeMsg);
//        }
//
//        if(!RegUtil.regLen(name)){
//            rs = 0;
//            addErrorMsg("name", RegUtil.lenMsg);
//        }else if(name.indexOf("'")!=-1){
//            rs = 0;
//            addErrorMsg("name", "不允许输入'");
//        }
//
//        if(!RegUtil.regLength2(0, 200, definition)){
//            rs = 0;
//            addErrorMsg("definition", "不得超过200个字符");
//        }
//
//        if(!RegUtil.regLength2(0, 10, type)){
//            rs = 0;
//            addErrorMsg("type", "不得超过10个字符");
//        }
//
//        if(!RegUtil.regLength2(0, 50, format)){
//            rs = 0;
//            addErrorMsg("format", "不得超过50个字符");
//        }
//
//        if(!RegUtil.regLength2(1, 64, columnName)){
//            rs = 0;
//            addErrorMsg("columnName", "请输入1~64个字符");
//        }else if(!repeatMap.get("columnName").add(columnName)){
//            rs = 0;
//            addErrorMsg("columnName", "列名重复");
//        }
//
//        if(!RegUtil.regLength2(0, 50, columnType)){
//            rs = 0;
//            addErrorMsg("columnType", "不得超过50个字符");
//        }
//
//        if(!RegUtil.regLength2(0, 15, columnLength)){
//            rs = 0;
//            addErrorMsg("columnLength", "不得超过15个字符");
//        }
//
//        if(StringUtils.isEmpty(primaryKey)){
//            primaryKey = "0";
//        }
//
//        if(!primaryKey.equals("0") && !primaryKey.equals("1")){
//            rs = 0;
//            addErrorMsg("primaryKey", "只允许输入0或1， 不填默认为0");
//        }
//
//        if(StringUtils.isEmpty(nullable)){
//            //该数据不填，默认为可以为空  nullable = "1"
//            nullable = "1";
//        }
//
//        if(!nullable.equals("0") && !nullable.equals("1")){
//            rs = 0;
//            addErrorMsg("nullable", "只允许输入0或1， 不填默认为1");
//        }else if(primaryKey.equals("1") && nullable.equals(primaryKey)){
//            addErrorMsg("nullable", "主键不允许为空");
//        }

        return rs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTelePhone() {
        return userTelePhone;
    }

    public void setUserTelePhone(String userTelePhone) {
        this.userTelePhone = userTelePhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(String serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public String getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(String serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
