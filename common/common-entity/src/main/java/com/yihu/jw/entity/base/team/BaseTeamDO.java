package com.yihu.jw.entity.base.team;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 团队信息实体
 *
 * @author litaohong on  2018年08月31日
 */
@Entity
@Table(name = "base_team")
public class BaseTeamDO extends UuidIdentityEntityWithOperator {

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 团队名称
     */
    private String name;

    /**
     * 领导医生标识
     */
    private String leaderId;

    /**
     * 团队人数
     */
    private String teamNum;

    /**
     * 团队二维码
     */
    private String qrcode;

    /**
     * 作废标识，1正常，0作废
     */
    private String del;

    public String getOrgCode() {
        return orgCode;
    }

    @Column(name = "org_code")
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    @Column(name = "org_name")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "leader_id")
    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    @Column(name = "team_num")
    public String getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(String teamNum) {
        this.teamNum = teamNum;
    }

    @Column(name = "qrcode")
    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    @Column(name = "del")
    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }


}
