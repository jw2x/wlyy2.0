package com.yihu.jw.restmodel.base.module;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yeshijie on 2018/9/28.
 */
@ApiModel(value = "InterfaceVO", description = "接口")
public class InterfaceVO extends UuidIdentityVO {

    @ApiModelProperty(value = "接口名称", example = "接口1")
    private String name;
    @ApiModelProperty(value = "模块id", example = "模块1d")
    private String moduleId;
    @ApiModelProperty(value = "模块名称", example = "模块名称")
    private String moduleName;
    @ApiModelProperty(value = "方法名", example = "方法1")
    private String methodName;
    @ApiModelProperty(value = "协议类型（2restful,1webservice）", example = "1")
    private Integer protocolType;
    @ApiModelProperty(value = "开发程度（1公开，2私有）", example = "1")
    private Integer openLevel;
    @ApiModelProperty(value = "审计程度（1审计，2不审计）", example = "1")
    private Integer checkLevel;
    @ApiModelProperty(value = "状态（1生效中，0已失效）", example = "1")
    private Integer status;
    @ApiModelProperty(value = "接口说明", example = "明")
    private String remark;
    @ApiModelProperty(value = "https请求地址", example = "https://")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }

    public Integer getOpenLevel() {
        return openLevel;
    }

    public void setOpenLevel(Integer openLevel) {
        this.openLevel = openLevel;
    }

    public Integer getCheckLevel() {
        return checkLevel;
    }

    public void setCheckLevel(Integer checkLevel) {
        this.checkLevel = checkLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
