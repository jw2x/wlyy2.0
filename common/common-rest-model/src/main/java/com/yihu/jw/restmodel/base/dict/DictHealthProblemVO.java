package com.yihu.jw.restmodel.base.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 健康问题字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictHealthProblemVO", description = "健康问题字典")
public class DictHealthProblemVO extends IntegerIdentityVO{

    /**
	 * saas配置id，null标识公共字典
	 */
	@ApiModelProperty(value = "saas配置id，null标识公共字典", example = "402803ee656498890165649ad2da1112")
    private String saasId;

    /**
	 * 字典编码
	 */
	@ApiModelProperty(value = "字典编码", example = "HP0001")
    private String code;

    /**
	 * 字典名称
	 */
	@ApiModelProperty(value = "字典名称", example = "肠道传染病")
    private String name;

    /**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", example = "肠道疾病")
    private String description;

    /**
     * 是否慢病
     */
    @ApiModelProperty(value = "是否慢病", example = "1-是，0-否")
    private String chronicFlag;

    /**
     * 是否传染病
     */
    @ApiModelProperty(value = "是否传染病", example = "1-是，0-否")
    private String infectiousFlag;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "创建时间", example = "yyyy-MM-dd HH:mm:ss/该字段可不填")
    private Date createTime;


    public String getSaasId() {
        return saasId;
    }
    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChronicFlag() {
        return chronicFlag;
    }
    public void setChronicFlag(String chronicFlag) {
        this.chronicFlag = chronicFlag;
    }

    public String getInfectiousFlag() {
        return infectiousFlag;
    }
    public void setInfectiousFlag(String infectiousFlag) {
        this.infectiousFlag = infectiousFlag;
    }

}