package com.yihu.jw.restmodel.common.base;

/**
 * Created by chenweida on 2018/5/8 0008.
 */
public enum BaseEnvelopStatus {
    success("请求成功", 200),
    ;

    BaseEnvelopStatus(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    private String name;
    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
