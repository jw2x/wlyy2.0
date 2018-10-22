package com.yihu.jw.base.endpoint.wx;

import com.alibaba.fastjson.JSONArray;
import com.yihu.jw.base.service.wx.WechatService;
import com.yihu.jw.base.service.wx.WxTemplateService;
import com.yihu.jw.entity.base.wx.*;
import com.yihu.jw.restmodel.base.wx.*;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.jw.rm.base.WechatRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Trick on 2018/9/26..
 */
@RestController
@RequestMapping(BaseRequestMapping.WeChat.wechat_base)
@Api(value = "微信基础信息管理", description = "微信基础信息管理", tags = {"微信基础 - 微信基础信息管理"})
public class WechatController extends EnvelopRestEndpoint {

    @Autowired
    private WechatService wechatService;
    @Autowired
    private WxTemplateService wxTemplateService;

    //====================微信与租户管理=======================

    @GetMapping(value = BaseRequestMapping.WeChat.getWechatInfos)
    @ApiOperation(value = "获取微信基本信息列表", notes = "获取微信基本信息列表")
    public MixEnvelop<WxWechatVO,WxWechatVO> getWxWechatList(@ApiParam(name = "name", value = "微信名称")
                                                             @RequestParam(value = "name", required = false) String name,
                                                             @ApiParam(name = "saasName", value = "租户名称")
                                                             @RequestParam(value = "saasName", required = false) String saasName,
                                                             @ApiParam(name = "status", value = "状态")
                                                             @RequestParam(value = "status", required = false) Integer status,
                                                             @ApiParam(name = "publicType", value = "微信类型")
                                                             @RequestParam(value = "publicType", required = false) Integer publicType,
                                                             @ApiParam(name = "page", value = "页码")
                                                             @RequestParam(value = "page", required = true) Integer page,
                                                             @ApiParam(name = "size", value = "每页大小")
                                                             @RequestParam(value = "size", required = true) Integer size) {
        return wechatService.getWxWechatList(name, saasName, status, publicType, page, size);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.saveWxAndSaas)
    @ApiOperation(value = "保存微信信息", notes = "保存微信信息")
    public Envelop saveWxAndSaas(@ApiParam(name = "wxWechatJson", value = "微信基本信息")
                                 @RequestParam(value = "wxWechatJson", required = true)String wxWechatJson,
                                 @ApiParam(name = "wxWechatSaasDOs", value = "微信租户关联")
                                 @RequestParam(value = "wxWechatSaasDOs", required = false)String wxWechatSaasDOs)throws Exception{
        WxWechatDO wxWechatDO = toEntity(wxWechatJson, WxWechatDO.class);
        List<WxWechatSaasDO> list = (List<WxWechatSaasDO>) JSONArray.parseArray(wxWechatSaasDOs, WxWechatSaasDO.class);
        return wechatService.saveWxAndSaas(wxWechatDO,list);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.updateWxAndSaas)
    @ApiOperation(value = "修改微信信息", notes = "修改微信信息")
    public Envelop updateWxAndSaas(@ApiParam(name = "wxWechatJson", value = "微信基本信息")
                                   @RequestParam(value = "wxWechatJson", required = true)String wxWechatJson,
                                   @ApiParam(name = "wxWechatSaasDOs", value = "微信租户关联")
                                   @RequestParam(value = "wxWechatSaasDOs", required = false)String wxWechatSaasDOs)throws Exception {
        WxWechatDO wxWechat = toEntity(wxWechatJson, WxWechatDO.class);
        List<WxWechatSaasDO> list = (List<WxWechatSaasDO>) JSONArray.parseArray(wxWechatSaasDOs, WxWechatSaasDO.class);
        return wechatService.updateWxAndSaas(wxWechat,list);
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findWxWechatSingle)
    @ApiOperation(value = "查询单条微信信息", notes = "查询单条微信信息")
    public ObjEnvelop<WxWechatSingleVO> findWxWechatSingle(String wechatId) {
        WxWechatDO wxWechatDO  = wechatService.findWxWechatSingle(wechatId);
        WxWechatSingleVO wxWechatSingleVO = convertToModel(wxWechatDO,WxWechatSingleVO.class);
        List<WxSaasVO> list = wechatService.getWxSaasVOs(wechatId);
        wxWechatSingleVO.setSaas(list);
        return success("success", wxWechatSingleVO);
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findWxWechatExist)
    @ApiOperation(value = "判断微信名称是否存在", notes = "判断微信名称是否存在")
    public Envelop findWxWechatExist(String name) {
       return success(BaseRequestMapping.WeChat.api_success, wechatService.findWxWechatExist(name));
    }


