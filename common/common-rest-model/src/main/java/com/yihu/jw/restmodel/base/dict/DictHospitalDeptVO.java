package com.yihu.jw.restmodel.base.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 医院科室字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  2018年09月05日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictHospitalDeptVO", description = "医院科室字典")
public class DictHospitalDeptVO extends IntegerIdentityVO{

    /**
	 * saas配置id，null标识公共字典
	 */
	@ApiModelProperty(value = "saas配置id，null标识公共字典", example = "模块1")
    private String saasId;

    /**
	 * 科室标识
	 */
	@ApiModelProperty(value = "科室标识", example = "模块1")
    private String code;

    /**
	 * 科室名称
	 */
	@ApiModelProperty(value = "科室名称", example = "模块1")
    private String name;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "创建时间", example = "模块1")
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

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}