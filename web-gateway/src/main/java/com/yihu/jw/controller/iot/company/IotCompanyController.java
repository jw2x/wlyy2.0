package com.yihu.jw.controller.iot.company;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.iot.IotCommonContants;
import com.yihu.jw.fegin.iot.company.IotCompanyFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.company.IotCompanyCertificateVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2018/1/20.
 */
@RestController
@RequestMapping(IotCommonContants.Common.company)
@Api(tags = "企业管理相关操作", description = "企业管理相关操作")
public class IotCompanyController extends EnvelopRestController {

    @Autowired
    private IotCompanyFeign iotCompanyFeign;

    @GetMapping(value = IotRequestMapping.Company.findCompanyPage)
    @ApiOperation(value = "分页查找企业", notes = "分页查找企业")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotCompanyVO> findCompanyPage(@ApiParam(name = "name", value = "供应商名称或联系人姓名", defaultValue = "")
                                       @RequestParam(value = "name", required = false) String name,
                                                 @ApiParam(name = "status", value = "审核状态", defaultValue = "")
                                       @RequestParam(value = "status", required = false) String status,
                                                 @ApiParam(name = "type", value = "企业类型", defaultValue = "")
                                       @RequestParam(value = "type", required = false) String type,
                                                 @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                       @RequestParam(value = "page", required = false) Integer page,
                                                 @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                       @RequestParam(value = "size", required = false) Integer size){
        return iotCompanyFeign.findCompanyPage(name,status,type,page,size);
    }

    @PostMapping(value = IotRequestMapping.Company.addCompany)
    @ApiOperation(value = "创建企业", notes = "创建企业")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotCompanyVO> addCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                  @RequestParam(value = "jsonData", required = true)String jsonData) {
        return iotCompanyFeign.addCompany(jsonData);
    }

    @GetMapping(value = IotRequestMapping.Company.findCompanyById)
    @ApiOperation(value = "根据id查找企业", notes = "根据id查找企业")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotCompanyVO> findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id) {
        return iotCompanyFeign.findByCode(id);
    }

    @GetMapping(value = IotRequestMapping.Company.findByBusinessLicense)
    @ApiOperation(value = "根据营业执照号查找企业", notes = "根据营业执照号查找企业")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotCompanyVO> findByBusinessLicense(@ApiParam(name = "businessLicense", value = "businessLicense")
                                            @RequestParam(value = "businessLicense", required = true) String businessLicense) {
        return iotCompanyFeign.findByBusinessLicense(businessLicense);
    }

    @PostMapping(value = IotRequestMapping.Company.delCompany)
    @ApiOperation(value = "删除企业", notes = "删除企业")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotCompanyVO> delCompany(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id) {
        return iotCompanyFeign.delCompany(id);
    }

    @PostMapping(value = IotRequestMapping.Company.updCompany)
    @ApiOperation(value = "修改企业信息", notes = "修改企业信息")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotCompanyVO> updCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                  @RequestParam(value = "jsonData", required = true)String jsonData) {
        return iotCompanyFeign.updCompany(jsonData);
    }

    @GetMapping(value = IotRequestMapping.Company.findCompanyCertPage)
    @ApiOperation(value = "分页获取企业证书", notes = "分页获取企业证书")
    public Envelop<IotCompanyCertificateVO> findCompanyCertPage
            (@ApiParam(name = "name", value = "证书名称", defaultValue = "")
             @RequestParam(value = "name", required = false) String name,
             @ApiParam(name = "companyId", value = "企业id", defaultValue = "")
             @RequestParam(value = "companyId", required = false) String companyId,
             @ApiParam(name = "page", value = "第几页", defaultValue = "")
             @RequestParam(value = "page", required = false) Integer page,
             @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
             @RequestParam(value = "size", required = false) Integer size){
        return iotCompanyFeign.findCompanyCertPage(name,companyId,page,size);
    }

    @GetMapping(value = IotRequestMapping.Company.findCompanyCertById)
    @ApiOperation(value = "根据id查找企业证书", notes = "根据id查找企业证书")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotCompanyCertificateVO> findCompanyCertById(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        return iotCompanyFeign.findCompanyCertById(id);
    }

    @GetMapping(value = IotRequestMapping.Company.findCompanyCertByCompanyId)
    @ApiOperation(value = "根据企业id查找企业证书", notes = "根据企业id查找企业证书")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotCompanyCertificateVO> findCompanyCertByCompanyId(@ApiParam(name = "companyId", value = "companyId")
                                                                @RequestParam(value = "companyId", required = true) String companyId) {
        return iotCompanyFeign.findCompanyCertByCompanyId(companyId);
    }

    @PostMapping(value = IotRequestMapping.Company.addCompanyCert)
    @ApiOperation(value = "创建企业证书", notes = "创建企业证书")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<IotCompanyCertificateVO> addCompanyCert(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = true)String jsonData) {
        return iotCompanyFeign.addCompanyCert(jsonData);
    }

    @PostMapping(value = IotRequestMapping.Company.delCompanyCert)
    @ApiOperation(value = "删除企业证书", notes = "删除企业证书")
    public Envelop<IotCompanyCertificateVO> delCompanyCert(@ApiParam(name = "id", value = "id", defaultValue = "")
                                                           @RequestParam(value = "id", required = true)String id) {
        return iotCompanyFeign.delCompanyCert(id);
    }

}
