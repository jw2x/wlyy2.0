package com.yihu.jw.entity.base.dict;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 药品字典实体
 * 
 * @author Administrator on  2018年09月07日
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
	 * 药品名称
	 */
	private String name;

    /**
	 * 药品规格
	 */
	private String physicSpec;

    /**
	 * 药品科目  科目类别字典中定义
	 */
	private String subjectClass;

    /**
	 * 剂量单位 计量单位字典中定义
	 */
	private String doseUnit;

    /**
	 * 数量单位  计量单位字典中定义
	 */
	private String quantityUnit;

    /**
	 * 包装单位   计量单位字典中定义
	 */
	private String packUnit;

    /**
	 * 最小剂量
	 */
	private double minDose;

    /**
	 * 
	 */
	private double packSpec;

    /**
	 * 零售价
	 */
	private double retailPrice;

    /**
	 * 
	 */
	private String physicForm;

    /**
	 * 毒理分类  药品毒理分类字典中定义
	 */
	private String toxicologyType;

    /**
	 * 基本药物标志  0：否；1：是
	 */
	private String basicFlag;

    /**
	 * 有效标志 0：无效；1：有效
	 */
	private String validFlag;

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

	@Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "physic_spec")
    public String getPhysicSpec() {
        return physicSpec;
    }
    public void setPhysicSpec(String physicSpec) {
        this.physicSpec = physicSpec;
    }

	@Column(name = "subject_class")
    public String getSubjectClass() {
        return subjectClass;
    }
    public void setSubjectClass(String subjectClass) {
        this.subjectClass = subjectClass;
    }

	@Column(name = "dose_unit")
    public String getDoseUnit() {
        return doseUnit;
    }
    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit;
    }

	@Column(name = "quantity_unit")
    public String getQuantityUnit() {
        return quantityUnit;
    }
    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

	@Column(name = "pack_unit")
    public String getPackUnit() {
        return packUnit;
    }
    public void setPackUnit(String packUnit) {
        this.packUnit = packUnit;
    }

	@Column(name = "min_dose")
    public double getMinDose() {
        return minDose;
    }
    public void setMinDose(double minDose) {
        this.minDose = minDose;
    }

	@Column(name = "pack_spec")
    public double getPackSpec() {
        return packSpec;
    }
    public void setPackSpec(double packSpec) {
        this.packSpec = packSpec;
    }

	@Column(name = "retail_price")
    public double getRetailPrice() {
        return retailPrice;
    }
    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

	@Column(name = "physic_form")
    public String getPhysicForm() {
        return physicForm;
    }
    public void setPhysicForm(String physicForm) {
        this.physicForm = physicForm;
    }

	@Column(name = "toxicology_type")
    public String getToxicologyType() {
        return toxicologyType;
    }
    public void setToxicologyType(String toxicologyType) {
        this.toxicologyType = toxicologyType;
    }

	@Column(name = "basic_flag")
    public String getBasicFlag() {
        return basicFlag;
    }
    public void setBasicFlag(String basicFlag) {
        this.basicFlag = basicFlag;
    }

	@Column(name = "valid_flag")
    public String getValidFlag() {
        return validFlag;
    }
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
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