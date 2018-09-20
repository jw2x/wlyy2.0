package com.yihu.jw.healthyhouse.model.facility;

import com.yihu.jw.entity.UuidIdentityEntity;

/**
 *  设施服务
 * @author HZY
 * @created 2018/9/20 8:48
 */
public class FacilityServices extends UuidIdentityEntity{

    private String code;
    private String name;
    private String type;//所属设施类型：系统字典设施类型

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
}
