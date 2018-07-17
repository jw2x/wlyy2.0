package com.yihu.jw.restmodel.specialist;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Trick on 2018/5/30.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "居民信息", description = "居民信息")
public class PatientRelationVO {

    @ApiModelProperty("居民code")
    private String patient;
    @ApiModelProperty("居民姓名")
    private String patientName;
    @ApiModelProperty("年龄")
    private String age;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("头像")
    private String photo;
    @ApiModelProperty("出院时间")
    private String createTime;
    @ApiModelProperty("居民健康情况")
    private String health;
    @ApiModelProperty("居民健康情况code")
    private String healthcode;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getHealthcode() {
        return healthcode;
    }

    public void setHealthcode(String healthcode) {
        this.healthcode = healthcode;
    }
}
