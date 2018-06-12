package com.yihu.jw.restmodel.specialist;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Trick on 2018/6/8.
 */
public class PatientLabelVO {

    @ApiModelProperty("居民code")
    private String code;
    @ApiModelProperty("居民")
    private String name ;
    @ApiModelProperty("年龄")
    private String age;
    @ApiModelProperty("标签名称")
    private String labelName;
    @ApiModelProperty("标签类型")
    private String labelType;
    @ApiModelProperty("标签code")
    private String label;
    @ApiModelProperty("居民头像")
    private String photo;
    @ApiModelProperty("居民健康情况")
    private String health;
    @ApiModelProperty("居民健康情况code")
    private String healthcode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
