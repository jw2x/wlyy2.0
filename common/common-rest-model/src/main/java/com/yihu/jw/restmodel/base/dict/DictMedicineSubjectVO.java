package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 药品科目类别字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年09月11日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictMedicineSubjectVO", description = "药品科目类别字典")
public class DictMedicineSubjectVO extends IntegerIdentityVO{

    /**
	 * 类别代码
	 */
	@ApiModelProperty(value = "类别代码", example = "模块1")
    private String code;

    /**
	 * 类别名称
	 */
	@ApiModelProperty(value = "类别名称", example = "模块1")
    private String name;

    /**
	 * 父类code
	 */
	@ApiModelProperty(value = "父类code", example = "模块1")
    private String parentCode;


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

    public String getParentCode() {
        return parentCode;
    }
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }


}