//package com.yihu.jw.business.user.contorller;
//
//import com.yihu.jw.base.user.BaseEmployRoleDO;
//import com.yihu.jw.base.user.BaseMenuDO;
//import com.yihu.jw.base.user.BaseRoleDO;
//import com.yihu.jw.base.user.BaseRoleMenuDO;
//import com.yihu.jw.business.user.service.BaseRoleService;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.exception.code.ExceptionCode;
//import com.yihu.jw.restmodel.base.user.BaseEmployVO;
//import com.yihu.jw.restmodel.base.user.BaseRoleVO;
//import com.yihu.jw.restmodel.common.Envelop;
//import com.yihu.jw.restmodel.common.EnvelopRestController;
//import com.yihu.jw.rm.base.BaseUserRequestMapping;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by LiTaohong on 2017/11/28.
// */
//@RestController
//@RequestMapping(BaseUserRequestMapping.api_user_common)
//@Api(description = "基础角色")
//public class BaseRoleController extends EnvelopRestController {
//
//    @Autowired
//    private BaseRoleService baseRoleService;
//
////    @Autowired
////    private BaseRoleMenuService baseRoleMenuService;
//
////    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    @ApiOperation(value = "创建角色", notes = "创建单个角色")
////    public Envelop createRole(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
////        try{
////            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
////            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_create,baseRoleService.createBaseRole(baseRoleDO));
////        } catch (ApiException e){
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
//
////    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    @ApiOperation(value = "修改角色", notes = "修改角色")
////    public Envelop updateBaseRole(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
////        try{
////            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
////            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_update,baseRoleService.updateBaseRole(baseRoleDO));
////        } catch (ApiException e){
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
//
////    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_getById, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    @ApiOperation(value = "查询单个角色", notes = "根据角色id查询角色信息")
////    public Envelop getOneRoleById(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
////        try{
////            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
////            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_find,baseRoleService.findById(baseRoleDO.getId()));
////        } catch (ApiException e){
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
//
////    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_getOne, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    @ApiOperation(value = "查询单个角色", notes = "根据平台和角色名称查询角色信息")
////    public Envelop getOneRoleBySaasIdAndName(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
////        try{
////            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
////            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_find,baseRoleService.findBySaasIdAndName(baseRoleDO.getName(),baseRoleDO.getOrgCode()));
////        } catch (ApiException e){
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
//
////    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_getlistNoPage, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    @ApiOperation(value = "查询多个角色", notes = "根据平台id查询所有角色信息，不分页")
////    public Envelop getRoleListBySaasId(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
////        try{
////            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
////            return Envelop.getSuccessList(BaseUserRequestMapping.BaseRole.message_success_find,baseRoleService.findAllBySaasId(baseRoleDO.getOrgCode()));
////        } catch (ApiException e){
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
//
//    @ApiOperation(value = "根据指定条件查询所有角色列表，分页")
//    @GetMapping(value = BaseUserRequestMapping.BaseRole.api_getListPage)
//    public Envelop getRoleListPage(@ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,name,remark") @RequestParam(value = "fields", required = false) String fields,
//                                              @ApiParam(name = "filters", value = "过滤器，为空检索所有条件") @RequestParam(value = "filters", required = false) String filters,
//                                              @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime") @RequestParam(value = "sorts", required = false) String sorts,
//                                              @ApiParam(name = "size", value = "分页大小", defaultValue = "15") @RequestParam(value = "size", required = false) int size,
//                                              @ApiParam(name = "page", value = "页码", defaultValue = "1") @RequestParam(value = "page", required = false) int page,
//                                              HttpServletRequest request, HttpServletResponse response) throws ParseException {
//        if (StringUtils.isBlank(filters)) {
//            return Envelop.getError(BaseUserRequestMapping.BaseRole.message_fail_params_not_present, ExceptionCode.common_error_params_code);
//        } else {
//            filters = "status<>-1;" + filters;
//        }
//        if (StringUtils.isBlank(sorts)) {
//            sorts = "-updateTime";
//        }
//
//        //得到list数据
//        List<BaseRoleVO> list = baseRoleService.search(fields, filters, sorts, page, size);
//
//        //获取总数
//        long count = baseRoleService.getCount(filters);
//        //封装头信息
//        pagedResponse(request, response, count, page, size);
//        //封装返回格式
//        List<BaseRoleVO> mFunctions = convertToModels(list, new ArrayList<>(list.size()), BaseRoleVO.class, fields);
//
//        return Envelop.getSuccessListWithPage(BaseUserRequestMapping.BaseRole.message_success_find, mFunctions, page, size, count);
//    }
//
//
////    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    @ApiOperation(value = "删除角色", notes = "根据角色id删除角色")
////    public Envelop deleteRole(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
////        try{
////            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
////            baseRoleService.deleteBaseRole(baseRoleDO);
////            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_delete);
////        } catch (ApiException e){
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
//
////    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_deleteBySaasId, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    @ApiOperation(value = "批量删除角色", notes = "删除某一平台下所有角色信息")
////    public Envelop deleteRolesBySaasId(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
////        try{
////            BaseRoleDO baseRoleDO = toEntity(jsonData,BaseRoleDO.class);
////            baseRoleService.deleteBaseRolesBySaasId(baseRoleDO.getOrgCode());
////            return Envelop.getSuccess(BaseUserRequestMapping.BaseRole.message_success_delete);
////        } catch (ApiException e){
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
//
//    @PostMapping(value = BaseUserRequestMapping.BaseRole.api_getRoleByPhoneAndSaasId, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "根据phone和saasid获取role", notes = "根据phone和saasid获取role")
//    public Envelop getPhoneAndSaasId(@ApiParam(name = "phone", value = "电话号码（账号）") @RequestParam(value = "phone", required = true) String phone,
//                                     @ApiParam(name = "saasId", value = "saasId") @RequestParam(value = "saasId", required = true) String saasId){
//        try{
//            List<BaseRoleDO> result = baseRoleService.findByPhoneAndSaasId(phone,saasId);
//            return Envelop.getSuccess("获取数据成功！",result);
//        } catch (ApiException e){
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
////    @ApiOperation(value = "給角色添加菜单", notes = "給角色添加菜单")
////    @PostMapping(value = BaseUserRequestMapping.BaseRoleMenu.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    public Envelop addMenuForRole(@ApiParam(name = "roleId", value = "", defaultValue = "") @RequestBody String roleId,
////                                  @ApiParam(name = "menuIds", value = "", defaultValue = "") @RequestBody String menuIds){
////        try{
////            String[] menuIdArray = menuIds.split(",");
////            List<BaseRoleMenuDO> list = new ArrayList<>();
////            for(String menuId:menuIdArray){
////                BaseRoleMenuDO baseRoleMenuDO = new BaseRoleMenuDO();
////                baseRoleMenuDO.setRoleId(roleId);
////                baseRoleMenuDO.setRoleId(menuId);
////            }
////            if(menuIdArray.length < 1){
////                return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_create,this.baseRoleMenuService.createBaseRoleMenuDO(list.get(0)));
////            }else{
////                return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_create,this.baseRoleMenuService.createBatchBaseRoleMenuDO(list));
////            }
////        } catch (ApiException e){
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
////
////
////    @ApiOperation(value = "修改角色菜单")
////    @GetMapping(value = BaseUserRequestMapping.BaseRoleMenu.api_update)
////    public Envelop updateMenuForRole(@ApiParam(name = "employId", value = "employId", required = true) @RequestParam(value = "employId", required = true) String id,
////                                    @ApiParam(name = "newMenuId", value = "newMenuId", required = true) @RequestParam(value = "roleId", required = true) String newMenuId) {
////        try {
////            BaseRoleMenuDO baseEmployRoleDO = new BaseRoleMenuDO();
////            baseEmployRoleDO.setId(id);
////            baseEmployRoleDO.setMenuId(newMenuId);
////            return Envelop.getSuccess(BaseUserRequestMapping.BaseEmploy.message_success_delete, this.baseRoleMenuService.updateBaseEmployRoleDO(baseEmployRoleDO));
////        } catch (ApiException e) {
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
////
////    @ApiOperation(value = "查看角色菜单列表，不分页")
////    @GetMapping(value = BaseUserRequestMapping.BaseRoleMenu.api_getListNoPage)
////    public Envelop findMenuListForRole(@ApiParam(name = "roleId", value = "roleId", required = true) @RequestParam(value = "roleId", required = true) String roleId) {
////        try {
////            return Envelop.getSuccessList(BaseUserRequestMapping.BaseEmploy.message_success_delete, this.baseRoleMenuService.findAllByRoleId(roleId));
////        } catch (ApiException e) {
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
////
////
////    @ApiOperation(value = "删除角色菜单", notes = "删除角色菜单")
////    @PostMapping(value = BaseUserRequestMapping.BaseRoleMenu.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    public Envelop deleteMenuForRole(@ApiParam(name = "roleId", value = "", defaultValue = "") @RequestBody String roleId,
////                                  @ApiParam(name = "menuIds", value = "", defaultValue = "") @RequestBody String menuIds){
////        try{
////            String[] menuIdArray = menuIds.split(",");
////            if(menuIdArray.length < 1){
////                this.baseRoleMenuService.deleteBaseRoleMenuDO(roleId,menuIds);
////                return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_delete,"删除角色单个菜单信息");
////            }else{
////                this.baseRoleMenuService.deleteBatchBaseRoleMenuDO(roleId,menuIds);
////                return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_delete,"删除角色多个菜单信息");
////            }
////        } catch (ApiException e){
////            return Envelop.getError(e.getMessage(), e.getErrorCode());
////        }
////    }
//
//}
