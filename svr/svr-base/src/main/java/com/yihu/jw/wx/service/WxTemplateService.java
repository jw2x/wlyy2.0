package com.yihu.jw.wx.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.util.HttpUtil;
import com.yihu.jw.wx.dao.WxTemplateDao;
import com.yihu.jw.wx.model.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@Service
public class WxTemplateService extends BaseJpaService<WxTemplate, WxTemplateDao> {

    @Autowired
    private WxTemplateDao wxTemplateDao;

    @Autowired
    private WxAccessTokenService wxAccessTokenService;


    public WxTemplate createWxTemplate(WxTemplate wxTemplate) {
        if (StringUtils.isEmpty(wxTemplate.getCode())) {
            throw new ApiException(WxContants.WxTemplate.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxTemplate.getWechatCode())) {
            throw new ApiException(WxContants.WxTemplate.message_fail_wechatCode_is_null, CommonContants.common_error_params_code);
        }
        return wxTemplateDao.save(wxTemplate);
    }

    public WxTemplate updateWxTemplate(WxTemplate wxTemplate) {
        if (StringUtils.isEmpty(wxTemplate.getCode())) {
            throw new ApiException(WxContants.WxTemplate.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxTemplate.getWechatCode())) {
            throw new ApiException(WxContants.WxTemplate.message_fail_wechatCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxTemplate.getId())) {
            throw new ApiException(WxContants.WxTemplate.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        return wxTemplateDao.save(wxTemplate);

    }

    public void deleteWxTemplate(String code) {
        WxTemplate wxTemplate = wxTemplateDao.findByCode(code);
        if (wxTemplate == null) {
            throw new ApiException(WxContants.WxTemplate.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        wxTemplate.setStatus(-1);
        wxTemplateDao.save(wxTemplate);
    }

    public WxTemplate findByCode(String code) {
        WxTemplate wxTemplate = wxTemplateDao.findByCode(code);
        if (wxTemplate == null) {
            throw new ApiException(WxContants.WxTemplate.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return wxTemplate;
    }

    public JSONObject sendTemplateMessage(String openid, String templateCode, String url, String data,Miniprogram miniprogram) {
        try {
            //首先根据wechatTemplate获取微信模版
            WxTemplate wxTemplate = findByCode(templateCode);
            String wechatCode =  wxTemplate.getWechatCode();
            String content = wxTemplate.getContent().replaceAll(" ", "");//{{result.DATA}}领奖金额:{{withdrawMoney.DATA}   }领奖  时间:{ {withdrawTime.DATA} }银行信息:{ {cardInfo.DATA} }到账时间:{{arrivedTime.DATA}}{{remark.DATA}}
            String[] contentArray = content.split("\\{\\{");

            //将result,withdrawMoney,withdrawTime,cardInfo,arrivedTime等字符串放入contentList中
            List<String> contentList = new ArrayList<>();
            for(int i=1;i<contentArray.length;i++){
                contentList.add(contentArray[i].substring(0,contentArray[i].indexOf(".")));
            }

            ObjectMapper mapper = new ObjectMapper();
            ////将data转为对象
            Map<String, WechatTemplateData> dataMap = mapper.readValue(data, new TypeReference<HashMap<String, WechatTemplateData>>() {});
            Map<String, WechatTemplateData> newDataMap = new HashMap<String, WechatTemplateData>();
            int j = 0;
            for (Map.Entry<String, WechatTemplateData> entry : dataMap.entrySet()) {//(keyword1,WechatTemplateData)
                String key = entry.getKey();//keyword1   转为result
                newDataMap.put(contentList.get(j),entry.getValue());
                j++;
            }

            //将数据封装在WechatTemplate对象中
            WechatTemplate wechatTemplate = new WechatTemplate();
            wechatTemplate.setTouser(openid);
            wechatTemplate.setUrl(url);
            wechatTemplate.setTemplate_id(wxTemplate.getTemplateId());
            if(miniprogram!=null){
                wechatTemplate.setMiniprogram(miniprogram);
            }
            wechatTemplate.setData(newDataMap);

            String params = mapper.writeValueAsString(wechatTemplate);

            WxAccessToken wxAccessTokenByCode = wxAccessTokenService.getWxAccessTokenByCode(wechatCode);
            String token = wxAccessTokenByCode.getAccessToken();
            String token_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
            String result = HttpUtil.sendPost(token_url, params);
            JSONObject jsonResult = new JSONObject(result);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonResult = new JSONObject(e);
            return jsonResult;
        }
    }

    public static void main(String[] args) {
        String a = "{{result.DATA}}领奖金额:{{withdrawMoney.DATA}   }领奖  时间:{ {withdrawTime.DATA} }银行信息:{ {cardInfo.DATA} }到账时间:{{arrivedTime.DATA}}{{remark.DATA}}".replaceAll(" ", "");
        System.out.println(a);
        String[] strings = a.split("\\{\\{");
        List<String> contentList = new ArrayList<>();
        for(int i=1;i<strings.length;i++){
            System.out.println(strings[i].substring(0,strings[i].indexOf(".")));
            contentList.add(strings[i].substring(0,strings[i].indexOf(".")));
        }

    }
}
