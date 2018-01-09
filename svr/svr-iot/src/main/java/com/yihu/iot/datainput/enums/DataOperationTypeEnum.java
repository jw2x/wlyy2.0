package com.yihu.iot.datainput.enums;


public enum DataOperationTypeEnum {

    convert("数据标准转换"),
    bindUser("设备注册绑定"),
    upload0("不含居民信息的数据上传"),
    upload1("含居民信息的数据上传"),
    search("数据查询");

    private String name;

    private DataOperationTypeEnum(String name){
        this.name = name;
    }


    public String getName() {
        return name.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name.toString();
    }

}
