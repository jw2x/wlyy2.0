package com.yihu.jw.entity.specialist;/**
 * Created by nature of king on 2018/8/16.
 */

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wangzhinan
 * @create 2018-08-16 10:16
 * @desc 服务项目
 **/
@Entity
@Table(name = "wlyy_service_item")
public class SpecialistServiceItemDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId; // saasId

    @Column(name = "title")
    private String title; //项目名称

    @Column(name = "content")
    private String content;//项目内涵

    @Column(name = "exclude_content")
    private String excludeContent; // 除去内容

    @Column(name = "description")
    private String description;//说明

    @Column(name = "disease_item")
    private String diseaseItem; //疾病项目

    @Column(name = "reserve")
    private Integer reserve; //是否预约

    @Column(name = "type")
    private Integer type;//完成方式

    @Column(name = "evaluation")
    private Integer evaluation;//项目评价

    @Column(name = "item_type")
    private Integer itemType; //项目类型

    @Column(name = "hospital_grade")
    private Integer hospitalGrade; //医院等级

    @Column(name = "three_hospitals")
    private Integer threeHospitals; //三级医院收费标准

    @Column(name = "two_hospitals")
    private Integer twoHospitals; //二级医院收费标准

    @Column(name = "one_hospitals")
    private Integer oneHospitals;//一级医院以及一级以下的社区医院

    @Column(name = "unit")
    private Integer unit; //计价单位

    @Column(name = "add_item")
    private String addItem;//加收项目

    @Column(name = "imediate")
    private Integer imediate;//立即生效

    @Column(name = "status")
    private Integer status; //删除状态

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "exclude_content")
    public String getExcludeContent() {
        return excludeContent;
    }

    public void setExcludeContent(String excludeContent) {
        this.excludeContent = excludeContent;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "disease_item")
    public String getDiseaseItem() {
        return diseaseItem;
    }

    public void setDiseaseItem(String diseaseItem) {
        this.diseaseItem = diseaseItem;
    }

    @Column(name = "reserve")
    public Integer getReserve() {
        return reserve;
    }

    public void setReserve(Integer reserve) {
        this.reserve = reserve;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "evaluation")
    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    @Column(name = "item_type")
    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    @Column(name = "hospital_grade")
    public Integer getHospitalGrade() {
        return hospitalGrade;
    }

    public void setHospitalGrade(Integer hospitalGrade) {
        this.hospitalGrade = hospitalGrade;
    }

    @Column(name = "three_hospitals")
    public Integer getThreeHospitals() {
        return threeHospitals;
    }

    public void setThreeHospitals(Integer threeHospitals) {
        this.threeHospitals = threeHospitals;
    }

    @Column(name = "two_hospitals")
    public Integer getTwoHospitals() {
        return twoHospitals;
    }

    public void setTwoHospitals(Integer twoHospitals) {
        this.twoHospitals = twoHospitals;
    }

    @Column(name = "one_hospitals")
    public Integer getOneHospitals() {
        return oneHospitals;
    }

    public void setOneHospitals(Integer oneHospitals) {
        this.oneHospitals = oneHospitals;
    }

    @Column(name = "unit")
    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @Column(name = "add_item")
    public String getAddItem() {
        return addItem;
    }

    public void setAddItem(String addItem) {
        this.addItem = addItem;
    }

    @Column(name = "imediate")
    public Integer getImediate() {
        return imediate;
    }

    public void setImediate(Integer imediate) {
        this.imediate = imediate;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