    @GetMapping(value = BaseRequestMapping.WeChat.findWechatCombo)
    @ApiOperation(value = "微信信息下拉框", notes = "微信信息下拉框")
    public MixEnvelop<WxComboVO,WxComboVO> findWechatCombo() {
        return wechatService.findWechatCombo();
    }

    //====================微信与租户管理end=======================

    //====================图文素材管理============================
    @GetMapping(value = BaseRequestMapping.WeChat.findWechatImgGroup)
    @ApiOperation(value = "获取图文素材分组", notes = "获取图文素材分组")
    public MixEnvelop<WxGraphicSceneVO,WxGraphicSceneVO> findWechatImgGroup(@ApiParam(name = "wechatId", value = "微信ID")
                                                                            @RequestParam(value = "wechatId", required = true)String wechatId,
                                                                            @ApiParam(name = "scene", value = "分组名称（场景值）")
                                                                            @RequestParam(value = "scene", required = false)String scene,
                                                                            @ApiParam(name = "page", value = "第几页")
                                                                            @RequestParam(value = "page", required = false)Integer page,
                                                                            @ApiParam(name = "size", value = "每页条数")
                                                                            @RequestParam(value = "size", required = false)Integer size) {
        return wechatService.findWechatImgGroup(wechatId,scene,page,size);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.createImgGroup)
    @ApiOperation(value = "创建图文素材分组", notes = "创建图文素材分组")
    public Envelop createImgGroup(@ApiParam(name = "wxGraphicSceneJson", value = "图文分组JSON")
                                  @RequestParam(value = "wxGraphicSceneJson", required = true)String wxGraphicSceneJson)throws Exception {
        WxGraphicSceneDO wxWechatScene = toEntity(wxGraphicSceneJson, WxGraphicSceneDO.class);
        return wechatService.createImgGroup(wxWechatScene);
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findImgGroupExist)
    @ApiOperation(value = "验证图文素材分组是否存在", notes = "验证图文素材分组是否存在")
    public Envelop findImgGroupExist(@ApiParam(name = "wechatId", value = "微信id")
                                     @RequestParam(value = "wechatId", required = true)String wechatId,
                                     @ApiParam(name = "scene", value = "场景值")
                                     @RequestParam(value = "scene", required = true)String scene) {
        return success(BaseRequestMapping.WeChat.api_success,wechatService.findImgGroupExist(wechatId,scene));
    }

