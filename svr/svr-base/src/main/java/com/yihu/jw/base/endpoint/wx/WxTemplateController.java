package com.yihu.jw.base.endpoint.wx;

import com.yihu.jw.base.service.wx.WxTemplateService;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.WechatRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@RestController
@RequestMapping(WechatRequestMapping.api_common)
@Api(value = "微信模版相关操作", description = "微信模版相关操作", tags = {"微信管理 - 微信模版相关操作"})
public class WxTemplateController extends EnvelopRestEndpoint {

    @Autowired
    private WxTemplateService wxTemplateService;

    @PostMapping(value = WechatRequestMapping.WxTemplate.api_test_template, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "测试发送微信模板", notes = "测试发送微信模板")
    public String sendWeTempMesTest(String wechatId,String openid)throws Exception{
        return wxTemplateService.sendWeTempMesTest(wechatId,openid);
    }

    @PostMapping(value = WechatRequestMapping.WxTemplate.api_getAllTemplate, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取所有微信模板", notes = "获取所有微信模板")
    public String  getAllTemp(String wechatId) {
        return wxTemplateService.getAllTemp(wechatId);
    }



//    @PostMapping(value = WechatRequestMapping.WxTemplate.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "创建微信模版", notes = "创建微信模版")
//    public Envelop createWxTemplate(
//            @ApiParam(name = "json_data", value = "微信模版json字符串")
//            @RequestBody String jsonData) {
//        try {
//            WxTemplateDO WxTemplate = toEntity(jsonData, WxTemplateDO.class);
//            return Envelop.getSuccess(WechatRequestMapping.WxTemplate.message_success_create, wxTemplateService.createWxTemplate(WxTemplate));
//        } catch (ApiException e) {
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    @PutMapping(value = WechatRequestMapping.WxTemplate.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "修改微信模版", notes = "修改微信模版")
//    public Envelop updateWxTemplate(
//            @ApiParam(name = "json_data", value = "", defaultValue = "")
//            @RequestBody String jsonData) {
//        try {
//            WxTemplateDO WxTemplate = toEntity(jsonData, WxTemplateDO.class);
//            return Envelop.getSuccess(WechatRequestMapping.WxTemplate.message_success_update, wxTemplateService.updateWxTemplate(WxTemplate));
//        } catch (ApiException e) {
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    @DeleteMapping(value = WechatRequestMapping.WxTemplate.api_delete)
//    @ApiOperation(value = "删除微信模版", notes = "删除微信模版")
//    public Envelop deleteWxTemplate(
//            @ApiParam(name = "codes", value = "codes")
//            @RequestParam(value = "codes", required = true) String codes,
//            @ApiParam(name = "userCode", value = "userCode")
//            @RequestParam(value = "userCode", required = true) String userCode,
//            @ApiParam(name = "userName", value = "userName")
//            @RequestParam(value = "userName", required = true) String userName
//    ) {
//        try {
//            wxTemplateService.deleteWxTemplate(codes, userCode, userName);
//            return Envelop.getSuccess(WechatRequestMapping.WxTemplate.message_success_delete );
//        } catch (ApiException e) {
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    @GetMapping(value = WechatRequestMapping.WxTemplate.api_getById)
//    @ApiOperation(value = "根据code查找微信模版", notes = "根据code查找微信模版")
//    public Envelop findById(
//            @ApiParam(name = "id", value = "id")
//            @RequestParam(value = "id", required = true) String id
//    ) {
//        try {
//            return Envelop.getSuccess(WechatRequestMapping.WxTemplate.message_success_find, wxTemplateService.findById(id));
//        } catch (ApiException e) {
//            return Envelop.getError(e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    @RequestMapping(value = WechatRequestMapping.WxTemplate.api_getWxTemplates, method = RequestMethod.GET)
//    @ApiOperation(value = "获取微信模版列表(分页)")
//    public Envelop getWechats(
//            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,title,wechatCode,templateId,content,remark,status")
//            @RequestParam(value = "fields", required = false) String fields,
//            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
//            @RequestParam(value = "filters", required = false) String filters,
//            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+title,+createTime")
//            @RequestParam(value = "sorts", required = false) String sorts,
//            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
//            @RequestParam(value = "size", required = false) int size,
//            @ApiParam(name = "page", value = "页码", defaultValue = "1")
//            @RequestParam(value = "page", required = false) int page,
//            HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        if(StringUtils.isBlank(sorts)){
//            sorts = "-updateTime";
//        }
//
//        //得到微信列表数据
//        List<WxWechatDO> wechats = wechatService.search(fields, filters, sorts, page, size);
//        for(WxWechatDO wechat:wechats){
//            List<WxTemplateDO> wxTemplates = wxTemplateService.findByWxId(wechat.getId());
//            if (wxTemplates.size()>0){
//                wechat.setState("closed");
//            }else{
//                wechat.setState("open");
//            }
//        }
//        //获取总数
//        long count=wechatService.getCount(filters);
//        //封装头信息
//        pagedResponse(request, response, count, page, size);
//        //封装返回格式
//        List<WxWechatVO> mwechats = convertToModels(wechats, new ArrayList<>(wechats.size()), WxWechatVO.class, fields);
//        return Envelop.getSuccessListWithPage(WechatRequestMapping.WxMenu.message_success_find_functions,mwechats, page, size,count);
//
//    }
//
//
//    @GetMapping(value = WechatRequestMapping.WxTemplate.api_getWxTemplatesNoPage)
//    @ApiOperation(value = "获取微信模版列表(不分页)")
//    public Envelop getList(
//            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,title,wechatCode,templateId,content,remark,status")
//            @RequestParam(value = "fields", required = false) String fields,
//            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
//            @RequestParam(value = "filters", required = false) String filters,
//            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+title,+createTime")
//            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
//        //得到list数据
//        List<WxTemplateDO> list = wxTemplateService.search(fields,filters,sorts);
//        //封装返回格式
//        List<WxTemplateVO> mWxTemplateVOs = convertToModels(list, new ArrayList<>(list.size()), WxTemplateVO.class, fields);
//        return Envelop.getSuccessList(WechatRequestMapping.WxTemplate.message_success_find_functions,mWxTemplateVOs);
//    }
//
//    @GetMapping(value = WechatRequestMapping.WxTemplate.api_sendTemplateMessage)
//    @ApiOperation(value = "发送微信模板消息")
//    @ResponseBody
//    public Envelop sendTemplateMessage(
//            @ApiParam(name="openid",value="微信用户的openid")
//            @RequestParam String openid,
//            @ApiParam(name="templateCode",value = "模板code")
//            @RequestParam String templateCode,
//            @ApiParam(name="url",value="模板跳转链接")
//            @RequestParam(required = false) String url,
//            @ApiParam(name="appid",value="所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）")
//            @RequestParam(required = false) String appid,
//            @ApiParam(name="pagepath",value="所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）")
//            @RequestParam(required = false) String pagepath,
//            @ApiParam(name="data",value="json字符串")
//            @RequestParam String data
//    ){
//        try {
//            Miniprogram miniprogram = null;
//            if(StringUtils.isNotBlank(appid)&&StringUtils.isNotBlank(pagepath)){
//                miniprogram = new Miniprogram();
//                miniprogram.setAppid(appid);
//                miniprogram.setPagepath(pagepath);
//            }
//            JSONObject jsonObject = wxTemplateService.sendTemplateMessage(openid, templateCode, url, data, miniprogram);
//            String errcode = jsonObject.get("errcode").toString();
//            WechatResponse wechatResponse = new WechatResponse(Integer.valueOf(errcode));
//            String msg = wechatResponse.getMsg();
//            return Envelop.getSuccess("成功",msg);
//        }catch (Exception exception) {
//            return Envelop.getSuccess("error", exception);
//        }
//    }
}
