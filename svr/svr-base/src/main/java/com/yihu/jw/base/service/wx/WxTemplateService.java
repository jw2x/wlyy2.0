package com.yihu.jw.base.service.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.dao.wx.WxTemplateConfigDao;
import com.yihu.jw.base.dao.wx.WxTemplateDao;
import com.yihu.jw.entity.base.wx.WxTemplateConfigDO;
import com.yihu.jw.entity.base.wx.WxTemplateDO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.jw.rm.base.WechatRequestMapping;
import com.yihu.jw.util.wechat.WeixinMessagePushUtils;
import com.yihu.jw.util.wechat.wxhttp.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@Service
public class WxTemplateService {

    private Logger logger= LoggerFactory.getLogger(WxTemplateService.class);

    @Autowired
    private WxTemplateConfigDao wxTemplateConfigDao;
    @Autowired
    private WxTemplateDao wxTemplateDao;

    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    @Autowired
    private WeixinMessagePushUtils weixinMessagePushUtils;

    public String sendWeTempMesTest(String wechatId,String openid)throws Exception{
        WxTemplateConfigDO config = wxTemplateConfigDao.findByWechatIdAndTemplateNameAndSceneAndStatus(wechatId,"template_survey","test",1);
        config.setFirst(config.getFirst().replace("key1","小明"));
        config.setKeyword2("2018-08-21");
        weixinMessagePushUtils.putWxMsg(wxAccessTokenService.getWxAccessTokenById(wechatId).getAccessToken(),openid,config);
        return "success";
    }

    public Envelop getAllTemp(String wechatId){

        String url ="https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token="+wxAccessTokenService.getWxAccessTokenById(wechatId).getAccessToken();
        String result = HttpUtil.sendGet(url);

        JSONObject tempJsons = JSON.parseObject(result);
        //获取所有本地模板
        List<String> localTemps = findAllTempDos(wechatId);
        //解析公众号模板
        JSONArray temps = tempJsons.getJSONArray("template_list");

        List<WxTemplateDO> savelist = new ArrayList<>();

        if(temps!=null&&!temps.isEmpty()){
            for(int i=0;i<temps.size();i++){
                JSONObject tp = (JSONObject) temps.get(i);
                String tpid = tp.getString("template_id");
                if(!localTemps.contains(tpid)){
                    WxTemplateDO wxTemplateDO = new WxTemplateDO();
                    wxTemplateDO.setWechatId(wechatId);
                    wxTemplateDO.setTemplateId(tpid);
                    wxTemplateDO.setTitle(tp.getString("title"));
                    wxTemplateDO.setContent(tp.getString("content"));
                    wxTemplateDO.setStatus(1);
                    wxTemplateDO.setCreateTime(new Date());
                    savelist.add(wxTemplateDO);
                }
            }

            wxTemplateDao.save(savelist);
        }
        return Envelop.getSuccess(BaseRequestMapping.WeChat.api_success);

    }

    public List<String> findAllTempDos(String wechatId){
        List<WxTemplateDO> list =  wxTemplateDao.findByWxId(wechatId);
        List<String> rs = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for(WxTemplateDO wxTemplateDO:list){
                rs.add(wxTemplateDO.getTemplateId());
            }
        }
        return rs;
    }



