package com.yihu.jw.manage.controller.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.manage.model.system.ManageRole;
import com.yihu.jw.manage.service.base.SaasService;
import com.yihu.jw.manage.service.system.RoleService;
import com.yihu.jw.restmodel.web.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/manage")
@Api(description = "用户管理")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private SaasService saasService;

    @GetMapping("role/list")
    @ApiOperation(value = "用户列表")
    public Envelop list(
            @ApiParam(name = "name", value = "用户名称", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
            @ApiParam(name = "start", value = "当前页(0开始)", required = false) @RequestParam(required = false, name = "start", defaultValue = "0") Integer page,
            @ApiParam(name = "length", value = "每页显示条数", required = false) @RequestParam(required = false, name = "length", defaultValue = "10") Integer pageSize
    ) {
        try {
            page=page/pageSize;
            Map<String, String> map = new HashMap<>();
            map.put("name",name);
            map.put("saasId",saasId);
            Page<ManageRole> roles = roleService.list(page, pageSize,map);
            return Envelop.getSuccessListWithPage(
                    "获取信息成功",
                    roles.getContent(),//数据内容
                    page, //当前页
                    pageSize,//每个显示条数
                    roles.getTotalElements()//总数
            );

        } catch (Exception e) {
            return Envelop.getError("获取信息失败:" + e.getMessage(), -1);
        }
    }

    @DeleteMapping(value = "role/{codes}")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    public Envelop delete(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode
    ) {
        try {
            roleService.delete(codes,userCode);
            return Envelop.getSuccess("删除成功");
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = "role/{code}")
    @ApiOperation(value = "根据code查找角色", notes = "根据code查找角色")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess("查询成功", roleService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = "role")
    @ApiOperation(value = "保存或者修改微信图文消息", notes = "保存或者修改微信图文消息")
    public Envelop saveOrUpdate(@ModelAttribute @Valid ManageRole role,@RequestParam(value = "userCode" ,required = true)String userCode) throws JsonProcessingException, ManageException {
        return roleService.saveOrUpdate(role,userCode);
    }
}
