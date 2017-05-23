package com.yihu.jw.wx.controller;

import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.MWxMenu;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.wx.model.WxMenu;
import com.yihu.jw.wx.model.WxWechat;
import com.yihu.jw.wx.service.WxMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@RestController
@RequestMapping(WxContants.WxMenu.api_common)
@Api(value = "微信菜单相关操作", description = "微信菜单相关操作")
public class WxMenuController extends EnvelopRestController {

    @Autowired
    private WxMenuService wxMenuService;

    @PostMapping(value = WxContants.WxMenu.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加微信菜单", notes = "添加微信菜单")
    public Envelop createWxMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxMenu wxMenu = toEntity(jsonData, WxMenu.class);
            return Envelop.getSuccess(WxContants.WxMenu.message_success_create, wxMenuService.createWxMenu(wxMenu));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WxContants.WxMenu.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信菜单", notes = "修改微信菜单")
    public Envelop updateWxMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxMenu wxMenu = toEntity(jsonData, WxMenu.class);
            return Envelop.getSuccess(WxContants.WxMenu.message_success_update, wxMenuService.updateWxMenu(wxMenu));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @DeleteMapping(value = WxContants.WxMenu.api_delete)
    @ApiOperation(value = "删除微信菜单", notes = "删除微信菜单")
    public Envelop deleteWxMenu(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            wxMenuService.deleteWxMenu(code);
            return Envelop.getSuccess(WxContants.WxMenu.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WxContants.WxMenu.api_getByCode)
    @ApiOperation(value = "根据code查找微信菜单", notes = "根据code查找微信菜单")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(WxContants.WxMenu.message_success_find, wxMenuService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WxContants.WxMenu.api_getWxMenus, method = RequestMethod.GET)
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
        //得到list数据
        List<WxWechat> list = wxMenuService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wxMenuService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MWxMenu> mWxMenus = convertToModels(list, new ArrayList<>(list.size()), MWxMenu.class, fields);

        return Envelop.getSuccessListWithPage(WxContants.WxMenu.message_success_find_functions,mWxMenus, page, size,count);
    }


    @GetMapping(value = WxContants.WxMenu.api_getWxMenuNoPage)
    @ApiOperation(value = "获取微信菜单列表，不分页")
    public Envelop getWxMenuNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WxMenu> list = wxMenuService.search(fields,filters,sorts);
        //封装返回格式
        List<MWxMenu> mWxMenus = convertToModels(list, new ArrayList<>(list.size()), MWxMenu.class, fields);
        return Envelop.getSuccessList(WxContants.WxMenu.message_success_find_functions,mWxMenus);
    }

    /**
     * 创建微信公众号菜单
     *
     * @return
     */
    @ApiOperation(value = "创建微信公众号菜单", notes = "创建微信公众号菜单")
    @RequestMapping(value = "/menu/create" ,method = RequestMethod.GET)
    @ResponseBody
    public String createWechatMenu(
            @ApiParam(name = "wechatCode", value = "", defaultValue = "")
            @RequestParam(value = "wechatCode", required = true)String wechatCode){
        try{
            String params ="";
            wxMenuService.createWechatMenu(wechatCode);

            //String url = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + getAccessToken();
            //
            //// 请求微信接口创建菜单
            //String jsonStr = HttpUtil.sendPost(url, params);
            //JSONObject result = new JSONObject(jsonStr);
            //if(result != null && result.get("errcode").toString().equals("0") && result.getString("errmsg").equals("ok")){
            //    return write(200,"创建成功!","data",jsonStr);
            //}else{
            //    return write(-1,"创建失败!","data",jsonStr);
            //}
            return null;
        }catch (Exception e){
            //return error(-1,"创建失败");
            return null;
        }
    }

}
