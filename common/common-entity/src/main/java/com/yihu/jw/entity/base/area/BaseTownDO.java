package com.yihu.jw.entity.base.area;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 区县字典实体
 * 
 * @author litaohong on  2018年08月31日
 *
 */
@Entity
@Table(name = "base_town")
public class BaseTownDO extends IntegerIdentityEntity {

    /**
	* 省编码
	*/
    private String province;

    /**
	* 城市编码
	*/
    private String city;

    /**
	* 区县编码
	*/
    private String code;

    /**
	* 区县名称
	*/
    private String name;

    /**
	* 创建时间
	*/
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