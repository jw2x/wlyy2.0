package com.yihu.jw.restmodel.base.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictMedicineVO", description = "药品字典")
public class DictMedicineVO extends IntegerIdentityVO{

    /**
	 * saas配置id，null标识公共字典
	 */
	@ApiModelProperty(value = "saas配置id，null标识公共字典", example = "402803ee656498890165649ad2da1112")
    private String saasId;

    /**
	 * 药品编码
	 */
	@ApiModelProperty(value = "药品编码", example = "A011")
    private String code;

    /**
	 * 药品名称
	 */
	@ApiModelProperty(value = "药品名称", example = "基础胰岛素")
    private String name;

    /**
	 * 药品类型：1健康记录
	 */
	@ApiModelProperty(value = "药品类型：1健康记录", example = "1")
    private String type;

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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}