//    public WxTemplateDO createWxTemplate(WxTemplateDO wxTemplate) {
//        if (StringUtils.isEmpty(wxTemplate.getTemplateId())) {
//            throw new ApiException(WechatRequestMapping.WxTemplate.message_fail_templateid_is_null, ExceptionCode.common_error_params_code);
//        }
//        String content = wxTemplate.getContent().replace(" ","");
//        if (StringUtils.isEmpty(content)) {
//            throw new ApiException(WechatRequestMapping.WxTemplate.message_fail_content_is_null, ExceptionCode.common_error_params_code);
//        }
//        if(!content.matches("\\{\\{.+\\.DATA\\}\\}")){//content必须还有 "{{.DATA}}"
//            throw new ApiException(WechatRequestMapping.WxTemplate.message_fail_content_format_is_not_right, ExceptionCode.common_error_params_code);
//        }
//        return wxTemplateDao.save(wxTemplate);
//    }
//
//    public WxTemplateDO updateWxTemplate(WxTemplateDO wxTemplate) {
//        if (StringUtils.isEmpty(wxTemplate.getTemplateId())) {
//            throw new ApiException(WechatRequestMapping.WxTemplate.message_fail_templateid_is_null, ExceptionCode.common_error_params_code);
//        }
//        String content = wxTemplate.getContent().replace(" ","");
//        if (StringUtils.isEmpty(content)) {
//            throw new ApiException(WechatRequestMapping.WxTemplate.message_fail_content_is_null, ExceptionCode.common_error_params_code);
//        }
//        String id = wxTemplate.getId();
//        if (StringUtils.isEmpty(id)) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_id_is_null, ExceptionCode.common_error_params_code);
//        }
//        WxTemplateDO wxTemplate1 = findById(id);
//        if(wxTemplate1==null){
//            throw new ApiException(WechatRequestMapping.WxTemplate.message_fail_template_is_no_exist, ExceptionCode.common_error_params_code);
//        }
//        wxTemplate.setCreateTime(wxTemplate1.getCreateTime());
//        wxTemplate.setUpdateTime(new Date());
//        if(!content.matches("\\{\\{.+\\.DATA\\}\\}")){//content必须还有 "{{.DATA}}"
//            throw new ApiException(WechatRequestMapping.WxTemplate.message_fail_content_format_is_not_right, ExceptionCode.common_error_params_code);
//        }
//        return wxTemplateDao.save(wxTemplate);
//    }
//
//    public void deleteWxTemplate(String codes, String userCode, String userName) {
//        if(!StringUtils.isEmpty(codes)) {
//            String[] codeArray = codes.split(",");
//            for (String code : codeArray) {
//                WxTemplateDO wxTemplate = wxTemplateDao.findById(code);
//                if (wxTemplate == null) {
//                    throw new ApiException(WechatRequestMapping.WxTemplate.message_fail_id_no_exist, ExceptionCode.common_error_params_code);
//                }
//                wxTemplate.setStatus(-1);
//                wxTemplate.setUpdateUser(userCode);
//                wxTemplate.setUpdateUserName(userName);
//                wxTemplateDao.save(wxTemplate);
//            }
//        }
//    }
//
//
//    public WxTemplateDO findById(String id) {
//        WxTemplateDO wxTemplate = wxTemplateDao.findById(id);
//        return wxTemplate;
//    }
//
//    public JSONObject sendTemplateMessage(String openid, String templateId, String url, String data,Miniprogram miniprogram) {
//        try {
//            //首先根据wechatTemplate获取微信模版
//            WxTemplateDO wxTemplate = findById(templateId);
//            if(wxTemplate==null){
//                throw new ApiException(WechatRequestMapping.WxTemplate.message_fail_template_is_no_exist, ExceptionCode.common_error_params_code);
//            }
//            String wechatCode =  wxTemplate.getWechatId();
//            String content = wxTemplate.getContent().replaceAll(" ", "");//{{result.DATA}}领奖金额:{{withdrawMoney.DATA}   }领奖  时间:{ {withdrawTime.DATA} }银行信息:{ {cardInfo.DATA} }到账时间:{{arrivedTime.DATA}}{{remark.DATA}}
//            String[] contentArray = content.split("\\{\\{");
//
//            //将result,withdrawMoney,withdrawTime,cardInfo,arrivedTime等字符串放入contentList中
//            List<String> contentList = new ArrayList<>();
//            for(int i=1;i<contentArray.length;i++){
//                contentList.add(contentArray[i].substring(0,contentArray[i].indexOf(".")));
//            }
//
//            ObjectMapper mapper = new ObjectMapper();
//            ////将data转为对象
//            Map<String, WechatTemplateDataDO> dataMap = mapper.readValue(data, new TypeReference<LinkedHashMap<String, WechatTemplateDataDO>>() {});
//            Map<String, WechatTemplateDataDO> newDataMap = new LinkedHashMap<String, WechatTemplateDataDO>();
//            int j = 0;
//            for (Map.Entry<String, WechatTemplateDataDO> entry : dataMap.entrySet()) {//(keyword1,WechatTemplateData)
//                String key = entry.getKey();//keyword1   转为result
//                newDataMap.put(contentList.get(j),entry.getValue());
//                j++;
//            }
//
//            //将数据封装在WechatTemplate对象中
//            WechatTemplateDO wechatTemplate = new WechatTemplateDO();
//            wechatTemplate.setTouser(openid);
//            wechatTemplate.setUrl(url);
//            wechatTemplate.setTemplate_id(wxTemplate.getTemplateId());
//            if(miniprogram!=null){
//                wechatTemplate.setMiniprogram(miniprogram);
//            }
//            wechatTemplate.setData(newDataMap);
//
//            String params = mapper.writeValueAsString(wechatTemplate);
//            logger.info("----------------------模版消息json字符串:"+params+"------------------");
//
//            WxAccessTokenDO wxAccessTokenByCode = wxAccessTokenService.getWxAccessTokenById(wechatCode);
//            String token = wxAccessTokenByCode.getAccessToken();
//            String token_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
//            String result = HttpUtil.sendPost(token_url, params);
//            logger.info("------------------------发送模板消息,微信返回结果:"+result+"-----------------------");
//
//            JSONObject jsonResult = new JSONObject(result);
//            return jsonResult;
//        } catch (Exception e) {
//            e.printStackTrace();
//            JSONObject jsonResult = new JSONObject();
//            return jsonResult;
//        }
//    }
//
//    public List<WxTemplateDO> findByWxId(String code) {
//        return wxTemplateDao.findByWxId(code);
//    }
}
