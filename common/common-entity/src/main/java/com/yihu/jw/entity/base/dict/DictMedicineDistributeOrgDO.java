package com.yihu.jw.entity.base.dict;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 机构药品分发字典实体
 * 
 * @author Administrator on  2018年09月07日
 *
 */
@Entity
@Table(name = "dict_medicine_distribute_org")
public class DictMedicineDistributeOrgDO extends IntegerIdentityEntity {

    /**
	 * 机构编码
	 */
	private String orgId;

    /**
	 * 药品代码
	 */
	private String medicineCode;

	@Column(name = "org_id")
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

	@Column(name = "medicine_code")
    public String getMedicineCode() {
        return medicineCode;
    }
    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

}