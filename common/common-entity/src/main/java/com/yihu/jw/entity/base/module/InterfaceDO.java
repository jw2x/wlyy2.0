package com.yihu.jw.entity.base.module;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 接口
 * @author yeshijie on 2018/9/28.
 */
@Entity
@Table(name = "base_interface")
public class InterfaceDO extends UuidIdentityEntity {

    private String name;//接口名称
    private String moduleId;//模块id
    private String moduleName;//模块名称
    private String methodName;//方法名
    private Integer protocolType;//协议类型（2restful,1webservice）
    private Integer openLevel;//开发程度（1公开，2私有）
    private Integer checkLevel;//审计程度（1审计，2不审计）
    private Integer status;//状态（1生效中，0已失效）
    private String remark;//接口说明
    private String url;//https请求地址
    private String requestDemo;//请求示例
    private String responseDemo;//响应示例
    private String errorDemo;//异常示例

    private List<InterfaceParamDO> entryParams;//入参
    private List<InterfaceParamDO> commonEntryParams;//公共入参
    private List<InterfaceParamDO> commonOutParams;//公共出参
    private List<InterfaceParamDO> outParams;//出参
    private List<InterfaceErrorCodeDO> errorCodes;//错误说明

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "module_id")
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "module_name")
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Column(name = "method_name")
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Column(name = "protocol_type")
    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }

    @Column(name = "open_level")
    public Integer getOpenLevel() {
        return openLevel;
    }

    public void setOpenLevel(Integer openLevel) {
        this.openLevel = openLevel;
    }

    @Column(name = "check_level")
    public Integer getCheckLevel() {
        return checkLevel;
    }

    public void setCheckLevel(Integer checkLevel) {
        this.checkLevel = checkLevel;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "request_demo")
    public String getRequestDemo() {
        return requestDemo;
    }

    public void setRequestDemo(String requestDemo) {
        this.requestDemo = requestDemo;
    }

    @Column(name = "response_demo")
    public String getResponseDemo() {
        return responseDemo;
    }

    public void setResponseDemo(String responseDemo) {
        this.responseDemo = responseDemo;
    }

    @Column(name = "error_demo")
    public String getErrorDemo() {
        return errorDemo;
    }

    public void setErrorDemo(String errorDemo) {
        this.errorDemo = errorDemo;
    }

    @Transient
    public List<InterfaceParamDO> getEntryParams() {
        return entryParams;
    }

    public void setEntryParams(List<InterfaceParamDO> entryParams) {
        this.entryParams = entryParams;
    }

    @Transient
    public List<InterfaceParamDO> getOutParams() {
        return outParams;
    }

    public void setOutParams(List<InterfaceParamDO> outParams) {
        this.outParams = outParams;
    }

    @Transient
    public List<InterfaceErrorCodeDO> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<InterfaceErrorCodeDO> errorCodes) {
        this.errorCodes = errorCodes;
    }

    @Transient
    public List<InterfaceParamDO> getCommonEntryParams() {
        return commonEntryParams;
    }

    public void setCommonEntryParams(List<InterfaceParamDO> commonEntryParams) {
        this.commonEntryParams = commonEntryParams;
    }

    @Transient
    public List<InterfaceParamDO> getCommonOutParams() {
        return commonOutParams;
    }

    public void setCommonOutParams(List<InterfaceParamDO> commonOutParams) {
        this.commonOutParams = commonOutParams;
    }
}
