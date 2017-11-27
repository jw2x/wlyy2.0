package com.yihu.jw.manage.controller.wechat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yihu.jw.manage.service.wechat.WechatMenuService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.base.wx.MWxMenu;
import com.yihu.jw.rm.base.WechatRequestMapping;
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


@RestController
@RequestMapping(WechatRequestMapping.api_common)
@Api(description = "微信菜单管理")
public class WechatMenuController {

    @Autowired
    private WechatMenuService menuService;

    @GetMapping(value=WechatRequestMapping.WxMenu.api_getWxMenus)
    @ApiOperation(value = "分页获取微信菜单列表")
    public Map<String,Object> list(
            @ApiParam(name = "name", value = "菜单名", required = false) @RequestParam(required = false, name = "name") String name,
            @ApiParam(name = "saasId", required = false) @RequestParam(required = false, name = "saasId") String saasId,
            @ApiParam(name = "page", value = "当前页", required = false) @RequestParam(required = false, name = "page", defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "每页显示条数", required = false) @RequestParam(required = false, name = "rows", defaultValue = "10") Integer rows
    ) {
        Map<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("saasId",saasId);
        Envelop envelop = menuService.list(rows, page,map);
        List list = envelop.getDetailModelList();

        //数据返回
        Map<String, Object> req = new HashMap<String, Object>();
        req.put("rows",list);
        req.put("total",envelop.getTotalCount());
        return req;
    }

    @DeleteMapping(value = "/menu/{codes}")
    @ApiOperation(value = "通过codes删除,多个code用,分割", notes = "通过codes删除")
    public Envelop deleteByCodes(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam String userCode
    ) {
        Envelop envelop = menuService.deleteByCode(codes,userCode);
        return envelop;
    }

    @GetMapping(value = WechatRequestMapping.WxMenu.api_getById)
    @ApiOperation(value = "根据code查找菜单", notes = "根据code查找菜单")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable String code
    ) {
        Envelop envelop = menuService.findByCode(code);
        Map map = (Map) envelop.getObj();
        String supMenucode = map.get("supMenucode")+"";
        if(supMenucode.equals("null")||supMenucode.equals("")){//说明是父菜单,查询是否有子菜单
            Envelop envelop1 = menuService.getChildMenus(map.get("code").toString());
            List list = (List)envelop1.getObj();
            if(list!=null&&list.size()>0){
                map.put("haveChildMenus",true);
                return envelop;
            }
        }
        map.put("haveChildMenus",false);
        return envelop;
    }

    @PostMapping(value = WechatRequestMapping.WxMenu.api_create)
    @ApiOperation(value = "保存或者修改微信菜单", notes = "保存或者修改微信菜单")
    public Envelop saveOrUpdate(@ModelAttribute @Valid MWxMenu menu,@RequestParam String userCode) throws JsonProcessingException {
        Envelop envelop = menuService.saveOrUpdate(menu,userCode);
        return envelop;
    }

    @GetMapping(value = WechatRequestMapping.WxMenu.api_getParentMenu)
    @ApiOperation(value = "根据微信code查找父菜单", notes = "根据微信code查找父菜单")
    public List getParentMenu(
            @ApiParam(name = "wechatCode", value = "wechatCode")
            @PathVariable String wechatCode
    ){
        Envelop envelop = menuService.getParentMenu(wechatCode);
        Object obj = envelop.getObj();
        if(obj==null){
            return new ArrayList<>();
        }
        return (List)obj;
    }

    @GetMapping(WechatRequestMapping.WxMenu.api_getChildMenus)
    @ApiOperation(value = "根据父菜单code获取子菜单")
    public List getChildMenus(
            @ApiParam(name = "parentCode", value = "parentCode")
            @PathVariable String parentCode){
        Envelop childMenus = menuService.getChildMenus(parentCode);
        Object obj = childMenus.getObj();
        if(obj==null){
            return new ArrayList<>();
        }
        return (List)obj;
    }
}
