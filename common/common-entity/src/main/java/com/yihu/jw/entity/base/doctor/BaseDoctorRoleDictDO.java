package com.yihu.jw.entity.base.doctor;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 医生角色字典实体
 * 
 * @author Administrator on  2018年09月05日
 *
 */
@Entity
@Table(name = "base_doctor_role_dict")
public class BaseDoctorRoleDictDO extends IntegerIdentityEntity {

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