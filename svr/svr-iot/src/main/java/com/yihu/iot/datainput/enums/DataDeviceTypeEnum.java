package com.yihu.iot.datainput.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 设备类型枚举类，有些设备测量血压，有些设备测量血糖等
 */
public enum DataDeviceTypeEnum {

    SYSTOLIC("1","SYSTOLIC"),
    BLOOD_SUGAR("2","BLOOD_SUGAR");

    private String type;
    private String name;

    private DataDeviceTypeEnum(String type,String name){
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 根据传入的设备类型返回对应的测量名称
     * @param type
     * @return
     */
    public static String getNameByType(String type){
        String name = "";
        DataDeviceTypeEnum[] arrays = DataDeviceTypeEnum.values();
        for(int i = 0;i < arrays.length;i++){
            if(StringUtils.equals(type,arrays[i].getType())){
                name = arrays[i].getName();
                return name;
            }
        }
        return name;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}
