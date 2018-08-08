/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.yihu.jw;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 统一定义id的entity基类.
 * 主键生成策略是UUID
 * @author calvin
 */
// JPA 基类的标识
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class IdEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	protected String id;  // 非业务主键

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
