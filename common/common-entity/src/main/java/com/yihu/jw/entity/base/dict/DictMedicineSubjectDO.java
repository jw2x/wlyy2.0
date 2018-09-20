package com.yihu.jw.entity.base.dict;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 药品科目类别字典实体
 * 
 * @author litaohong on  2018年09月11日
 *
 */
@Entity
@Table(name = "dict_medicine_subject")
public class DictMedicineSubjectDO extends IntegerIdentityEntity {

    /**
	 * 类别代码
	 */
	private String code;

    /**
	 * 类别名称
	 */
	private String name;

    /**
	 * 父类code
	 */
	private String parentCode;


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

	@Column(name = "parent_code")
    public String getParentCode() {
        return parentCode;
    }
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }



}