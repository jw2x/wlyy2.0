package com.yihu.jw.entity.specialist;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Trick on 2018/4/24.
 */
@Entity
@Table(name = "wlyy_specialist_consult")
public class SpecialistConsultDO extends IdEntityWithOperation implements Serializable {
    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "consult")
    private String consult;//关联咨询code
    @Column(name = "doctor")
    private String doctor;//专科医生
    @Column(name = "doctor_name")
    private String doctorName;//专科医生姓名
    @Column(name = "type")
    private String type;//类型：1.家庭医生咨询，2.居民咨询
    @Column(name = "member")
    private String member;//咨询对象
    @Column(name = "member_name")
    private String memberName;//咨询对象
    @Column(name = "status")
    private String status;// 1.咨询完成，0.咨询进行中
    @Column(name = "reply")
    private String reply;// 1.专科医生已经参与；0.~未参与
    @Column(name = "content")
    private String content;//咨询内容


    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }


    public String getConsult() {
        return consult;
    }

    public void setConsult(String consult) {
        this.consult = consult;
    }


    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }


    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }


    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