    @PostMapping(value = BaseRequestMapping.WeChat.updateImgGroup)
    @ApiOperation(value = "修改图文素材分组", notes = "修改图文素材分组")
    public Envelop updateImgGroup(@ApiParam(name = "id", value = "id")
                                  @RequestParam(value = "id", required = true)String id,
                                  @ApiParam(name = "scene", value = "分组名称（场景值）")
                                  @RequestParam(value = "scene", required = true)String scene) {
        return wechatService.updateImgGroup(id,scene);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.deleteImgGroup)
    @ApiOperation(value = "删除图文素材分组", notes = "删除图文素材分组")
    public Envelop deleteImgGroup(@ApiParam(name = "id", value = "id")
                                  @RequestParam(value = "id", required = true)String id) {
        return wechatService.deleteImgGroup(id);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.saveImg)
    @ApiOperation(value = "保存图文素材", notes = "保存图文素材")
    public Envelop saveImg(@ApiParam(name = "wxGraphicMessageJson", value = "保存图文素材")
                           @RequestParam(value = "wxGraphicMessageJson", required = true)String wxGraphicMessageJson)throws Exception {
        WxGraphicMessageDO WxGraphicMessage = toEntity(wxGraphicMessageJson, WxGraphicMessageDO.class);
        return wechatService.saveImg(WxGraphicMessage);
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findImg)
    @ApiOperation(value = "获取图文素材", notes = "获取图文素材")
    public MixEnvelop<WxGraphicMessageVO,WxGraphicMessageVO> findImg(@ApiParam(name = "wechatId", value = "微信id")
                                                                     @RequestParam(value = "wechatId", required = true)String wechatId,
                                                                     @ApiParam(name = "title", value = "素材标题")
                                                                     @RequestParam(value = "title", required = false)String title,
                                                                     @ApiParam(name = "scene", value = "场景值")
                                                                     @RequestParam(value = "scene", required = false)String scene,
                                                                     @ApiParam(name = "page", value = "第几页")
                                                                     @RequestParam(value = "page", required = true)Integer page,
                                                                     @ApiParam(name = "size", value = "每页几条")
                                                                     @RequestParam(value = "size", required = true)Integer size) {
        return wechatService.findImg(wechatId, title, scene, page, size);
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findGraphicMessageSingle)
    @ApiOperation(value = "获取图文素材(单条)", notes = "获取图文素材(单条)")
    public ObjEnvelop<WxGraphicMessageVO> findGraphicMessageSingle(@ApiParam(name = "id", value = "图文id")
                                                       @RequestParam(value = "id", required = true)String id) {
        return success(wechatService.findGraphicMessageSingle(id),WxGraphicMessageVO.class);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.saveImgGroup)
    @ApiOperation(value = "分组图文素材", notes = "分组图文素材")
    public Envelop saveImgGroup(@ApiParam(name = "groups", value = "图文消息关系json")
                                @RequestParam(value = "groups", required = true)String groups) {
        List<WxGraphicSceneGroupDO> list = (List<WxGraphicSceneGroupDO>) JSONArray.parseArray(groups, WxGraphicSceneGroupDO.class);
        return wechatService.saveImgGroup(list);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.deleteImgGroupRelation)
    @ApiOperation(value = "分组图文素材", notes = "分组图文素材")
    public Envelop deleteImgGroupRelation(@ApiParam(name = "wechatId", value = "微信id")
                                          @RequestParam(value = "wechatId", required = true)String wechatId,
                                          @ApiParam(name = "scene", value = "图文分组名称（场景值）")
                                          @RequestParam(value = "scene", required = true)String scene,
                                          @ApiParam(name = "imgId", value = "图文id")
                                          @RequestParam(value = "imgId", required = true)String imgId) {
        return wechatService.deleteImgGroupRelation(wechatId,scene,imgId);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.saveWxReplyScene)
    @ApiOperation(value = "事件配置场景", notes = "事件配置场景")
    public Envelop saveWxReplyScene(@ApiParam(name = "wxReplySceneJson", value = "事件配置json")
                                    @RequestParam(value = "wxReplySceneJson", required = true)String wxReplySceneJson) throws Exception{
        WxReplySceneDO wxReplyScene = toEntity(wxReplySceneJson, WxReplySceneDO.class);
        return wechatService.saveWxReplyScene(wxReplyScene);

    }

    @PostMapping(value = BaseRequestMapping.WeChat.findDefaultReply)
    @ApiOperation(value = "获取默认事件配置", notes = "获取默认事件配置")
    public ObjEnvelop<WxReplySceneVO> findDefaultReply(@ApiParam(name = "wechatId", value = "事件配置json")
                                                       @RequestParam(value = "wechatId", required = true)String wechatId) {
        return success(wechatService.findDefaultReply(wechatId),WxReplySceneVO.class);
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findWxReplySceneExist)
    @ApiOperation(value = "验证事件配置是否存在", notes = "验证事件配置是否存在")
    public Envelop findWxReplySceneExist(@ApiParam(name = "wechatId", value = "微信id")
                                         @RequestParam(value = "wechatId", required = true)String wechatId,
                                         @ApiParam(name = "msgType", value = "消息类型")
                                         @RequestParam(value = "msgType", required = true)String msgType,
                                         @ApiParam(name = "event", value = "event")
                                         @RequestParam(value = "event", required = true)String event,
                                         @ApiParam(name = "content", value = "消息文本")
                                         @RequestParam(value = "content", required = false)String content,
                                         @ApiParam(name = "scene", value = "场景值（图文分组）")
                                         @RequestParam(value = "scene", required = true)String scene) {
        return success(BaseRequestMapping.WeChat.api_success,wechatService.findWxReplySceneExist(wechatId,msgType,event,content,scene));
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findWxReplyScene)
    @ApiOperation(value = "获取消息配置场景", notes = "获取消息配置场景")
    public MixEnvelop<WxReplySceneVO,WxReplySceneVO> findWxReplyScene(@ApiParam(name = "wechatId", value = "微信id")
                                                                      @RequestParam(value = "wechatId", required = true)String wechatId,
                                                                      @ApiParam(name = "msgType", value = "消息类型")
                                                                      @RequestParam(value = "msgType", required = false)String msgType,
                                                                      @ApiParam(name = "event", value = "事件类型")
                                                                      @RequestParam(value = "event", required = false)String event,
                                                                      @ApiParam(name = "content", value = "回复内容")
                                                                      @RequestParam(value = "content", required = false)String content,
                                                                      @ApiParam(name = "status", value = "删除状态")
                                                                      @RequestParam(value = "status", required = true)Integer status,
                                                                      @ApiParam(name = "page", value = "页数")
                                                                      @RequestParam(value = "page", required = true)Integer page,
                                                                      @ApiParam(name = "size", value = "每页大小")
                                                                      @RequestParam(value = "size", required = true)Integer size) {
        return wechatService.findWxReplyScene(wechatId, msgType, event, content,status, page, size);
    }
    //====================图文素材管理end============================

