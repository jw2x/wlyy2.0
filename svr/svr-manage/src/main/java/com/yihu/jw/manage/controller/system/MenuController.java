package com.yihu.jw.manage.controller.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.model.system.ManageMenu;
import com.yihu.jw.manage.service.system.MenuService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.exception.business.ManageException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/12.
 */

@RestController
@RequestMapping("/manage")
@Api(description = "用户管理")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("menu/list")
    @ApiOperation(value = "用户列表")
    public Envelop list(
            @ApiParam(name = "name", value = "用户名称", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "start", value = "当前页(0开始)", required = false) @RequestParam(required = false, name = "start", defaultValue = "0") Integer page,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer pageSize
    ) {
        try {
            page=page/pageSize;
            Page<ManageMenu> menus = menuService.list(name, page, pageSize);
            return Envelop.getSuccessListWithPage(
                    "获取信息成功",
                    menus.getContent(),//数据内容
                    page, //当前页
                    pageSize,//每个显示条数
                    menus.getTotalElements()//总数
            );

        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }

    @GetMapping(value = "menu/{code}")
    @ApiOperation(value = "根据code查找用户", notes = "根据code查找用户")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess("查询成功", menuService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping("childMenu/list/{parentCode}")
    @ApiOperation(value = "根据父菜单code,获取该父菜单下的子菜单")
    public Envelop getChildMenus(
            @ApiParam(name = "parentCode", value = "parentCode")
            @PathVariable String parentCode){
        List<ManageMenu> childMenus = menuService.getChildMenus(parentCode);
        return Envelop.getSuccess("查询成功", childMenus);
    }

    @GetMapping(value = "/parentMenus/list")
    @ApiOperation(value = "获取所有父菜单", notes = "获取所有父菜单")
    public Envelop getParentMenus(){
        List<ManageMenu> parentMenus = menuService.getParentMenus();
        return Envelop.getSuccess("查询成功", parentMenus);
    }

    @DeleteMapping(value = "menu/{codes}")
    @ApiOperation(value = "删除微信模版", notes = "删除微信模版")
    public Envelop delete(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode
            ) {
        try {
            menuService.delete(codes,userCode);
            return Envelop.getSuccess("删除成功");
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = "menu")
    @ApiOperation(value = "保存或者修改菜单", notes = "保存或者修改菜单")
    public Envelop saveOrUpdate(@ModelAttribute @Valid ManageMenu menu,@RequestParam(value = "userCode" ,required = true)String userCode) throws JsonProcessingException, ManageException {
        menuService.saveOrUpdate(menu,userCode);
        return Envelop.getSuccess("成功");
    }

    @RequestMapping("menu/menuTree")
    public Envelop getMenus() throws ManageException {
        Map<String, List> menuTree = menuService.getMenuTree();
        return Envelop.getSuccess("查询成功",menuTree);
    }

}
