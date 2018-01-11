package com.yihu.iot.datainput.enums;

import java.util.HashSet;
import java.util.Set;

public enum DataTypeEnum {
    body_sign_params("体征数据"),
    body_sports_params("运动数据");

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private DataTypeEnum(String desc){
        this.desc = desc;
    }

    public static Set<String> getNames(){
        Set<String> set = new HashSet<>();
        for(DataTypeEnum dataTypeEnum:DataTypeEnum.values()){
            set.add(dataTypeEnum.name().toString());
        }
        return set;
    }
}
