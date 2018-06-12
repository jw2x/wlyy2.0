package com.yihu.jw.datainput;


import java.util.List;
/**
 * 微信运动数据实体类
 * @author lith on 2018/01/17.
 */
public class WeRunDataDO {

    private String usercode; //用户code，唯一识别码
    private String username; //用户名
    private List<StepInfoDO> stepInfoList;//用户过去三十天的微信运动步数

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

    public List<StepInfoDO> getStepInfoList() {
        return stepInfoList;
    }

    public void setStepInfoList(List<StepInfoDO> stepInfoList) {
        this.stepInfoList = stepInfoList;
    }
}
