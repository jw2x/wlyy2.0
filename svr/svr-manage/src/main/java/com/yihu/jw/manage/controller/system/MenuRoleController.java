package com.yihu.jw.manage.controller.system;

import com.yihu.jw.manage.service.system.MenuRoleService;
import com.yihu.jw.manage.service.system.MenuService;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("manage")
@Api(description = "管理系统角色-权限关系")
public class MenuRoleController {
    @Autowired
    private MenuRoleService menuRoleService;

    @Autowired
    private MenuService menuService;


    @GetMapping("menuRole/change")
    @ApiOperation(value = "更新权限")
    public Envelop changeMenuRole(
            @ApiParam(name = "roleCode", value = "roleCode", required = true) @RequestParam(required = true, name = "roleCode") String roleCode,
            @ApiParam(name = "menuCodes", value = "menuCodes", required = true) @RequestParam(required = true, name = "menuCodes") String menuCodes){
        menuRoleService.changeMenuRole(roleCode, menuCodes);
        return Envelop.getSuccess("更新成功",null);
    }

    @GetMapping("menuRole/getMenuRoles")
    @ApiOperation(value = "获取已经存在的权限")
    public List<String> getMenuRoles(@ApiParam(name = "roleCode", value = "roleCode", required = true) @RequestParam(required = true, name = "roleCode") String roleCode){
        List<String> roles = menuRoleService.getMenuRoles(roleCode);
        return roles;
    }

    @GetMapping("menuRole/reloadPrivilege")
    @ApiOperation(value = "更新缓存中的权限")
    public void reloadPrivilege(){
        menuRoleService.reloadPrivilege();
    }

}
