package com.yihu.jw.datainput;


import io.searchbox.annotations.JestId;

import java.util.List;

/**
 * 体征数据实体类，新的标准的数据格式
 * @author lith on 2018/01/17.
 */
public class DataBodySignsDO {

    @JestId
    private String id;
    private String access_token; //访问token
    private String data_source; //数据来源
    private String sn;          //设备序列码
    private String ext_code;    //设备A，B键，取值为0,1，代表绑定不同的用户，
    private String device_name; //设备名称
    private String device_model;//设备型号
    private List<Data> data;    //设备所测量的数据内容
    private String idCard;      //设备绑定的用户身份证号
    private String username;    //用户名
    private String usercode;    //用户在系统中的code，唯一识别

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getExt_code() {
        return ext_code;
    }

    public void setExt_code(String ext_code) {
        this.ext_code = ext_code;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
}
