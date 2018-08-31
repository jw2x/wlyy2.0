package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * ICD10字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictIcd10VO", description = "ICD10字典")
public class DictIcd10VO extends IntegerIdentityVO{

    /**
	saas配置id，null标识公共字典	*/
    private String saasId;
    /**
	icd10字典编码	*/
    private String code;
    /**
	icd10字典名称	*/
    private String name;
    /**
	字典名称拼音首字母	*/
    private String phoneticCode;
    /**
	是否慢病	*/
    private String chronicFlag;
    /**
	是否传染病	*/
    private String infectiousFlag;
    /**
	描述	*/
    private String description;
    /**
	创建时间	*/
    private Date createTime;

	@ApiModelProperty(value = "saas配置id，null标识公共字典", example = "模块1")
    public String getSaasId() {
        return saasId;
    }
    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

	@ApiModelProperty(value = "icd10字典编码", example = "模块1")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@ApiModelProperty(value = "icd10字典名称", example = "模块1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@ApiModelProperty(value = "字典名称拼音首字母", example = "模块1")
    public String getPhoneticCode() {
        return phoneticCode;
    }
    public void setPhoneticCode(String phoneticCode) {
        this.phoneticCode = phoneticCode;
    }

	@ApiModelProperty(value = "是否慢病", example = "模块1")
    public String getChronicFlag() {
        return chronicFlag;
    }
    public void setChronicFlag(String chronicFlag) {
        this.chronicFlag = chronicFlag;
    }

	@ApiModelProperty(value = "是否传染病", example = "模块1")
    public String getInfectiousFlag() {
        return infectiousFlag;
    }
    public void setInfectiousFlag(String infectiousFlag) {
        this.infectiousFlag = infectiousFlag;
    }

	@ApiModelProperty(value = "描述", example = "模块1")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

	@ApiModelProperty(value = "创建时间", example = "模块1")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}