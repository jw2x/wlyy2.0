package com.yihu.jw.restmodel.iot.datainput;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class WeRunDataVO{

    @ApiModelProperty(value = "用户code，唯一识别码",hidden = true)
    private String usercode;
    @ApiModelProperty(value = "用户名",hidden = true)
    private String username;
    @ApiModelProperty(value = "用户过去三十天的微信运动步数")
    private List<StepInfoVO> stepInfoList;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<StepInfoVO> getStepInfoList() {
        return stepInfoList;
    }

    public void setStepInfoList(List<StepInfoVO> stepInfoList) {
        this.stepInfoList = stepInfoList;
    }
}
