//package com.yihu.jw.business.user.contorller;
//
//import com.yihu.jw.base.user.BaseMenuDO;
//import com.yihu.jw.base.user.BaseRoleDO;
//import com.yihu.jw.business.user.service.BaseMenuService;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.restmodel.common.Envelop;
//import com.yihu.jw.restmodel.common.EnvelopRestController;
//import com.yihu.jw.rm.base.BaseUserRequestMapping;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Created by LiTaohong on 2017/11/28.
// */
//@RestController
//@RequestMapping(BaseUserRequestMapping.api_user_common)
//@Api(description = "基础菜单")
//public class BaseMenuController extends EnvelopRestController {
//
//    @Autowired
//    private BaseMenuService baseMenuService;
//
//    @PostMapping(value = BaseUserRequestMapping.BaseMenu.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "创建菜单", notes = "创建单个菜单")
//    public Envelop createBaseMenu(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
//        try{
//            BaseMenuDO baseMenuDO = toEntity(jsonData,BaseMenuDO.class);
//            return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_create,baseMenuService.createBaseMenu(baseMenuDO));
//        } catch (ApiException e){
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    @PostMapping(value = BaseUserRequestMapping.BaseMenu.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "修改菜单", notes = "修改菜单")
//    public Envelop updateBaseMenu(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
//        try{
//            BaseMenuDO baseMenuDO = toEntity(jsonData,BaseMenuDO.class);
//            return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_update,baseMenuService.updateBaseMenuDO(baseMenuDO));
//        } catch (ApiException e){
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    @PostMapping(value = BaseUserRequestMapping.BaseMenu.api_getOne, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "查询单个菜单", notes = "根据菜单id查询菜单信息")
//    public Envelop getOneMenuById(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
//        try{
//            BaseMenuDO baseMenuDO = toEntity(jsonData,BaseMenuDO.class);
//            return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_find,baseMenuService.findById(baseMenuDO.getId()));
//        } catch (ApiException e){
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    @PostMapping(value = BaseUserRequestMapping.BaseMenu.api_getchildren, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "查询单个菜单的所有子菜单信息", notes = "根据平台和菜单名称查询菜单信息")
//    public Envelop getChlidrenMenuList(@ApiParam(name = "saasId", value = "", defaultValue = "") @RequestParam String saasId,
//                                       @ApiParam(name = "parentId", value = "", defaultValue = "") @RequestParam String parentId){
//        try{
//            return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_find,baseMenuService.getChlidrenMenuList(saasId,parentId));
//        } catch (ApiException e){
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    @PostMapping(value = BaseUserRequestMapping.BaseMenu.api_getList, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "查询多个菜单", notes = "根据平台saasId查询所有菜单信息")
//    public Envelop getMenuListBySaasId(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
//        try{
//            BaseMenuDO baseMenuDO = toEntity(jsonData,BaseMenuDO.class);
//            return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_find,baseMenuService.findAllBySaasId(baseMenuDO.getSaasId()));
//        } catch (ApiException e){
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    @PostMapping(value = BaseUserRequestMapping.BaseMenu.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "批量删除菜单", notes = "删除某一平台saasId下所有菜单信息")
//    public Envelop deleteMenusBySaasId(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
//        try{
//            BaseMenuDO baseMenuDO = toEntity(jsonData,BaseMenuDO.class);
//            baseMenuService.deleteBaseMenusBySaasId(baseMenuDO.getSaasId());
//            return Envelop.getSuccess(BaseUserRequestMapping.BaseMenu.message_success_delete);
//        } catch (ApiException e){
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//}
