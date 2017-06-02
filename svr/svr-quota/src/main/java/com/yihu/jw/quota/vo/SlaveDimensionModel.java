package com.yihu.jw.quota.vo;

/**
 * Created by chenweida on 2017/6/1.
 */
public class SlaveDimensionModel {
    private String slaveKey1;//从维度  1级维度
    private String slaveKey1Name;
    private String slaveKey2;//从维度  2级维度
    private String slaveKey2Name;
    private String slaveKey3;//从维度  3级维度
    private String slaveKey3Name;
    private String slaveKey4;//从维度  4级维度
    private String slaveKey4Name;

    public SlaveDimensionModel() {
    }

    public SlaveDimensionModel(String slaveKey1, String slaveKey2, String slaveKey3, String slaveKey4) {
        this.slaveKey1 = slaveKey1;
        this.slaveKey2 = slaveKey2;
        this.slaveKey3 = slaveKey3;
        this.slaveKey4 = slaveKey4;
    }

    public String getSlaveKey1() {
        return slaveKey1;
    }

    public void setSlaveKey1(String slaveKey1) {
        this.slaveKey1 = slaveKey1;
    }

    public String getSlaveKey1Name() {
        return slaveKey1Name;
    }

    public void setSlaveKey1Name(String slaveKey1Name) {
        this.slaveKey1Name = slaveKey1Name;
    }

    public String getSlaveKey2() {
        return slaveKey2;
    }

    public void setSlaveKey2(String slaveKey2) {
        this.slaveKey2 = slaveKey2;
    }

    public String getSlaveKey2Name() {
        return slaveKey2Name;
    }

    public void setSlaveKey2Name(String slaveKey2Name) {
        this.slaveKey2Name = slaveKey2Name;
    }

    public String getSlaveKey3() {
        return slaveKey3;
    }

    public void setSlaveKey3(String slaveKey3) {
        this.slaveKey3 = slaveKey3;
    }

    public String getSlaveKey3Name() {
        return slaveKey3Name;
    }

    public void setSlaveKey3Name(String slaveKey3Name) {
        this.slaveKey3Name = slaveKey3Name;
    }

    public String getSlaveKey4() {
        return slaveKey4;
    }

    public void setSlaveKey4(String slaveKey4) {
        this.slaveKey4 = slaveKey4;
    }

    public String getSlaveKey4Name() {
        return slaveKey4Name;
    }

    public void setSlaveKey4Name(String slaveKey4Name) {
        this.slaveKey4Name = slaveKey4Name;
    }
}
