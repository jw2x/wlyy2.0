package com.yihu.wlyy.figure.label.enums;

/**
 * @author litaohong on 2018/4/11
 * @project patient-co-management
 *
 * 增量字段类型枚举 1时间 （yyyy-mm-dd  HH:MM:ss）2数字
 */
public enum JobSqlFieldTypeEnum {
    TIME(1,"时间"),
    NUM(2,"数字");

    private int value;
    private String desc;


    private JobSqlFieldTypeEnum(int value,String desc){
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }
}
