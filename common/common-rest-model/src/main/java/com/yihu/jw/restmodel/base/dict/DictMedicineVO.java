package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 药品字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  2018年09月07日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictMedicineVO", description = "药品字典")
public class DictMedicineVO extends IntegerIdentityVO{

    /**
     * 药品代码
     */
    @ApiModelProperty(value = "分发药品的机构名称", example = "")
    private String orgName;

    /**
	 * 药品代码
	 */
	@ApiModelProperty(value = "药品代码", example = "")
    private String code;

    /**
	 * 药品名称
	 */
	@ApiModelProperty(value = "药品名称", example = "")
    private String name;

    /**
	 * 药品规格
	 */
	@ApiModelProperty(value = "药品规格", example = "")
    private String physicSpec;

    /**
	 * 药品科目  科目类别字典中定义
	 */
	@ApiModelProperty(value = "药品科目  科目类别字典中定义", example = "")
    private String subjectClass;

    /**
	 * 剂量单位 计量单位字典中定义
	 */
	@ApiModelProperty(value = "剂量单位 计量单位字典中定义", example = "")
    private String doseUnit;

    /**
	 * 数量单位  计量单位字典中定义
	 */
	@ApiModelProperty(value = "数量单位  计量单位字典中定义", example = "")
    private String quantityUnit;

    /**
	 * 包装单位   计量单位字典中定义
	 */
	@ApiModelProperty(value = "包装单位   计量单位字典中定义", example = "")
    private String packUnit;

    /**
	 * 最小剂量
	 */
	@ApiModelProperty(value = "最小剂量", example = "")
    private double minDose;

    /**
	 * 
	 */
	@ApiModelProperty(value = "", example = "")
    private double packSpec;

    /**
	 * 零售价
	 */
	@ApiModelProperty(value = "零售价", example = "")
    private double retailPrice;

    /**
	 * 
	 */
	@ApiModelProperty(value = "", example = "")
    private String physicForm;

    /**
	 * 毒理分类  药品毒理分类字典中定义
	 */
	@ApiModelProperty(value = "毒理分类  药品毒理分类字典中定义", example = "")
    private String toxicologyType;

    /**
	 * 基本药物标志  0：否；1：是
	 */
	@ApiModelProperty(value = "基本药物标志  0：否；1：是", example = "1")
    private String basicFlag;

    /**
	 * 有效标志 0：无效；1：有效
	 */
	@ApiModelProperty(value = "有效标志 0：无效；1：有效", example = "0")
    private String validFlag;

    /**
	 * 拼音首码
	 */
	@ApiModelProperty(value = "拼音首码", example = "")
    private String spellCode;

    /**
	 * 五笔首码
	 */
	@ApiModelProperty(value = "五笔首码", example = "")
    private String wbzxCode;

    /**
	 * 排序号
	 */
	@ApiModelProperty(value = "排序号", example = "")
    private Integer sequence;

    /**
	 * 2表示需要冷藏，其他表示不需要冷藏
	 */
	@ApiModelProperty(value = "2表示需要冷藏，其他表示不需要冷藏", example = "2")
    private String storageConditions;


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhysicSpec() {
        return physicSpec;
    }
    public void setPhysicSpec(String physicSpec) {
        this.physicSpec = physicSpec;
    }

    public String getSubjectClass() {
        return subjectClass;
    }
    public void setSubjectClass(String subjectClass) {
        this.subjectClass = subjectClass;
    }

    public String getDoseUnit() {
        return doseUnit;
    }
    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }
    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getPackUnit() {
        return packUnit;
    }
    public void setPackUnit(String packUnit) {
        this.packUnit = packUnit;
    }

    public double getMinDose() {
        return minDose;
    }
    public void setMinDose(double minDose) {
        this.minDose = minDose;
    }

    public double getPackSpec() {
        return packSpec;
    }
    public void setPackSpec(double packSpec) {
        this.packSpec = packSpec;
    }

    public double getRetailPrice() {
        return retailPrice;
    }
    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getPhysicForm() {
        return physicForm;
    }
    public void setPhysicForm(String physicForm) {
        this.physicForm = physicForm;
    }

    public String getToxicologyType() {
        return toxicologyType;
    }
    public void setToxicologyType(String toxicologyType) {
        this.toxicologyType = toxicologyType;
    }

    public String getBasicFlag() {
        return basicFlag;
    }
    public void setBasicFlag(String basicFlag) {
        this.basicFlag = basicFlag;
    }

    public String getValidFlag() {
        return validFlag;
    }
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getSpellCode() {
        return spellCode;
    }
    public void setSpellCode(String spellCode) {
        this.spellCode = spellCode;
    }

    public String getWbzxCode() {
        return wbzxCode;
    }
    public void setWbzxCode(String wbzxCode) {
        this.wbzxCode = wbzxCode;
    }

    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getStorageConditions() {
        return storageConditions;
    }
    public void setStorageConditions(String storageConditions) {
        this.storageConditions = storageConditions;
    }


}