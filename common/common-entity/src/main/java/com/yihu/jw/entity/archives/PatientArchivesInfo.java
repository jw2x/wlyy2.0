package com.yihu.jw.entity.archives;

import com.yihu.jw.IdEntityWithOperation;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Trick on 2018/2/7.
 */
public class PatientArchivesInfo extends IdEntityWithOperation implements Serializable {
    private String code; //业务主键
    private String saasId; //saasid
    private String archivesCode; //档案code
    private String level1;//1.历史情况，2.既往史，3.家族史，4.生活环境
    private String level2; //子类别
    private String key; //字典值，或判断值（有，无）
    private String value; //字典名称
    private Date date;//关联时间
    private String remark; //备注/其他/描述/详情

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
