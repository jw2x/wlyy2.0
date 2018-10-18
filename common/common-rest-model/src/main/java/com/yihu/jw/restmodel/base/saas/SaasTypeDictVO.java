package com.yihu.jw.restmodel.base.saas;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zdm on 2018/10/10.
 */
@ApiModel(value = "SaasTypeDictVO", description = "SAAS类型VO")
public class SaasTypeDictVO extends UuidIdentityVOWithOperator {

    @ApiModelProperty(value = "Saas类型编码", example = "familyDoctor")
    private Integer code;

    @ApiModelProperty(value = "Saas类型名称", example = "家医型")
    private String name;

    @ApiModelProperty(value = "状态（effective-生效中，invalid-已失效）", example = "effective")
    private SaasTypeDictDO.Status status;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SaasTypeDictDO.Status getStatus() {
        return status;
    }

    public void setStatus(SaasTypeDictDO.Status status) {
        this.status = status;
    }
}
