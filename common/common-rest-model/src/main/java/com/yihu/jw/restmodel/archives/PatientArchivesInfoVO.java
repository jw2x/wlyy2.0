package com.yihu.jw.restmodel.archives;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Trick on 2018/2/8.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "居民档案详细信息", description = "居民档案详细信息")
public class PatientArchivesInfoVO {

    @ApiModelProperty("saasid")
    private String saasId; //saasid
    @ApiModelProperty("档案code")
    private String archivesCode; //档案code
    @ApiModelProperty("1.历史情况，2.既往史，3.家族史，4.生活环境")
    private String level1;//1.历史情况，2.既往史，3.家族史，4.生活环境
    @ApiModelProperty("子类别")
    private String level2; //子类别
    @ApiModelProperty("字典值，或判断值（有，无）")
    private String key; //字典值，或判断值（有，无）
    @ApiModelProperty("字典名称")
    private String value; //字典名称
    @ApiModelProperty("关联时间")
    private Date date;//关联时间
    @ApiModelProperty("备注/其他/描述/详情")
    private String remark; //备注/其他/描述/详情


    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getArchivesCode() {
        return archivesCode;
    }

    public void setArchivesCode(String archivesCode) {
        this.archivesCode = archivesCode;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
