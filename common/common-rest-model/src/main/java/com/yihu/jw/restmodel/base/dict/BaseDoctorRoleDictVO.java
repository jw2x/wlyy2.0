package com.yihu.jw.restmodel.base.dict;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 医生角色字典vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseDoctorRoleDictVO", description = "医生角色字典")
public class BaseDoctorRoleDictVO extends IntegerIdentityVO{

    /**
	角色code	*/
    private String code;
    /**
	角色名称：全科医生、专科医生、健康管理师、管理员等	*/
    private String name;
    /**
	作废标识，1正常，0作废	*/
    private String del;

	@ApiModelProperty(value = "角色code", example = "模块1")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@ApiModelProperty(value = "角色名称：全科医生、专科医生、健康管理师、管理员等", example = "模块1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "模块1")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}