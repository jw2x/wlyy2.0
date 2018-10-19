package com.yihu.jw.entity.base.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 医院科室字典实体
 * 
 * @author Administrator on  2018年09月05日
 *
 */
@Entity
@Table(name = "dict_hospital_dept")
public class DictHospitalDeptDO extends IntegerIdentityEntity {

    /**
	 * 机构code，每个机构的科室不完全一样
	 */
	private String orgCode;

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;


	@Column(name = "org_code")
    public String getOrgCode() {
        return orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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