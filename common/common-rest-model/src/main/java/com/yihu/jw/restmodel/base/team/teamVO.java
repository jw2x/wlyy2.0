package com.yihu.jw.restmodel.base.team;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Trick on 2018/8/31.
 */
public class TeamVO {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "saas化")
    private String saasid;//saas化',
    @ApiModelProperty(value = "团队名称")
    private String name;//团队名称',
    @ApiModelProperty(value = "领导医生标识")
    private String leaderId;//领导医生标识',
    @ApiModelProperty(value = "团队人数")
    private String teamNum;//团队人数',
    @ApiModelProperty(value = "团队二维码")
    private String qrcode;//团队二维码',
    @ApiModelProperty(value = "作废标识，1正常，0作废',")
    private String del;//作废标识，1正常，0作废',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaasid() {
        return saasid;
    }

    public void setSaasid(String saasid) {
        this.saasid = saasid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(String teamNum) {
        this.teamNum = teamNum;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
