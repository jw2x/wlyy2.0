//package com.yihu.jw.restmodel.base.saas;
//
//import com.yihu.jw.restmodel.IntegerIdentityVO;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//
///**
// * VO - Saas所分配的模块功能
// * Created by progr1mmer on 2018/8/27.
// */
//@ApiModel(value = "SaasModuleFunctionVO", description = "Saas所分配的模块功能")
//public class SaasModuleFunctionVO extends IntegerIdentityVO {
//
//    //Saas ID
//    @ApiModelProperty(value = "Saas ID", example = "402303ee65634dfs0234sf9ad2wa00d2")
//    private String saasId;
//    //模块ID
//    @ApiModelProperty(value = "模块ID", example = "402303ee656498890234sf9ad2wa00sa")
//    private String moduleId;
//    //功能ID
//    @ApiModelProperty(value = "功能ID", example = "402303ee656498890sd24s9ad2wa00sd")
//    private String functionId;
//
//    public String getOrgCode() {
//        return saasId;
//    }
//
//    public void setOrgCode(String saasId) {
//        this.saasId = saasId;
//    }
//
//    public String getModuleId() {
//        return moduleId;
//    }
//
//    public void setModuleId(String moduleId) {
//        this.moduleId = moduleId;
//    }
//
//    public String getFunctionId() {
//        return functionId;
//    }
//
//    public void setFunctionId(String functionId) {
//        this.functionId = functionId;
//    }
//}
