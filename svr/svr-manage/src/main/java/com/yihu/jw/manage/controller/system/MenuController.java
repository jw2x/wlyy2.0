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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
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
    public Map<String,Object> list() {
        //查询结果
        List<ManageMenu> menus = menuService.list();

        //添加基卫
        ArrayList<ManageMenu> manageMenus = new ArrayList<ManageMenu>();
        ManageMenu manageMenu = new ManageMenu();
        manageMenu.setChildren(menus);
        manageMenu.setName("基卫后台管理系统");
        manageMenu.setParentCode("-1");
        manageMenu.setId(new Long(0));
        manageMenu.setCode("0");
        manageMenu.setType(0);
        manageMenus.add(manageMenu);

        //数据返回
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows",manageMenus);
        return map;
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
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
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

    @GetMapping("menu/menuTree")
    public List<ManageMenu> getMenus() throws ManageException {
        List<ManageMenu> menus = menuService.list();
        return menus;
    }
}
