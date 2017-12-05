package com.yihu.jw.business.user.contorller;

import com.yihu.jw.base.user.BaseEmployDO;
import com.yihu.jw.base.user.BaseRoleDO;
import com.yihu.jw.business.user.service.EmployService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.jw.rm.base.BaseSmsRequestMapping;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/5/11.
 */
@RestController
@RequestMapping(BaseUserRequestMapping.api_common)
@Api(description = "医生，行政人员等非患者用户（基础用户）")
public class EmployController extends EnvelopRestController {
    @Autowired
    private EmployService employService;


    @PostMapping(value = BaseUserRequestMapping.BaseEmploy.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "创建单个用户")
    public Envelop createBaseEmployDO(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseEmployDO baseEmployDO = toEntity(jsonData,BaseEmployDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_create,employService.createBaseEmployDO(baseEmployDO));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = BaseUserRequestMapping.BaseEmploy.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public Envelop updateBaseEmployDO(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseEmployDO baseEmployDO = toEntity(jsonData,BaseEmployDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_update,employService.updateBaseEmployDO(baseEmployDO));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据id查找用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getById)
    public Envelop getEmployeeById(@ApiParam(name = "id", value = "id", required = true) @RequestParam(value = "id", required = true) String id) {
        try{
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_find,this.employService.findById(id));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据saasId查找所有用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getList)
    public Envelop getAllEmployeeBySaasId(@ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId) {
        try{
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_find,this.employService.findAllBySaasId(saasId));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据name模糊查询某saasId平台下的所有用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getList)
    public Envelop getListByNameAndSaasId(@ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId) {
        try{
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_find,this.employService.findAllBySaasId(saasId));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
}
