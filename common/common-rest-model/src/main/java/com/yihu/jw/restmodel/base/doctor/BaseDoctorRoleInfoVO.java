package com.yihu.jw.restmodel.base.doctor;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * 
 * 业务模块角色字典（给医生用的）vo
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  2018年10月25日 Created
 *
 * </pre>
 * @since 1.
 */
@ApiModel(value = "BaseDoctorRoleInfoVO", description = "业务模块角色字典（给医生用的）")
public class BaseDoctorRoleInfoVO extends IntegerIdentityVO{

    /**
	 * saasid,不同租户各自医生的业务模块角色信息独立
	 */
	@ApiModelProperty(value = "saasid,不同租户各自医生的业务模块角色信息独立", example = "模块1")
    private String saasid;

    /**
	 * 角色code
	 */
	@ApiModelProperty(value = "角色code", example = "模块1")
    private String code;

    /**
	 * 角色名称：全科医生、专科医生、健康管理师、管理员等
	 */
	@ApiModelProperty(value = "角色名称：全科医生、专科医生、健康管理师、管理员等", example = "模块1")
    private String name;

    /**
	 * 作废标识，1正常，0作废
	 */
	@ApiModelProperty(value = "作废标识，1正常，0作废", example = "模块1")
    private String del;


    public String getSaasid() {
        return saasid;
    }
    public void setSaasid(String saasid) {
        this.saasid = saasid;
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

    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }


}