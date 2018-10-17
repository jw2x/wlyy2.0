package com.yihu.jw.entity.base.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.IntegerIdentityEntity;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
* 居民信息（居民就是患者）实体
*
* @author Administrator on  2018年09月05日
*
*/
@Entity
@Table(name = "patient_medicare_card")
public class PatientMedicareCardDO extends IntegerIdentityEntity {

    public enum Type{
        medicareCard(0),
        healthCard(1);

        private int value;

        Type(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
	 * 卡标识
	 */
	private String code;

    /**
	 * 卡类型,1-医保卡，2-电子健康卡
	 */
	private String type;

    /**
	 * 居民标识
	 */
	private String patientCode;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "patient_code")
    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }
}