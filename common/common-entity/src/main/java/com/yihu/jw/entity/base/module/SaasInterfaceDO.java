package com.yihu.jw.entity.base.module;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 租户接口
 * @author yeshijie on 2018/10/11.
 */
@Entity
@Table(name = "base_saas_interface")
public class SaasInterfaceDO extends UuidIdentityEntity {

    private String name;//接口名称
    private String saasId;//saas_id
    private String interfaceId;//接口id
    private String moduleId;//模块id
    private String moduleName;//模块名称
    private String methodName;//方法名
    private Integer protocolType;//协议类型（2restful,1webservice）
    private Integer openLevel;//开发程度（1公开，2私有）
    private Integer checkLevel;//审计程度（1审计，2不审计）
    private Integer status;//状态（1生效中，0已失效）
    private String remark;//接口说明
    private String url;//https请求地址

    private List<SaasInterfaceParamDO> entryParams;//入参
    private List<SaasInterfaceParamDO> outParams;//出参
    private List<SaasInterfaceErrorCodeDO> errorCodes;//错误说明

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "interface_id")
    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

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

    @Transient
    public List<SaasInterfaceParamDO> getEntryParams() {
        return entryParams;
    }

    public void setEntryParams(List<SaasInterfaceParamDO> entryParams) {
        this.entryParams = entryParams;
    }

    @Transient
    public List<SaasInterfaceParamDO> getOutParams() {
        return outParams;
    }

    public void setOutParams(List<SaasInterfaceParamDO> outParams) {
        this.outParams = outParams;
    }

    @Transient
    public List<SaasInterfaceErrorCodeDO> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<SaasInterfaceErrorCodeDO> errorCodes) {
        this.errorCodes = errorCodes;
    }
}
