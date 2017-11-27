package com.yihu.jw.business.wx.controller;

import com.yihu.jw.base.wx.WxMenuDO;
import com.yihu.jw.base.wx.WxWechatDO;
import com.yihu.jw.business.wx.WechatResponse;
import com.yihu.jw.business.wx.service.WechatService;
import com.yihu.jw.business.wx.service.WxMenuService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.base.wx.MWxMenu;
import com.yihu.jw.restmodel.base.wx.MWxWechat;
import com.yihu.jw.rm.base.WechatRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@RestController
@RequestMapping(WechatRequestMapping.api_common)
@Api(value = "微信菜单相关操作", description = "微信菜单相关操作")
public class WxMenuController extends EnvelopRestController {

    @Autowired
    private WxMenuService wxMenuService;

    @Autowired
    private WechatService wechatService;

    @PostMapping(value = WechatRequestMapping.WxMenu.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加微信菜单", notes = "添加微信菜单")
    public Envelop createWxMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxMenuDO wxMenu = toEntity(jsonData, WxMenuDO.class);
            return Envelop.getSuccess(WechatRequestMapping.WxMenu.message_success_create, wxMenuService.createWxMenu(wxMenu));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WechatRequestMapping.WxMenu.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信菜单", notes = "修改微信菜单")
    public Envelop updateWxMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxMenuDO wxMenu = toEntity(jsonData, WxMenuDO.class);
            return Envelop.getSuccess(WechatRequestMapping.WxMenu.message_success_update, wxMenuService.updateWxMenu(wxMenu));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @DeleteMapping(value = WechatRequestMapping.WxMenu.api_delete)
    @ApiOperation(value = "删除微信菜单", notes = "删除微信菜单")
    public Envelop deleteWxMenu(
            @ApiParam(name = "ids", value = "ids")
            @RequestParam(value = "ids", required = true) String ids,
            @ApiParam(name = "userId", value = "userId")
            @RequestParam(value = "userId", required = true) String userId,
            @ApiParam(name = "userName", value = "userName")
            @RequestParam(value = "userName", required = true) String userName
    ) {
        try {
            wxMenuService.deleteWxMenu(ids, userId, userName);
            return Envelop.getSuccess(WechatRequestMapping.WxMenu.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WechatRequestMapping.WxMenu.api_getById)
    @ApiOperation(value = "根据code查找微信菜单", notes = "根据code查找微信菜单")
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return Envelop.getSuccess(WechatRequestMapping.WxMenu.message_success_find, wxMenuService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getWxMenus, method = RequestMethod.GET)
    @ApiOperation(value = "获取微信菜单列表(分页)")
    public Envelop getWxMenus(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if(StringUtils.isBlank(sorts)){
            sorts = "-updateTime";
        }

        //得到微信列表数据
        List<WxWechatDO> wechats = wechatService.search(fields, filters, sorts, page, size);
        for(WxWechatDO wechat:wechats){
            List<WxMenuDO> parentMenus = wxMenuService.findParentMenuByWechatCode(wechat.getId());
            if (parentMenus.size()>0){
                wechat.setState("closed");
            }else{
                wechat.setState("open");
            }
        }
        //获取总数
        long count=wechatService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MWxWechat> mwechats = convertToModels(wechats, new ArrayList<>(wechats.size()), MWxWechat.class, fields);
        return Envelop.getSuccessListWithPage(WechatRequestMapping.WxMenu.message_success_find_functions,mwechats, page, size,count);
    }


    @GetMapping(value = WechatRequestMapping.WxMenu.api_getWxMenuNoPage)
    @ApiOperation(value = "获取微信菜单列表，不分页")
    public Envelop getWxMenuNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        if(filters!=null){
            filters = "supMenucode=0;"+filters;
        }else{
            filters = "supMenucode=0;";
        }
        //得到list数据
        List<WxMenuDO> list = wxMenuService.search(fields,filters,sorts);
        for(WxMenuDO wxMenu:list){
            List<WxMenuDO> childMenus = wxMenuService.findChildMenus(wxMenu.getId());
            wxMenu.setChildren(childMenus);
        }
        //封装返回格式
        List<MWxMenu> mWxMenus = convertToModels(list, new ArrayList<>(list.size()), MWxMenu.class, fields);
        Map<String, String> map = wechatService.getAllWechatConfig();
        for(MWxMenu menu:mWxMenus){
            menu.setWechatName(map.get(menu.getWechatCode()));
        }
        return Envelop.getSuccessList(WechatRequestMapping.WxMenu.message_success_find_functions,mWxMenus);
    }

    /**
     * 创建微信公众号菜单
     *
     * @return
     */
    @ApiOperation(value = "创建微信公众号菜单", notes = "创建微信公众号菜单")
    @RequestMapping(value = WechatRequestMapping.WxMenu.api_createMenu ,method = RequestMethod.GET)
    public Envelop createWechatMenu(
            @ApiParam(name = "wechatId", value = "", defaultValue = "")
            @RequestParam(value = "wechatId", required = true)String wechatId){
        try{
            JSONObject result = wxMenuService.createWechatMenu(wechatId);
            String errcode = result.get("errcode").toString();
            WechatResponse wechatResponse = new WechatResponse(Integer.valueOf(errcode));
            String msg = wechatResponse.getMsg();
            return Envelop.getSuccess("成功",msg);
        }catch (Exception e){
            return Envelop.getSuccess("创建失败",e );
        }
    }

    /**
     * 根据微信code查找父菜单
     * @param wechatId
     * @return
     */
    @GetMapping(value = WechatRequestMapping.WxMenu.api_getParentMenu)
    @ApiOperation(value = "根据微信code查找父菜单", notes = "根据微信code查找父菜单")
    public Envelop getParentMenu(
            @ApiParam(name = "wechatId", value = "wechatId")
            @PathVariable(value = "wechatId", required = true) String wechatId
    ) {
        try {
            List<WxMenuDO> parentMenus = wxMenuService.findParentMenuByWechatCode(wechatId);
            for(WxMenuDO parentMenu:parentMenus){
                List<WxMenuDO> childMenus = wxMenuService.findChildMenus(parentMenu.getId());
                if (childMenus.size()>0){
                    parentMenu.setState("closed");
                }else{
                    parentMenu.setState("open");
                }
            }
            return Envelop.getSuccess(WechatRequestMapping.WxMenu.message_success_find, parentMenus);
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    /**
     * 根据父级菜单code查找子菜单
     * @param parentCode
     * @return
     */
    @GetMapping(value = WechatRequestMapping.WxMenu.api_getChildMenus)
    @ApiOperation(value = "根据父级菜单code查找子菜单", notes = "根据父级菜单code查找子菜单")
    public Envelop getChildMenus(
            @ApiParam(name = "parentCode", value = "parentCode")
            @PathVariable(value = "parentCode", required = true) String parentCode
    ) {
        try {
            return Envelop.getSuccess(WechatRequestMapping.WxMenu.message_success_find, wxMenuService.findChildMenus(parentCode));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

}
