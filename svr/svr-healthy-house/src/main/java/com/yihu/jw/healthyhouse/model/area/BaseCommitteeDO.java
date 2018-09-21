package com.yihu.jw.healthyhouse.model.area;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* 居委会实体
*
* @author Administrator on  2018年09月05日
*
*/
@Entity
@Table(name = "base_committee")
public class BaseCommitteeDO extends UuidIdentityEntityWithOperator {

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
	private String street;

    /**
	 * 居委会标识
	 */
	private String code;

    /**
	 * 居委会名称
	 */
	private String name;


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

	@Column(name = "street")
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
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



}