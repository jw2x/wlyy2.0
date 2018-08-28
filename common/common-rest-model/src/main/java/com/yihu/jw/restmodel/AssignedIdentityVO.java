package com.yihu.jw.restmodel;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Basic VO - 自定义主键类型基类
 * Created by progr1mmer on 2018/8/13.
 */
public abstract class AssignedIdentityVO implements Serializable {

    @ApiModelProperty(value = "id", example = "iREOlyuyKfRBIGOHbBGJ" )
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
