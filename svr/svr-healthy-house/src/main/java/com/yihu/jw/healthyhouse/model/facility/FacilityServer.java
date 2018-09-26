package com.yihu.jw.healthyhouse.model.facility;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

/**
 * 设施服务
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
@Entity
@Table(name = "facility_server")
public class FacilityServer extends UuidIdentityEntityWithOperator {
    //设施服务编码
    @Column(name = "code", nullable = false)
    private String code;
    //设施服务名称
    @Column(name = "name")
    private String name;
    //所属设施类型：系统字典设施类型
    @Column(name = "type" )
    private String type ;
    //设施服务提供量：多少设施提供此服务
    @Column(name = "num")
    private String num;
    //设施类型字典值
    private String typeValue ;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    @Formula("( SELECT a.value FROM system_dict_entries a WHERE a.dict_id = 7 AND a.code = type )")
    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
