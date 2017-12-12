package com.yihu.jw.business.user.contorller;

import com.yihu.jw.base.user.BaseEmployDO;
import com.yihu.jw.base.user.BaseEmployRoleDO;
import com.yihu.jw.business.user.service.EmployRoleService;
import com.yihu.jw.business.user.service.EmployService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/5/11.
 */
@RestController
@RequestMapping(BaseUserRequestMapping.api_common)
@Api(description = "医生，行政人员等非患者用户（基础用户）")
public class EmployController extends EnvelopRestController {
    @Autowired
    private EmployService employService;

    @Autowired
    private EmployRoleService employRoleService;

    @PostMapping(value = BaseUserRequestMapping.BaseEmploy.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "创建单个用户")
    public Envelop createBaseEmployDO(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseEmployDO baseEmployDO = toEntity(jsonData,BaseEmployDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_create,employService.createBaseEmployDO(baseEmployDO));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = BaseUserRequestMapping.BaseEmploy.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public Envelop updateBaseEmployDO(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseEmployDO baseEmployDO = toEntity(jsonData,BaseEmployDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_update,employService.updateBaseEmployDO(baseEmployDO));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据id查找用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getByPhone)
    public Envelop getEmployeeById(@ApiParam(name = "id", value = "id", required = true) @RequestParam(value = "id", required = true) String id) {
        try{
            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find,this.employService.findById(id));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据saasId查找所有用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getList)
    public Envelop getAllEmployeeBySaasId(@ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId) {
        try{
            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find,this.employService.findAllBySaasId(saasId));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据手机号和saasId查找用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getList)
    public Envelop getEmployeeByPhoneAndSaasId(@ApiParam(name = "phone", value = "phone", required = true) @RequestParam(value = "phone", required = true) String phone,
                                               @ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId) {
        try {
            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find, this.employService.findAllBySaasId(saasId));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据name模糊查询某saasId平台下的所有用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getList)
    public Envelop getListByNameAndSaasId(@ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId,
                                          @ApiParam(name = "name", value = "name", required = true) @RequestParam(value = "name", required = true) String name) {
        try{
            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find,this.employService.findAllByNameAndSaasId(name,saasId));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @ApiOperation(value = "给某一用户新增角色")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_create)
    public Envelop createEmployRoles(@ApiParam(name = "employId", value = "employId", required = true) @RequestParam(value = "employId", required = true) String employId,
                                     @ApiParam(name = "roleIds", value = "roleIds", required = true) @RequestParam(value = "roleIds", required = true) String roleIds) {
        try {
            String[] roleIdArray = roleIds.split(",");
            List<BaseEmployRoleDO> list = new ArrayList<>();
            for (String roleId : roleIdArray) {
                BaseEmployRoleDO baseEmployRoleDO = new BaseEmployRoleDO();
                baseEmployRoleDO.setEmployId(employId);
                baseEmployRoleDO.setRoleId(roleId);
                list.add(baseEmployRoleDO);
            }
            if (roleIdArray.length < 1) {
                return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find, this.employRoleService.createBaseEmployRoleDO(list.get(0)));
            }else{
                return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find, this.employRoleService.createBatchBaseEmployRoleDO(list));
            }
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @ApiOperation(value = "删除用户角色")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_delete)
    public Envelop deleteEmployRoles(@ApiParam(name = "employId", value = "employId", required = true) @RequestParam(value = "employId", required = true) String employId,
                                     @ApiParam(name = "roleIds", value = "roleIds", required = true) @RequestParam(value = "roleIds", required = true) String roleIds) {
        try {
            String[] idArray = roleIds.split(",");
            if (idArray.length < 1) {
                this.employRoleService.deleteBaseEmployRoleDO(employId,roleIds);
                return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_delete,"删除单个角色");
            }else{
                this.employRoleService.deleteBatchBaseEmployRoleDO(employId,roleIds);
                return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_delete, "删除多个角色");
            }
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
















}
