package com.yihu.jw.entity.base.org;

import com.yihu.jw.entity.IntegerIdentityEntity;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* 机构与Saas关联信息实体
*
* @author litaohong on  2018年10月20日
*
*/
@Entity
@Table(name = "base_org_saas")
public class BaseOrgSaasDO extends IntegerIdentityEntity {

    /**
	 * saas化配置
	 */
	private String saasid;

    /**
	 * 机构标识
	 */
	private String orgCode;


	@Column(name = "saasid")
    public String getSaasid() {
        return saasid;
    }
    public void setSaasid(String saasid) {
        this.saasid = saasid;
    }

	@Column(name = "org_code")
    public String getOrgCode() {
        return orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }



}