    //===================模板消息==========================================
    @PostMapping(value = BaseRequestMapping.WeChat.saveWxTemp)
    @ApiOperation(value = "保存微信模板消息基础信息", notes = "保存微信模板消息基础信息")
    public Envelop saveWxTemp(@ApiParam(name = "wxTemplateJson", value = "微信模板消息基础信息json")
                              @RequestParam(value = "wxTemplateJson", required = true)String wxTemplateJson)throws Exception {
        WxTemplateDO wxTemplate = toEntity(wxTemplateJson, WxTemplateDO.class);

        return wechatService.saveWxTemp(wxTemplate);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.findWxTempExist)
    @ApiOperation(value = "判断微信模板(模板id且模板名称)是否存在", notes = "判断微信模板(模板id且模板名称)是否存在")
    public Envelop findWxTempExist(@ApiParam(name = "wechatId", value = "微信id")
                                   @RequestParam(value = "wechatId", required = true)String wechatId,
                                   @ApiParam(name = "templateName", value = "微信id")
                                   @RequestParam(value = "templateName", required = true)String templateName,
                                   @ApiParam(name = "templateId", value = "微信模板id(微信的同步的id)")
                                   @RequestParam(value = "templateId", required = true)String templateId) {
       return success(BaseRequestMapping.WeChat.api_success,wechatService.findWxTempExist(wechatId,templateId,templateName));
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findWxtemp)
    @ApiOperation(value = "获取微信模板消息基础信息（列表）", notes = "获取微信模板消息基础信息（列表）")
    public MixEnvelop<WxTemplateVO,WxTemplateVO> findWxtemp(@ApiParam(name = "wechatId", value = "微信id")
                                                            @RequestParam(value = "wechatId", required = true)String wechatId,
                                                            @ApiParam(name = "status", value = "状态")
                                                            @RequestParam(value = "status", required = true)Integer status,
                                                            @ApiParam(name = "name", value = "微信模板名称")
                                                            @RequestParam(value = "name", required = true)String name,
                                                            @ApiParam(name = "key", value = "模板id或标题模糊匹配")
                                                            @RequestParam(value = "key", required = true)String key,
                                                            @ApiParam(name = "page", value = "页码")
                                                            @RequestParam(value = "page", required = true)Integer page,
                                                            @ApiParam(name = "size", value = "分页大小")
                                                            @RequestParam(value = "size", required = true)Integer size) {
        return wechatService.findWxtemp(wechatId, status, name, key, page, size);
    }

    @PostMapping(value = BaseRequestMapping.WeChat.saveWxTempConfig)
    @ApiOperation(value = "保存微信模板消息信息", notes = "保存微信模板消息信息")
    public Envelop saveWxTempConfig(@ApiParam(name = "wxTemplateConfigJosn", value = "微信模板消息信息json")
                                    @RequestParam(value = "wxTemplateConfigJosn", required = true)String wxTemplateConfigJosn)throws Exception {
        WxTemplateConfigDO WxTemplateConfig = toEntity(wxTemplateConfigJosn, WxTemplateConfigDO.class);
        return wechatService.saveWxTempConfig(WxTemplateConfig);
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findWxTempConfigList)
    @ApiOperation(value = "获取微信模板列表", notes = "获取微信模板列表")
    public MixEnvelop<WxTemplateConfigVO,WxTemplateConfigVO> findWxTempConfigList(@ApiParam(name = "wechatId", value = "微信id")
                                                                                  @RequestParam(value = "wechatId", required = true)String wechatId,
                                                                                  @ApiParam(name = "scene", value = "微信场景值")
                                                                                  @RequestParam(value = "scene", required = true)String scene,
                                                                                  @ApiParam(name = "templateId", value = "微信公众号模板id")
                                                                                  @RequestParam(value = "templateId", required = true)String templateId,
                                                                                  @ApiParam(name = "page", value = "第几页")
                                                                                  @RequestParam(value = "page", required = true)Integer page,
                                                                                  @ApiParam(name = "size", value = "分页大小")
                                                                                  @RequestParam(value = "size", required = true)Integer size) {
        return wechatService.findWxTempConfigList(wechatId,templateId,scene, page, size);
    }



