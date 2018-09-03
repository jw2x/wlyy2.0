package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 药品字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictMedicineVO", description = "药品字典")
public class DictMedicineVO extends IntegerIdentityVO{

    /**
	saas配置id，null标识公共字典	*/
    private String saasId;
    /**
	药品编码	*/
    private String code;
    /**
	药品名称	*/
    private String name;
    /**
	药品类型：1健康记录	*/
    private String type;
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

	@ApiModelProperty(value = "药品编码", example = "模块1")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@ApiModelProperty(value = "药品名称", example = "模块1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@ApiModelProperty(value = "药品类型：1健康记录", example = "模块1")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

	@ApiModelProperty(value = "创建时间", example = "模块1")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}