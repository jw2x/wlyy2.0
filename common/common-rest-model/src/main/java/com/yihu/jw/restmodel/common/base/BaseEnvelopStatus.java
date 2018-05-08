package com.yihu.jw.restmodel.common.base;

/**
 * Created by chenweida on 2018/5/8 0008.
 */
public enum BaseEnvelopStatus {
    success("请求成功", 200),
    status_10100("账号不存在", 10100),
    status_10101("密码错误", 10101),
    status_10102("用户未登录", 10102),
    status_10103("登陆超时", 10103),
    status_10104("账号被挤", 10104),;

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
