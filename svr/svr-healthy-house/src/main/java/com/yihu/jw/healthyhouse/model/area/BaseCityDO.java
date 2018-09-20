package com.yihu.jw.healthyhouse.model.area;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 城市字典实体
 * 
 * @author Administrator on  2018年09月05日
 *
 */
@Entity
@Table(name = "base_city")
public class BaseCityDO extends IntegerIdentityEntity {

    /**
	 * 省编码
	 */
	private String province;

    /**
	 * 城市编码
	 */
	private String code;

    /**
	 * 城市名称
	 */
	private String name;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;


	@Column(name = "province")
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

	@Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}