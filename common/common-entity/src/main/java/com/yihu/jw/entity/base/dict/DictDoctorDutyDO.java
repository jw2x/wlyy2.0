package com.yihu.jw.entity.base.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 医生职务字典实体
 * 
 * @author litaohong on  2018年10月19日
 *
 */
@Entity
@Table(name = "dict_doctor_duty")
public class DictDoctorDutyDO extends IntegerIdentityEntity {

    /**
	 * 职务标识
	 */
	private String code;

    /**
	 * 职务名称（院长/科室主任等等）
	 */
	private String name;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;


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