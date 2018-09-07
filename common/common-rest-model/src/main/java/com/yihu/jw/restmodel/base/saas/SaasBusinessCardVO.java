package com.yihu.jw.restmodel.base.saas;


import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * VO - SAAS名片
 * Created by progr1mmer on 2018/9/7.
 */
@ApiModel(value = "SaasBusinessCardVO", description = "SAAS名片")
public class SaasBusinessCardVO extends IntegerIdentityVO {

    //SAAS ID
    @ApiModelProperty(value = "Saas ID", example = "402303ee65634dfs0234sf9ad2wa00d2")
    private String saasId;
    //字段
    @ApiModelProperty(value = "实体字段", example = "name")
    private String field;
    //类型
    @ApiModelProperty(value = "名片类型", example = "doctor")
    private SaasDO.Type type;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public SaasDO.Type getType() {
        return type;
    }

    public void setType(SaasDO.Type type) {
        this.type = type;
    }
}
