package com.yihu.jw.entity.base.area;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 街道字典实体
 * 
 * @author Administrator on  2018年09月05日
 *
 */
@Entity
@Table(name = "base_street")
public class BaseStreetDO extends IntegerIdentityEntity {

    /**
	 * 省标识
	 */
	private String province;

    /**
	 * 市标识
	 */
	private String city;

    /**
	 * 区县标识
	 */
	private String town;

    /**
	 * 街道标识
	 */
	private String code;

    /**
	 * 街道名称
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

	@Column(name = "city")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

	@Column(name = "town")
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
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