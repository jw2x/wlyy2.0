package com.yihu.ehr.iot.controller.company;

import com.yihu.ehr.iot.controller.common.BaseController;
import com.yihu.ehr.iot.service.company.CompanyService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyCertificateVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2018/1/16.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.company)
@Api(tags = "企业管理相关操作", description = "企业管理相关操作")
public class IotCompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = IotRequestMapping.Company.findCompanyPage)
    @ApiOperation(value = "分页查找企业", notes = "分页查找企业")
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
        try {
            return companyService.findCompanyPage(name,status,type,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Company.addCompany)
    @ApiOperation(value = "创建企业", notes = "创建企业")
    public Envelop<IotCompanyVO> addCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = true)String jsonData) {
        try {
            return companyService.addCompany(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Company.findCompanyById)
    @ApiOperation(value = "根据id查找企业", notes = "根据id查找企业")
    public Envelop<IotCompanyVO> findByCode(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            return companyService.findByCode(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Company.findByBusinessLicense)
    @ApiOperation(value = "根据营业执照号查找企业", notes = "根据营业执照号查找企业")
    public Envelop<IotCompanyVO> findByBusinessLicense(@ApiParam(name = "businessLicense", value = "businessLicense")
                                                       @RequestParam(value = "businessLicense", required = true) String businessLicense) {
        try {
            return companyService.findByBusinessLicense(businessLicense);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Company.delCompany)
    @ApiOperation(value = "删除企业", notes = "删除企业")
    public Envelop<IotCompanyVO> delCompany(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            return companyService.delCompany(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Company.updCompany)
    @ApiOperation(value = "修改企业信息", notes = "修改企业信息")
    public Envelop<IotCompanyVO> updCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = true)String jsonData) {
        try {
            return companyService.updCompany(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Company.findCompanyCertPage)
    @ApiOperation(value = "分页获取企业证书", notes = "分页获取企业证书")
    public Envelop<IotCompanyCertificateVO> findCompanyCertPage
            (@ApiParam(name = "name", value = "证书名称", defaultValue = "")
             @RequestParam(value = "name", required = false) String name,
             @ApiParam(name = "page", value = "第几页", defaultValue = "")
             @RequestParam(value = "page", required = false) Integer page,
             @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
             @RequestParam(value = "size", required = false) Integer size){
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return companyService.findCompanyCertPage(name,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Company.findCompanyCertById)
    @ApiOperation(value = "根据id查找企业证书", notes = "根据id查找企业证书")
    public Envelop<IotCompanyCertificateVO> findCompanyCertById(@ApiParam(name = "id", value = "id")
                                                                @RequestParam(value = "id", required = true) String id) {
        try {
            return companyService.findCompanyCertById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Company.findCompanyCertByCompanyId)
    @ApiOperation(value = "根据企业id查找企业证书", notes = "根据企业id查找企业证书")
    public Envelop<IotCompanyCertificateVO> findCompanyCertByCompanyId(@ApiParam(name = "companyId", value = "companyId")
                                                                       @RequestParam(value = "companyId", required = true) String companyId) {
        try {
            return companyService.findCompanyCertByCompanyId(companyId);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Company.addCompanyCert)
    @ApiOperation(value = "创建企业证书", notes = "创建企业证书")
    public Envelop<IotCompanyCertificateVO> addCompanyCert(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                                           @RequestParam(value = "jsonData", required = true)String jsonData) {
        try {
            return companyService.addCompanyCert(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

}
