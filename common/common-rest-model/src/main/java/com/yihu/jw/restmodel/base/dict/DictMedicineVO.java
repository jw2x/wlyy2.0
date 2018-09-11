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
 * litaohong 	1.0  2018年09月11日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictMedicineVO", description = "药品字典")
public class DictMedicineVO extends IntegerIdentityVO{

    /**
	 * 药品代码
	 */
	@ApiModelProperty(value = "药品代码", example = "模块1")
    private String code;

    /**
	 * 药品中文名
	 */
	@ApiModelProperty(value = "药品中文名", example = "模块1")
    private String name;

    /**
	 * 药品所属科目代码
	 */
	@ApiModelProperty(value = "药品所属科目代码", example = "模块1")
    private String subjectCode;

    /**
	 * 药品剂型
	 */
	@ApiModelProperty(value = "药品剂型", example = "模块1")
    private String dosageForm;

    /**
	 * 药品规格
	 */
	@ApiModelProperty(value = "药品规格", example = "模块1")
    private String specification;

    /**
	 * 包装规格
	 */
	@ApiModelProperty(value = "包装规格", example = "模块1")
    private String packingSpecification;

    /**
	 * 适应症
	 */
	@ApiModelProperty(value = "适应症", example = "模块1")
    private String indication;

    /**
	 * 拼音首码
	 */
	@ApiModelProperty(value = "拼音首码", example = "模块1")
    private String spellCode;

    /**
	 * 五笔首码
	 */
	@ApiModelProperty(value = "五笔首码", example = "模块1")
    private String wbzxCode;

    /**
	 * 排序号
	 */
	@ApiModelProperty(value = "排序号", example = "模块1")
    private Integer sequence;

    /**
	 * 2表示需要冷藏，其他表示不需要冷藏
	 */
	@ApiModelProperty(value = "2表示需要冷藏，其他表示不需要冷藏", example = "模块1")
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

    public String getSubjectCode() {
        return subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getDosageForm() {
        return dosageForm;
    }
    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getSpecification() {
        return specification;
    }
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPackingSpecification() {
        return packingSpecification;
    }
    public void setPackingSpecification(String packingSpecification) {
        this.packingSpecification = packingSpecification;
    }

    public String getIndication() {
        return indication;
    }
    public void setIndication(String indication) {
        this.indication = indication;
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