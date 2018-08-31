package com.yihu.jw.restmodel.specialist;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by humingfen on 2018/8/30.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "居民签约疾病信息", description = "居民签约疾病信息")
public class PatientDisseaseInfoVO {

    @ApiModelProperty("疾病")
    private String disease;
    @ApiModelProperty("疾病名称")
    private String diseaseName;
    @ApiModelProperty("居民code")
    private String patient;
    @ApiModelProperty("居民姓名")
    private String patientName;
    @ApiModelProperty("年龄")
    private String age;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("身份证")
    private String idcard;
    @ApiModelProperty("生日")
    private String  birthday;

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
