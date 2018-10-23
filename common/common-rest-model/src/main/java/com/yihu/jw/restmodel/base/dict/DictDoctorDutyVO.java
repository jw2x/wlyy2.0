package com.yihu.jw.restmodel.base.dict;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 医生职务字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年10月19日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "DictDoctorDutyVO", description = "医生职务字典")
public class DictDoctorDutyVO extends IntegerIdentityVO{

    /**
	 * 职务标识
	 */
	@ApiModelProperty(value = "职务标识", example = "模块1")
    private String code;

    /**
	 * 职务名称（院长/科室主任等等）
	 */
	@ApiModelProperty(value = "职务名称（院长/科室主任等等）", example = "模块1")
    private String name;

    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@ApiModelProperty(value = "创建时间", example = "模块1")
    private Date createTime;


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