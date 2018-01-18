package com.yihu.jw.restmodel.iot.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author yeshijie on 2018/1/17.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "产品信息表")
public class IotProductVO implements Serializable{

    @ApiModelProperty("产品基础信息")
    private IotProductBaseInfoVO iotProductBaseInfo;
    @ApiModelProperty("产品扩展信息")
    private IotProductExtendInfoVO iotProductExtendInfo;
    @ApiModelProperty("测量数据")
    private List<IotProductMeasuredDataVO> measuredDataVOList;

    public IotProductBaseInfoVO getIotProductBaseInfo() {
        return iotProductBaseInfo;
    }

    public void setIotProductBaseInfo(IotProductBaseInfoVO iotProductBaseInfo) {
        this.iotProductBaseInfo = iotProductBaseInfo;
    }

    public IotProductExtendInfoVO getIotProductExtendInfo() {
        return iotProductExtendInfo;
    }

    public void setIotProductExtendInfo(IotProductExtendInfoVO iotProductExtendInfo) {
        this.iotProductExtendInfo = iotProductExtendInfo;
    }

    public List<IotProductMeasuredDataVO> getMeasuredDataVOList() {
        return measuredDataVOList;
    }

    public void setMeasuredDataVOList(List<IotProductMeasuredDataVO> measuredDataVOList) {
        this.measuredDataVOList = measuredDataVOList;
    }
}
