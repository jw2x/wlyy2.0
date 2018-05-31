package com.yihu.jw.business.user.contorller;

import com.yihu.jw.base.base.FunctionDO;
import com.yihu.jw.base.user.BaseEmployDO;
import com.yihu.jw.base.user.BaseEmployRoleDO;
import com.yihu.jw.business.user.service.EmployService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.jw.restmodel.base.base.FunctionVO;
import com.yihu.jw.restmodel.base.user.BaseEmployVO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/5/11.
 */
@RestController
@RequestMapping(BaseUserRequestMapping.api_user_common)
@Api(description = "医生，行政人员等非患者用户（基础用户）")
public class EmployController extends EnvelopRestController {
    @Autowired
    private EmployService employService;

    @PostMapping(value = BaseUserRequestMapping.BaseEmploy.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "创建单个用户")
    public Envelop createBaseEmployDO(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            BaseEmployDO baseEmployDO = toEntity(jsonData,BaseEmployDO.class);
            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_create,employService.createBaseEmployDO(baseEmployDO));
        } catch (Exception e){
            return Envelop.getError(e.getMessage());
        }
    }

//    @PostMapping(value = BaseUserRequestMapping.BaseEmploy.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "修改用户", notes = "修改用户")
//    public Envelop updateBaseEmployDO(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
//        try{
//            BaseEmployDO baseEmployDO = toEntity(jsonData,BaseEmployDO.class);
//            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_update,employService.updateBaseEmployDO(baseEmployDO));
//        } catch (ApiException e){
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }

    @ApiOperation(value = "根据id查找用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getById)
    public Envelop getEmployeeById(@ApiParam(name = "id", value = "id", required = true) @RequestParam(value = "id", required = true) String id) {
        try{
            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find,this.employService.findById(id));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据saasId查找所有用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getListBySaasId)
    public Envelop getAllEmployeeBySaasId(@ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId) {
        try{
            return Envelop.getSuccessList(BaseUserRequestMapping.BaseEmploy.message_success_find,this.employService.findAllBySaasId(saasId));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @ApiOperation(value = "根据手机号和saasId查找用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getByPhone)
    public Envelop getEmployeeByPhoneAndSaasId(@ApiParam(name = "phone", value = "phone", required = true) @RequestParam(value = "phone", required = true) String phone,
                                               @ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId) {
        try {
            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find, this.employService.findByPhoneAndSaasId(phone,saasId));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据name模糊查询某saasId平台下的所有用户")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getListByName)
    public Envelop getListByNameAndSaasId(@ApiParam(name = "saasId", value = "saasId", required = true) @RequestParam(value = "saasId", required = true) String saasId,
                                          @ApiParam(name = "name", value = "name", required = true) @RequestParam(value = "name", required = true) String name) {
        try{
            return Envelop.getSuccessList(BaseUserRequestMapping.BaseEmploy.message_success_find,this.employService.findAllByNameAndSaasId(name,saasId));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据指定条件查询用户列表，分页")
    @GetMapping(value = BaseUserRequestMapping.BaseEmploy.api_getListNoPage)
    public Envelop getListPageByNameAndSaasId(@ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,name,sex,photo,skill,email,phone,family_tel,introduction,jxzc,lczc,xlzc,xzzc") @RequestParam(value = "fields", required = false) String fields,
                                              @ApiParam(name = "filters", value = "过滤器，为空检索所有条件") @RequestParam(value = "filters", required = false) String filters,
                                              @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime") @RequestParam(value = "sorts", required = false) String sorts,
                                              @ApiParam(name = "size", value = "分页大小", defaultValue = "15") @RequestParam(value = "size", required = false) int size,
                                              @ApiParam(name = "page", value = "页码", defaultValue = "1") @RequestParam(value = "page", required = false) int page,
                                              HttpServletRequest request, HttpServletResponse response) throws ParseException {
        if (StringUtils.isBlank(filters)) {
            return Envelop.getError(BaseUserRequestMapping.BaseEmploy.message_fail_params_not_present, ExceptionCode.common_error_params_code);
        } else {
            filters = "status<>-1;" + filters;
        }
        if (StringUtils.isBlank(sorts)) {
            sorts = "-updateTime";
        }

        //得到list数据
        List<BaseEmployVO> list = employService.search(fields, filters, sorts, page, size);

        //获取总数
        long count = employService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<BaseEmployVO> mFunctions = convertToModels(list, new ArrayList<>(list.size()), BaseEmployVO.class, fields);

        return Envelop.getSuccessListWithPage(BaseUserRequestMapping.BaseEmploy.message_success_find, mFunctions, page, size, count);
    }


//    @ApiOperation(value = "给某一用户新增角色")
//    @GetMapping(value = BaseUserRequestMapping.BaseEmployRole.api_create)
//    public Envelop createEmployRoles(@ApiParam(name = "employId", value = "employId", required = true) @RequestParam(value = "employId", required = true) String employId,
//                                     @ApiParam(name = "roleIds", value = "roleIds", required = true) @RequestParam(value = "roleIds", required = true) String roleIds) {
//        try {
//            String[] roleIdArray = roleIds.split(",");
//            List<BaseEmployRoleDO> list = new ArrayList<>();
//            for (String roleId : roleIdArray) {
//                BaseEmployRoleDO baseEmployRoleDO = new BaseEmployRoleDO();
//                baseEmployRoleDO.setEmployId(employId);
//                baseEmployRoleDO.setRoleId(roleId);
//                list.add(baseEmployRoleDO);
//            }
//            if (roleIdArray.length < 1) {
//                return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find, this.employRoleService.createBaseEmployRoleDO(list.get(0)));
//            }else{
//                return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_find, this.employRoleService.createBatchBaseEmployRoleDO(list));
//            }
//        } catch (ApiException e) {
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }

//    @ApiOperation(value = "修改用户角色")
//    @GetMapping(value = BaseUserRequestMapping.BaseEmployRole.api_update)
//    public Envelop updateEmployRole(@ApiParam(name = "employId", value = "employId", required = true) @RequestParam(value = "employId", required = true) String id,
//                                    @ApiParam(name = "roleId", value = "roleId", required = true) @RequestParam(value = "roleId", required = true) String newRoleId) {
//        try {
//            BaseEmployRoleDO baseEmployRoleDO = new BaseEmployRoleDO();
//            baseEmployRoleDO.setId(id);
//            baseEmployRoleDO.setRoleId(newRoleId);
//            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_delete, this.employRoleService.updateBaseEmployRoleDO(baseEmployRoleDO));
//        } catch (ApiException e) {
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }

//    @ApiOperation(value = "查看用户角色列表，不分页")
//    @GetMapping(value = BaseUserRequestMapping.BaseEmployRole.api_getListNoPage)
//    public Envelop findEmployRoleList(@ApiParam(name = "employId", value = "employId", required = true) @RequestParam(value = "employId", required = true) String employId) {
//        try {
//            return Envelop.getSuccessList(BaseUserRequestMapping.BaseEmploy.message_success_delete, this.employRoleService.findAllByEmployId(employId));
//        } catch (ApiException e) {
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }

//    @ApiOperation(value = "删除用户角色")
//    @GetMapping(value = BaseUserRequestMapping.BaseEmployRole.api_delete)
//    public Envelop deleteEmployRoles(@ApiParam(name = "employId", value = "employId", required = true) @RequestParam(value = "employId", required = true) String employId,
//                                     @ApiParam(name = "roleIds", value = "roleIds", required = true) @RequestParam(value = "roleIds", required = true) String roleIds) {
//        try {
//            String[] idArray = roleIds.split(",");
//            if (idArray.length < 1) {
//                this.employRoleService.deleteBaseEmployRoleDO(employId,roleIds);
//                return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_delete,"删除单个角色");
//            }else{
//                this.employRoleService.deleteBatchBaseEmployRoleDO(employId,roleIds);
//                return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_delete, "删除多个角色");
//            }
//        } catch (ApiException e) {
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
}
