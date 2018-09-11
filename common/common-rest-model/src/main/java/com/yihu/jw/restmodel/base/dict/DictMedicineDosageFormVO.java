package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 药品剂型字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年09月11日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictMedicineDosageFormVO", description = "药品剂型字典")
public class DictMedicineDosageFormVO extends IntegerIdentityVO{

    /**
	 * 剂型代码
	 */
	@ApiModelProperty(value = "剂型代码", example = "模块1")
    private String code;

    /**
	 * 剂型名称（颗粒型，注射液，胶囊等）
	 */
	@ApiModelProperty(value = "剂型名称（颗粒型，注射液，胶囊等）", example = "模块1")
    private String name;


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


}