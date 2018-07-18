package com.yihu.jw.restmodel.specialist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangdan on 2018/7/6.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "筛查结果", description = "筛查结果")
public class SurveyScreenResultVo{


    @ApiModelProperty("id")
    private long id;
    @ApiModelProperty("唯一标识")
    private String code;
    //
    @ApiModelProperty("模板编码")
    private String templateCode;
    //模板标题
    @ApiModelProperty("模板标题")
    private  String templateTitle;
    //
    @ApiModelProperty("疾病类型(根据模板表的疾病类型定义)")
    private int disease;
    //
    @ApiModelProperty("全科医生code")
    private String doctor;
    @ApiModelProperty("家医电话号码")
    private String doctorMobile;
    //
    @ApiModelProperty("居民code")
    private String patientCode;
    //
    @ApiModelProperty("居民openid")
    private String openId;
    //
    @ApiModelProperty("居民名字")
    private String patientName;
    //筛查结果设置中相关code
    @ApiModelProperty("筛查结果唯一标识")
    private String screenResultCode;
    //筛查结果分值
    @ApiModelProperty("筛查结果分数")
    private Integer screenResultScore;
    //筛查结果
    @ApiModelProperty("筛查结果")
    private String screenResult;
    //是否高危预警（0不是  1是）
    @ApiModelProperty("是否高危预警（0不是  1是）")
    private Integer isDanger;
    //是否预约（0未预约 1已预约）
    @ApiModelProperty("是否预约（0未预约 1已预约）")
    private Integer isOrder;
    //是否跟踪（0未跟踪 1已跟踪）
    @ApiModelProperty("是否跟踪（0未跟踪 1已跟踪）")
    private Integer following;
    //是否进行健康教育(0没有  1有)
    @ApiModelProperty("是否进行健康教育(0没有  1有)")
    private Integer isEducate;
    //是否完成（0未完成 1已完成）
    @ApiModelProperty("是否完成（0未完成 1已完成）")
    private Integer over;
    //预约记录code
    @ApiModelProperty("预约记录code")
    private String reservationCode;
    //创建时间
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date czrq;
    //是否可以再次评估（0不可以 1可以）
    @ApiModelProperty("是否可以再次评估（0不可以 1可以）")
    private int isAgain;
    //再次筛查的上一份记录code
    @ApiModelProperty("再次筛查的上一份记录code")
    private String parentCode;
    //首份记录code
    @ApiModelProperty("首份记录code")
    private String originCode;
    //建议code,多个逗号隔开
    @ApiModelProperty("建议code,多个逗号隔开")
    private String adviceCode;
    //其他建议
    @ApiModelProperty("其他建议")
    private String otherAdvice;
    //来源（1医生发放  2居民自我评估）
    @ApiModelProperty("来源（1医生发放  2居民自我评估）")
    private int source;
    //更新时间
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("身份证号码")
    private String idcard;

    @ApiModelProperty("性别（1男2女3未知）")
    private String sex;

    @ApiModelProperty("年龄")
    private int age;

    @ApiModelProperty("医生类型：1专科医生，2全科医生，3健康管理师")
    private int level;

    @ApiModelProperty("筛查家庭医生姓名")
    private String doctorName;

    public SurveyScreenResultVo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public int getDisease() {
        return disease;
    }

    public void setDisease(int disease) {
        this.disease = disease;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctorMobile() {
        return doctorMobile;
    }

    public void setDoctorMobile(String doctorMobile) {
        this.doctorMobile = doctorMobile;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getScreenResultCode() {
        return screenResultCode;
    }

    public void setScreenResultCode(String screenResultCode) {
        this.screenResultCode = screenResultCode;
    }

    public Integer getScreenResultScore() {
        return screenResultScore;
    }

    public void setScreenResultScore(Integer screenResultScore) {
        this.screenResultScore = screenResultScore;
    }

    public String getScreenResult() {
        return screenResult;
    }

    public void setScreenResult(String screenResult) {
        this.screenResult = screenResult;
    }

    public Integer getIsDanger() {
        return isDanger;
    }

    public void setIsDanger(Integer isDanger) {
        this.isDanger = isDanger;
    }

    public Integer getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(Integer isOrder) {
        this.isOrder = isOrder;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Integer getIsEducate() {
        return isEducate;
    }

    public void setIsEducate(Integer isEducate) {
        this.isEducate = isEducate;
    }

    public Integer getOver() {
        return over;
    }

    public void setOver(Integer over) {
        this.over = over;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public Date getCzrq() {
        return czrq;
    }

    public void setCzrq(Date czrq) {
        this.czrq = czrq;
    }

    public int getIsAgain() {
        return isAgain;
    }

    public void setIsAgain(int isAgain) {
        this.isAgain = isAgain;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getAdviceCode() {
        return adviceCode;
    }

    public void setAdviceCode(String adviceCode) {
        this.adviceCode = adviceCode;
    }

    public String getOtherAdvice() {
        return otherAdvice;
    }

    public void setOtherAdvice(String otherAdvice) {
        this.otherAdvice = otherAdvice;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
