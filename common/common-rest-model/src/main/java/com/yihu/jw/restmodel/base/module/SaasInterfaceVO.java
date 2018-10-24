package com.yihu.jw.restmodel.base.module;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 租户接口
 * @author yeshijie on 2018/10/11.
 */
@ApiModel(value = "SaasInterfaceVO", description = "租户接口")
public class SaasInterfaceVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saas_id", example = "saas_id")
    private String saasId;
    @ApiModelProperty(value = "接口id", example = "接口id")
    private String interfaceId;
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
    @ApiModelProperty(value = "状态（1启用，0暂停）", example = "1")
    private Integer status;
    @ApiModelProperty(value = "接口说明", example = "明")
    private String remark;
    @ApiModelProperty(value = "https请求地址", example = "")
    private String url;
    @ApiModelProperty(value = "请求示例", example = "")
    private String requestDemo;
    @ApiModelProperty(value = "响应示例", example = "")
    private String responseDemo;
    @ApiModelProperty(value = "异常示例", example = "")
    private String errorDemo;

    @ApiModelProperty(value = "公共入参", example = "")
    private List<SaasInterfaceParamVO> commonEntryParams;
    @ApiModelProperty(value = "公共出参", example = "")
    private List<SaasInterfaceParamVO> commonOutParams;
    @ApiModelProperty(value = "入参", example = "")
    private List<SaasInterfaceParamVO> entryParams;
    @ApiModelProperty(value = "出参", example = "")
    private List<SaasInterfaceParamVO> outParams;
    @ApiModelProperty(value = "错误说明", example = "")
    private List<SaasInterfaceErrorCodeVO> errorCodes;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

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

    public List<SaasInterfaceParamVO> getEntryParams() {
        return entryParams;
    }

    public void setEntryParams(List<SaasInterfaceParamVO> entryParams) {
        this.entryParams = entryParams;
    }

    public List<SaasInterfaceParamVO> getOutParams() {
        return outParams;
    }

    public void setOutParams(List<SaasInterfaceParamVO> outParams) {
        this.outParams = outParams;
    }

    public List<SaasInterfaceErrorCodeVO> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<SaasInterfaceErrorCodeVO> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public List<SaasInterfaceParamVO> getCommonEntryParams() {
        return commonEntryParams;
    }

    public void setCommonEntryParams(List<SaasInterfaceParamVO> commonEntryParams) {
        this.commonEntryParams = commonEntryParams;
    }

    public List<SaasInterfaceParamVO> getCommonOutParams() {
        return commonOutParams;
    }

    public void setCommonOutParams(List<SaasInterfaceParamVO> commonOutParams) {
        this.commonOutParams = commonOutParams;
    }

    public String getRequestDemo() {
        return requestDemo;
    }

    public void setRequestDemo(String requestDemo) {
        this.requestDemo = requestDemo;
    }

    public String getResponseDemo() {
        return responseDemo;
    }

    public void setResponseDemo(String responseDemo) {
        this.responseDemo = responseDemo;
    }

    public String getErrorDemo() {
        return errorDemo;
    }

    public void setErrorDemo(String errorDemo) {
        this.errorDemo = errorDemo;
    }
}
