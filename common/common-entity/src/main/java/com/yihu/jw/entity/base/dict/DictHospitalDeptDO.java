package com.yihu.jw.entity.base.dict;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 医院科室字典实体
 * 
 * @author litaohong on  2018年08月31日
 *
 */
@Entity
@Table(name = "dict_hospital_dept")
public class DictHospitalDeptDO extends IntegerIdentityEntity {

    /**
	* saas配置id，null标识公共字典
	*/
    private String saasId;

    /**
	* 科室标识
	*/
    private String code;

    /**
	* 科室名称
	*/
    private String name;

    /**
	* 创建时间
	*/
    private Date createTime;


	@Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }
    public void setSaasId(String saasId) {
        this.saasId = saasId;
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