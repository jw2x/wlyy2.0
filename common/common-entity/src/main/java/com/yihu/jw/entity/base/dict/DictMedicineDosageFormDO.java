package com.yihu.jw.entity.base.dict;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 药品剂型字典实体
 * 
 * @author litaohong on  2018年09月11日
 *
 */
@Entity
@Table(name = "dict_medicine_dosage_form")
public class DictMedicineDosageFormDO extends IntegerIdentityEntity {

    /**
	 * 剂型代码
	 */
	private String code;

    /**
	 * 剂型名称（颗粒型，注射液，胶囊等）
	 */
	private String name;


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