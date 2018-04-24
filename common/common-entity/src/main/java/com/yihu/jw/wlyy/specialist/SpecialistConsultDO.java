package com.yihu.jw.wlyy.specialist;

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

    private String consult;//关联咨询code
    private String doctor;//专科医生
    private String doctorName;//专科医生姓名
    private String type;//类型：1.家庭医生咨询，2.居民咨询
    private String member;//咨询对象
    private String memberName;//咨询对象
    private String status;// 1.咨询完成，0.咨询进行中
    private String reply;// 1.专科医生已经参与；0.~未参与
    private String content;//咨询内容

    @Column(name = "consult")
    public String getConsult() {
        return consult;
    }

    public void setConsult(String consult) {
        this.consult = consult;
    }

    @Column(name = "doctor")
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Column(name = "doctor_name")
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "member")
    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Column(name = "member_name")
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "reply")
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
