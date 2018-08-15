package com.yihu.jw.entity.health.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wang zhinan on 2018/4/26.
 */
@Entity
@Table(name = "wlyy_health_bank_activity")
public class ActivityDO extends UuidIdentityEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//saasId

    @Column(name = "organizer")
    private String organizer; // 主办方&&发布机构

    @Column(name = "title")
    private String title;//活动标题

    @Column(name = "description")
    private String description;//活动说明

    @Column(name = "location")
    private String location;//活动地点

    @Column(name = "img")
    private String img;//活动图片

    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date startTime;//开始时间

    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date endTime; //结束时间

    @Column(name = "status")
    private Integer status;//状态（1代表有效，0代表无效,-1代表过期）

    @Column(name = "is_flag")
    private Integer isFlag;//标识是否有问卷（1：有，-1：没有）

    @Column(name = "area")
    private String area;//区域

    @Column(name = "area_type")
    private Integer areaType; //区域类型

    @Column(name = "remark")
    private String remark;//活动备注

    @Column(name = "type")
    private String type;//活动类型

    @Transient
    private String patientId;//居民id

    @Transient
    private String openId;//微信编码

    @Transient
    private String unionId;

    @Transient
    private String patientIdcard;//身份证号

    @Transient
    private Long total;//参加总数

    @Transient
    private Integer activityRanking;//活动中的排名

    @Transient
    private List<TaskPatientDetailDO> taskPatientDetailDOS;//参与活动详情

    @Transient
    private List<TaskDO> taskDOS;//活动任务列表

    @Transient
    private Long sum;//活动积分总数

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(Integer isFlag) {
        this.isFlag = isFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    @Transient
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Transient
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Transient
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public List<TaskPatientDetailDO> getTaskPatientDetailDOS() {
        return taskPatientDetailDOS;
    }

    public void setTaskPatientDetailDOS(List<TaskPatientDetailDO> taskPatientDetailDOS) {
        this.taskPatientDetailDOS = taskPatientDetailDOS;
    }

    @Transient
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Transient
    public String getPatientIdcard() {
        return patientIdcard;
    }

    public void setPatientIdcard(String patientIdcard) {
        this.patientIdcard = patientIdcard;
    }

    @Transient
    public Integer getActivityRanking() {
        return activityRanking;
    }

    public void setActivityRanking(Integer activityRanking) {
        this.activityRanking = activityRanking;
    }

    @Transient
    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    @Transient
    public List<TaskDO> getTaskDOS() {
        return taskDOS;
    }

    public void setTaskDOS(List<TaskDO> taskDOS) {
        this.taskDOS = taskDOS;
    }
}
