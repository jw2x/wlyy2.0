package com.yihu.jw.restmodel;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Basic Entity - Integer类型的主键基类
 * Created by progr1mmer on 2018/8/13.
 */
public abstract class IntegerIdentityVO implements Serializable {

    @ApiModelProperty(value = "id", example = "1" )
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
