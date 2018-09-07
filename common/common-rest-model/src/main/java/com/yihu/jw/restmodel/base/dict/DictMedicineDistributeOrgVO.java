package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 机构药品分发字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  2018年09月07日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictMedicineDistributeOrgVO", description = "机构药品分发字典")
public class DictMedicineDistributeOrgVO extends IntegerIdentityVO{

    /**
	 * 机构编码
	 */
	@ApiModelProperty(value = "机构编码", example = "")
    private String orgId;

    /**
	 * 药品代码
	 */
	@ApiModelProperty(value = "药品代码", example = "")
    private String medicineCode;


    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getMedicineCode() {
        return medicineCode;
    }
    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

}