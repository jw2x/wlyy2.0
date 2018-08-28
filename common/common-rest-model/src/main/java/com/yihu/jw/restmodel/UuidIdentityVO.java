/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.yihu.jw.restmodel;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Basic VO - UUID类型的主键基类
 * Created by progr1mmer on 2018/8/13.
 */
public abstract class UuidIdentityVO implements Serializable {

	@ApiModelProperty(value = "id", example = "402803ee656498890165649ad2da1112" )
	protected String id;  // 非业务主键

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
