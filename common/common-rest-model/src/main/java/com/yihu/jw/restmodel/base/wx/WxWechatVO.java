package com.yihu.jw.restmodel.base.wx;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class WxWechatVO {

    @ApiModelProperty(value = "id")
    private String id ;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "对接类型")
    private Integer publicType;
    @ApiModelProperty(value = "租户")
    private List<WxSaasVO> saas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPublicType() {
        return publicType;
    }

    public void setPublicType(Integer publicType) {
        this.publicType = publicType;
    }

    public List<WxSaasVO> getSaas() {
        return saas;
    }

    public void setSaas(List<WxSaasVO> saas) {
        this.saas = saas;
    }
}
