package com.yihu.jw.restmodel.base.dict;

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
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictHealthProblemVO", description = "健康问题字典")
public class DictHealthProblemVO extends IntegerIdentityVO{

    /**
	saas配置id，null标识公共字典	*/
    private String saasId;
    /**
	字典编码	*/
    private String code;
    /**
	字典名称	*/
    private String name;
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

	@ApiModelProperty(value = "字典编码", example = "模块1")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@ApiModelProperty(value = "字典名称", example = "模块1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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