    @GetMapping(value = BaseRequestMapping.WeChat.findWxTemplateConfig)
    @ApiOperation(value = "获取微信模板列表(单条)", notes = "获取微信模板列表(单条)")
    public ObjEnvelop<WxTemplateConfigVO>  findWxTemplateConfig(@ApiParam(name = "wechatId", value = "微信id")
                                                                @RequestParam(value = "wechatId", required = true)String wechatId,
                                                                @ApiParam(name = "name", value = "模板名称")
                                                                @RequestParam(value = "name", required = true)String name,
                                                                @ApiParam(name = "scene", value = "场景值")
                                                                @RequestParam(value = "scene", required = true)String scene) {
        return success(wechatService.findWxTemplateConfig(wechatId,name,scene), WxTemplateConfigVO.class);
    }

    @GetMapping(value = BaseRequestMapping.WeChat.findWxTemplateConfigExist)
    @ApiOperation(value = "判断微信配置模板场景值是否存在", notes = "判断微信配置模板场景值是否存在(单条)")
    public Envelop findWxTemplateConfigExist(@ApiParam(name = "wechatId", value = "微信id")
                                             @RequestParam(value = "wechatId", required = true)String wechatId,
                                             @ApiParam(name = "scene", value = "场景值")
                                             @RequestParam(value = "scene", required = true)String scene) {
        return success(BaseRequestMapping.WeChat.api_success,wechatService.findWxTemplateConfigExist(wechatId,scene));
    }

    @PostMapping(value = BaseRequestMapping.WeChat.getAllTemp)
    @ApiOperation(value = "获取所有微信模板（微信拉取）", notes = "获取所有微信模板（微信拉取）")
    public Envelop getAllTemp(@ApiParam(name = "wechatId", value = "微信id")
                              @RequestParam(value = "wechatId", required = true)String wechatId) {
        return wxTemplateService.getAllTemp(wechatId);
    }
    //===================模板消息end==========================================

    //===================微信粉丝统计==========================================
    @GetMapping(value = BaseRequestMapping.WeChat.getusersummary)
    @ApiOperation(value = "获取用户增减数据", notes = "获取用户增减数据")
    public Envelop getusersummary(@ApiParam(name = "wechatId", value = "微信id")
                                  @RequestParam(value = "wechatId", required = true)String wechatId,
                                  @ApiParam(name = "beginDate", value = "开始时间，例如：2014-12-02")
                                  @RequestParam(value = "beginDate", required = true)String beginDate,
                                  @ApiParam(name = "endDate", value = "结束时间，例如：2014-12-07")
                                  @RequestParam(value = "endDate", required = true)String endDate) {
        return wechatService.getusersummary(wechatId,beginDate,endDate);
    }

    @GetMapping(value = BaseRequestMapping.WeChat.getusercumulate)
    @ApiOperation(value = "获取累计用户数据", notes = "获取累计用户数据")
    public Envelop getusercumulate(@ApiParam(name = "wechatId", value = "微信id")
                                   @RequestParam(value = "wechatId", required = true)String wechatId,
                                   @ApiParam(name = "beginDate", value = "开始时间，例如：2014-12-02")
                                   @RequestParam(value = "beginDate", required = true)String beginDate,
                                   @ApiParam(name = "endDate", value = "结束时间，例如：2014-12-07")
                                   @RequestParam(value = "endDate", required = true)String endDate) {
        return wechatService.getusercumulate(wechatId,beginDate,endDate);
    }

    //===================微信粉丝统计end==========================================
//    @GetMapping(value = "header")
//    @ApiOperation(value = "测试header", notes = "测试header")
//    public String getHeader(){
//        return getUID();
//    }
}
