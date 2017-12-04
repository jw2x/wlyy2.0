package com.yihu.jw.business.user.contorller;

import com.yihu.jw.base.user.BaseRoleDO;
import com.yihu.jw.business.user.service.BaseRoleService;
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

/**
 * Created by LiTaohong on 2017/11/28.
 */
@RestController
@RequestMapping("/role")
@Api(description = "角色")
public class BaseRoleController extends EnvelopRestController {

    @Autowired
    private BaseRoleService baseRoleService;

    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建角色", notes = "创建单个角色")
    public Envelop createRole(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_create,baseRoleService.createBaseRole(baseRoleDO));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改角色", notes = "修改角色")
    public Envelop updateBaseRole(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_update,baseRoleService.updateBaseRole(baseRoleDO));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_getOne, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询单个角色", notes = "根据角色id查询角色信息")
    public Envelop getOneRoleById(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_find,baseRoleService.findById(baseRoleDO.getId()));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_getOne, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询单个角色", notes = "根据平台和角色名称查询角色信息")
    public Envelop getOneRoleBySaasIdAndName(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_find,baseRoleService.findBySaasIdAndName(baseRoleDO.getName(),baseRoleDO.getSaasId()));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_getList, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询多个角色", notes = "根据平台id查询所有角色信息")
    public Envelop getRoleListBySaasId(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_find,baseRoleService.findAllBySaasId(baseRoleDO.getSaasId()));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除角色", notes = "根据角色id删除角色")
    public Envelop deleteRole(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
            baseRoleService.deleteBaseRole(baseRoleDO);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_delete);
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "批量删除角色", notes = "删除某一平台下所有角色信息")
    public Envelop deleteRolesBySaasId(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
            baseRoleService.deleteBaseRolesBySaasId(baseRoleDO.getSaasId());
            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_delete);
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

}
