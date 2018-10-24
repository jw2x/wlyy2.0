package com.yihu.jw.entity.base.org;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* 机构与机构管理员关联信息实体
*
* @author litaohong on  2018年10月20日
*
*/
@Entity
@Table(name = "base_org_user")
public class BaseOrgUserDO extends IntegerIdentityEntity {

    /**
	 * 机构标识，base_org里的code
	 */
	private String orgCode;

    /**
	 * 用户账号，base_user表里的id
	 */
	private String userId;


	@Column(name = "org_code")
    public String getOrgCode() {
        return orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

	@Column(name = "user_id")
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }



}