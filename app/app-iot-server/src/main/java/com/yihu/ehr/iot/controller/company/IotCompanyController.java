package com.yihu.ehr.iot.controller.company;

import com.yihu.ehr.iot.controller.common.BaseController;
import com.yihu.ehr.iot.service.company.CompanyService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping(value = IotRequestMapping.Company.addCompany)
//    @ApiOperation(value = "创建企业", notes = "创建企业")
//    public Envelop<IotCompanyVO> addCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
//                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
//        try {
//            IotCompanyDO iotCompany = toEntity(jsonData, IotCompanyDO.class);
//            return Envelop.getSuccess(IotRequestMapping.Company.message_success_create, iotCompanyService.create(iotCompany));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Envelop.getError(e.getMessage());
//        }
//    }
//
//    @GetMapping(value = IotRequestMapping.Company.findCompanyById)
//    @ApiOperation(value = "根据id查找企业", notes = "根据id查找企业")
//    public Envelop<IotCompanyVO> findByCode(@ApiParam(name = "id", value = "id")
//                                            @RequestParam(value = "id", required = true) String id) {
//        try {
//            IotCompanyDO iotCompanyDO = iotCompanyService.findById(id);
//            IotCompanyVO vo = iotCompanyService.convertToModelVO(iotCompanyDO);
//            return Envelop.getSuccess(IotRequestMapping.Company.message_success_find, vo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Envelop.getError(e.getMessage());
//        }
//    }
//
//    @GetMapping(value = IotRequestMapping.Company.findByBusinessLicense)
//    @ApiOperation(value = "根据营业执照号查找企业", notes = "根据营业执照号查找企业")
//    public Envelop<IotCompanyVO> findByBusinessLicense(@ApiParam(name = "businessLicense", value = "businessLicense")
//                                                       @RequestParam(value = "businessLicense", required = true) String businessLicense) {
//        try {
//            IotCompanyDO iotCompanyDO = iotCompanyService.findByBusinessLicense(businessLicense);
//            IotCompanyVO vo = iotCompanyService.convertToModelVO(iotCompanyDO);
//            return Envelop.getSuccess(IotRequestMapping.Company.message_success_find, vo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Envelop.getError(e.getMessage());
//        }
//    }
//
//    @PostMapping(value = IotRequestMapping.Company.delCompany)
//    @ApiOperation(value = "删除企业", notes = "删除企业")
//    public Envelop<IotCompanyVO> delCompany(@ApiParam(name = "id", value = "id")
//                                            @RequestParam(value = "id", required = true) String id) {
//        try {
//            iotCompanyService.delCompany(id);
//            return Envelop.getSuccess(IotRequestMapping.Company.message_success_find);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Envelop.getError(e.getMessage());
//        }
//    }
//
//    @PostMapping(value = IotRequestMapping.Company.updCompany)
//    @ApiOperation(value = "修改企业信息", notes = "修改企业信息")
//    public Envelop<IotCompanyVO> updCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
//                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
//        try {
//            IotCompanyDO iotCompany = toEntity(jsonData, IotCompanyDO.class);
//            iotCompanyService.updCompany(iotCompany);
//            return Envelop.getSuccess(IotRequestMapping.Company.message_success_find);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Envelop.getError(e.getMessage());
//        }
//    }
//
//    @PostMapping(value = IotRequestMapping.Company.findCompanyCertPage)
//    @ApiOperation(value = "分页获取企业证书", notes = "分页获取企业证书")
//    public Envelop<IotCompanyCertificateVO> findCompanyCertPage
//            (@ApiParam(name = "name", value = "证书名称", defaultValue = "")
//             @RequestParam(value = "name", required = false) String name,
//             @ApiParam(name = "page", value = "第几页", defaultValue = "")
//             @RequestParam(value = "page", required = false) Integer page,
//             @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
//             @RequestParam(value = "size", required = false) Integer size){
//        try {
//            if(page == null|| page < 0){
//                page = 1;
//            }
//            if(size == null){
//                size = 10;
//            }
//            return iotCompanyCertificateService.queryPage(page,size,name);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Envelop.getError(e.getMessage());
//        }
//    }
//
//    @GetMapping(value = IotRequestMapping.Company.findCompanyCertById)
//    @ApiOperation(value = "根据id查找企业证书", notes = "根据id查找企业证书")
//    public Envelop<IotCompanyCertificateVO> findCompanyCertById(@ApiParam(name = "id", value = "id")
//                                                                @RequestParam(value = "id", required = true) String id) {
//        try {
//            IotCompanyCertificateDO iotCompanyCertificateDO = iotCompanyCertificateService.findById(id);
//            IotCompanyCertificateVO vo = convertToModel(iotCompanyCertificateDO,IotCompanyCertificateVO.class);
//            return Envelop.getSuccess(IotRequestMapping.Common.message_success_find, vo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Envelop.getError(e.getMessage());
//        }
//    }
//
//    @GetMapping(value = IotRequestMapping.Company.findCompanyCertByCompanyId)
//    @ApiOperation(value = "根据企业id查找企业证书", notes = "根据企业id查找企业证书")
//    public Envelop<IotCompanyCertificateVO> findCompanyCertByCompanyId(@ApiParam(name = "companyId", value = "companyId")
//                                                                       @RequestParam(value = "companyId", required = true) String companyId) {
//        try {
//            List<IotCompanyCertificateDO> iotCompanyCertificateDOList = iotCompanyCertificateService.findByCompanyId(companyId);
//            List<IotCompanyCertificateVO> voList = convertToModels(iotCompanyCertificateDOList,new ArrayList<>(iotCompanyCertificateDOList.size()),IotCompanyCertificateVO.class);
//            return Envelop.getSuccessList(IotRequestMapping.Common.message_success_find, voList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Envelop.getError(e.getMessage());
//        }
//    }
//
//    @PostMapping(value = IotRequestMapping.Company.addCompanyCert)
//    @ApiOperation(value = "创建企业证书", notes = "创建企业证书")
//    public Envelop<IotCompanyCertificateVO> addCompanyCert(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
//                                                           @RequestParam(value = "jsonData", required = false)String jsonData) {
//        try {
//            IotCompanyCertificateDO iotCompanyCertificate = toEntity(jsonData, IotCompanyCertificateDO.class);
//            return Envelop.getSuccess(IotRequestMapping.Common.message_success_create, iotCompanyCertificateService.create(iotCompanyCertificate));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Envelop.getError(e.getMessage());
//        }
//    }

}
