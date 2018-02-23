package com.yihu.jw.entity.archives;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Trick on 2018/2/7.
 */
@Entity
@Table(name = "wlyy_patient_archives_info")
public class PatientArchivesInfo extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId; //saasid
    @Column(name = "archives_code")
    private String archivesCode; //档案code
    @Column(name = "level1")
    private String level1;//1.历史情况，2.既往史，3.家族史，4.生活环境
    @Column(name = "level2")
    private String level2; //子类别
    @Column(name = "key")
    private String key; //字典值，或判断值（有，无）
    @Column(name = "value")
    private String value; //字典名称
    @Column(name = "date")
    private Date date;//关联时间
    @Column(name = "remark")
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
