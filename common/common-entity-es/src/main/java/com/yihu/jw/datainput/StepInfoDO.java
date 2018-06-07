package com.yihu.jw.datainput;

/**
 * 微信运动具体时间步数实体类
 * @author lith on 2018/01/17.
 */
public class StepInfoDO {

    private String timestamp; //时间戳
    private Integer step; //运动步数

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
