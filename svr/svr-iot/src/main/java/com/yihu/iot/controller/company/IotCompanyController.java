package com.yihu.iot.controller.company;

import com.yihu.iot.service.company.IotCompanyService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.iot.company.IotCompanyDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopObj;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2018/1/16.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.company)
@Api(tags = "企业管理相关操作", description = "企业管理相关操作")
public class IotCompanyController extends EnvelopRestController {

    @Autowired
    private IotCompanyService iotCompanyService;

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
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            if(StringUtils.isBlank(type)){
                return iotCompanyService.queryPage(page,size,status,name);
            }else {
                return iotCompanyService.queryPage(page,size,status,name,type);
            }

        } catch (Exception e) {
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Company.addCompany)
    @ApiOperation(value = "创建企业", notes = "创建企业")
    public Envelop<IotCompanyVO> addCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                  @RequestParam(value = "jsonData", required = false)String jsonData) {
        try {
            IotCompanyDO iotCompany = toEntity(jsonData, IotCompanyDO.class);
            return Envelop.getSuccess(IotRequestMapping.Company.message_success_create, iotCompanyService.create(iotCompany));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @GetMapping(value = IotRequestMapping.Company.findCompanyById)
    @ApiOperation(value = "根据id查找企业", notes = "根据id查找企业")
    public EnvelopObj<IotCompanyVO> findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id) {
        try {
            IotCompanyDO iotCompanyDO = iotCompanyService.findById(id);
            IotCompanyVO vo = convertToModel(iotCompanyDO,IotCompanyVO.class);
            EnvelopObj<IotCompanyVO> obj = new EnvelopObj<>();
            obj.setObj(vo);
            return obj;
        } catch (ApiException e) {
            return null;
        }
    }

    @PostMapping(value = IotRequestMapping.Company.delCompany)
    @ApiOperation(value = "删除企业", notes = "删除企业")
    public Envelop<IotCompanyVO> delCompany(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id) {
        try {
            iotCompanyService.delCompany(id);
            return Envelop.getSuccess(IotRequestMapping.Company.message_success_find);
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = IotRequestMapping.Company.updCompany)
    @ApiOperation(value = "修改企业信息", notes = "修改企业信息")
    public Envelop<IotCompanyVO> updCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                  @RequestParam(value = "jsonData", required = false)String jsonData) {
        try {
            IotCompanyDO iotCompany = toEntity(jsonData, IotCompanyDO.class);
            iotCompanyService.updCompany(iotCompany);
            return Envelop.getSuccess(IotRequestMapping.Company.message_success_find);
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

}
