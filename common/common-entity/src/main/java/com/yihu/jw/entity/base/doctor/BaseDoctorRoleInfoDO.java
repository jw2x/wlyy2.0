package com.yihu.jw.entity.base.doctor;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 业务模块角色字典（给医生用的）实体
 * 
 * @author litaohong on  2018年10月25日
 *
 */
@Entity
@Table(name = "base_doctor_role_info")
public class BaseDoctorRoleInfoDO extends IntegerIdentityEntity {

    /**
	 * saasid,不同租户各自医生的业务模块角色信息独立
	 */
	private String saasid;

    /**
	 * 角色code
	 */
	private String code;

    /**
	 * 角色名称：全科医生、专科医生、健康管理师、管理员等
	 */
	private String name;

    /**
	 * 作废标识，1正常，0作废
	 */
	private String del;


	@Column(name = "saasid")
    public String getSaasid() {
        return saasid;
    }
    public void setSaasid(String saasid) {
        this.saasid = saasid;
    }

	@Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

	@Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "del")
    public String getDel() {
        return del;
    }
    public void setDel(String del) {
        this.del = del;
    }



}