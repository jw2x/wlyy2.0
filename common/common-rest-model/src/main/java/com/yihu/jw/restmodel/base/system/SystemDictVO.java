package com.yihu.jw.restmodel.base.system;

import com.yihu.jw.entity.base.system.SystemDictDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * VO - 系统字典
 * Created by progr1mmer on 2018/8/27.
 */
@ApiModel(value = "SystemDictVO", description = "系统字典")
public class SystemDictVO implements Serializable {

    //编码（唯一）
    @ApiModelProperty(value = "编码（唯一）", example = "SYSTEM_SETTING")
    private String code;
    //saas id 用于租户的自定义字典
    @ApiModelProperty(value = "saas id 用于租户的自定义字典")
    private String saasId;
    //拼音码
    @ApiModelProperty(value = "拼音码", example = "XTSZ")
    private String pyCode;
    //名称
    @ApiModelProperty(value = "名称", example = "系统设置")
    private String name;
    //类型
    @ApiModelProperty(value = "类型", example = "basic")
    private SystemDictDO.Type type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getPyCode() {
        return pyCode;
    }

    public void setPyCode(String pyCode) {
        this.pyCode = pyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SystemDictDO.Type getType() {
        return type;
    }

    public void setType(SystemDictDO.Type type) {
        this.type = type;
    }
}
