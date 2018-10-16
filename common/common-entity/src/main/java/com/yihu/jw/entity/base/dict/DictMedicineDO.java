package com.yihu.jw.entity.base.dict;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 药品字典实体
 * 
 * @author litaohong on  2018年09月11日
 *
 */
@Entity
@Table(name = "dict_medicine")
public class DictMedicineDO extends IntegerIdentityEntity {

    /**
	 * 药品代码
	 */
	private String code;

    /**
     * saasId
     */
    private String saasId;

    /**
	 * 药品中文名
	 */
	private String name;

    /**
	 * 药品所属科目代码
	 */
	private String subjectCode;

    /**
	 * 药品剂型
	 */
	private String dosageForm;

    /**
	 * 药品规格
	 */
	private String specification;

    /**
	 * 包装规格
	 */
	private String packingSpecification;

    /**
	 * 适应症
	 */
	private String indication;

    /**
	 * 拼音首码
	 */
	private String spellCode;

    /**
	 * 五笔首码
	 */
	private String wbzxCode;

    /**
	 * 排序号
	 */
	private Integer sequence;

    /**
	 * 2表示需要冷藏，其他表示不需要冷藏
	 */
	private String storageConditions;


	@Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "subject_code")
    public String getSubjectCode() {
        return subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

	@Column(name = "dosage_form")
    public String getDosageForm() {
        return dosageForm;
    }
    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

	@Column(name = "specification")
    public String getSpecification() {
        return specification;
    }
    public void setSpecification(String specification) {
        this.specification = specification;
    }

	@Column(name = "packing_specification")
    public String getPackingSpecification() {
        return packingSpecification;
    }
    public void setPackingSpecification(String packingSpecification) {
        this.packingSpecification = packingSpecification;
    }

	@Column(name = "indication")
    public String getIndication() {
        return indication;
    }
    public void setIndication(String indication) {
        this.indication = indication;
    }

	@Column(name = "spell_code")
    public String getSpellCode() {
        return spellCode;
    }
    public void setSpellCode(String spellCode) {
        this.spellCode = spellCode;
    }

	@Column(name = "wbzx_code")
    public String getWbzxCode() {
        return wbzxCode;
    }
    public void setWbzxCode(String wbzxCode) {
        this.wbzxCode = wbzxCode;
    }

	@Column(name = "sequence")
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

	@Column(name = "storage_conditions")
    public String getStorageConditions() {
        return storageConditions;
    }
    public void setStorageConditions(String storageConditions) {
        this.storageConditions = storageConditions;
    }



}