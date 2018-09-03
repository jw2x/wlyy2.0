package com.yihu.jw.restmodel.base.statistics;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author yeshijie on 2018/8/31.
 */
@ApiModel(value = "QuotaVO", description = "统计指标")
public class QuotaVO extends UuidIdentityVO {

    @ApiModelProperty(value = "指标code")
    private String code;//指标code
    @ApiModelProperty(value = "指标名称")
    private String name;//指标名称
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "删除标志：1: 正常 0： 删除")
    private Integer del;//

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
