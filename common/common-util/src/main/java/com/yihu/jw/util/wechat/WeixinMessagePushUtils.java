package com.yihu.jw.util.wechat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.entity.base.wx.*;
import com.yihu.jw.util.wechat.wxhttp.HttpUtil;
import com.yihu.utils.network.HttpUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 微信模板消息工具类
 * 调用putWxMsg 发送模板消息
 * 微信文档地址
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1445241432
 * Created by Trick on 2018/8/21.
 */
@Component
public class WeixinMessagePushUtils {

    private static Logger logger = LoggerFactory.getLogger(WeixinMessagePushUtils.class);

    private static LinkedBlockingQueue<Map<String,Object>> queue = new LinkedBlockingQueue<Map<String,Object>>();

    public void putWxMsg(String accessToken,String opennid,WxTemplateConfigDO wxTemplateConfigDO){
        try {
            Map<String,Object> mes = new HashMap();
            if(StringUtils.isBlank(opennid)){
                logger.info("wechat queue put opennid is null ");
                return;
            }
            if(StringUtils.isBlank(accessToken)){
                logger.info("wechat queue put accessToken is null ");
                return;
            }
            mes.put("accessToken",accessToken);
            mes.put("openid",opennid);
            mes.put("wxTemplateConfig",wxTemplateConfigDO);
            queue.put(mes);
        }catch (Exception e){
            logger.info("wechat queue put error :",e);
        }
    }

    @PostConstruct
    private void run() {
        new Thread(new ConsumerTask()).start();
    }

    // 消费者
    class ConsumerTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    // 如果queue为空，则当前线程会堵塞，直到有新数据加入
                    Map<String,Object> mes = queue.take();
                    //发送微信模板消息
                    sendWeixinMessage(mes);
                }catch (Exception e){
                    logger.info("wechat ConsumerTask run:",e);
                }

            }
        }
    }

    /**
     * 发送微信模板消息
     * @param mes
     * @return
     */
    public boolean sendWeixinMessage(Map<String,Object> mes){
        WxTemplateConfigDO wxtemp = (WxTemplateConfigDO)mes.get("wxTemplateConfig");
        String token = (String)mes.get("accessToken");
        //发送微信模板地址
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
        //微信消息参数
        String params = getWxParam(mes);
        if(StringUtils.isBlank(params)){
            logger.info("wechat params is null");
            return false;
        }
        //发送微信模板消息
        String result = HttpUtil.sendPost(url, params);
        JSONObject jsonResult = new JSONObject(result);
        if (Integer.parseInt(jsonResult.get("errcode").toString()) == 0) {
            logger.info("wechat temp send success");
            return true;
        } else {
            logger.error("wechat temp send fail："+result);
            return false;
        }

    }

    /**
     * 构造微信模板参数
     *
     * @param mes
     * @return
     */
    public String getWxParam(Map<String,Object> mes){
        try{
            String toUser = (String)mes.get("openid");
            //微信模板消息实体
            WxTemplateConfigDO wxtemp = (WxTemplateConfigDO)mes.get("wxTemplateConfig");

            //创建微信模板实体，设置参数及内容
            WechatTemplateDO wechatTemp = new WechatTemplateDO();
            //接收者的openid
            wechatTemp.setTouser(toUser);
            //设置模板ID
            wechatTemp.setTemplate_id(wxtemp.getTemplateId());
            //设置跳转路径
            if(StringUtils.isNotBlank(wxtemp.getUrl())){
                wechatTemp.setUrl(wxtemp.getUrl());
            }
            //设置小程序跳转
            if(StringUtils.isNotBlank(wxtemp.getAppid())){
                Miniprogram miniprogram = new Miniprogram();
                miniprogram.setAppid(wxtemp.getAppid());
                miniprogram.setPagepath(wxtemp.getPagepath());
                wechatTemp.setMiniprogram(miniprogram);
            }
            //配置微信模板内容
            setWechatTemplateData(wechatTemp,wxtemp);
            ObjectMapper mapper = new ObjectMapper();
            String strJson = mapper.writeValueAsString(wechatTemp);
            logger.info("--------wechat param json message --------:" + strJson);
            return strJson;
        }catch (Exception e){
            logger.info("--------wechat param json message --------:" + e);
        }
        return "";
    }

    /**
     * 设置微信模板内容
     * @param wechatTemp 微信待发送模板
     * @param wxtemp 读取数据配置
     */
    public void setWechatTemplateData(WechatTemplateDO wechatTemp,WxTemplateConfigDO wxtemp){
        //设置模板内容

        Map<String,WechatTemplateDataDO> data = new HashedMap();

        //设置头部
        WechatTemplateDataDO first = new WechatTemplateDataDO();
        first.setValue(wxtemp.getFirst());
        first.setColor("#000000");
        data.put("first",first);
        //设置备注
        WechatTemplateDataDO remark = new WechatTemplateDataDO();
        remark.setValue(wxtemp.getRemark());
        remark.setColor("#000000");
        data.put("remark",remark);
        //配置keyword,微信最多可配置7个项目
        //设置keyword1
        if(StringUtils.isNotBlank(wxtemp.getKeyword1())){
            WechatTemplateDataDO keyword1 = new WechatTemplateDataDO();
            keyword1.setValue(wxtemp.getRemark());
            keyword1.setColor("#000000");
            data.put("keyword1",keyword1);
        }
        //设置keyword2
        if(StringUtils.isNotBlank(wxtemp.getKeyword2())){
            WechatTemplateDataDO keyword2 = new WechatTemplateDataDO();
            keyword2.setValue(wxtemp.getKeyword2());
            keyword2.setColor("#000000");
            data.put("keyword2",keyword2);
        }
        //设置keyword3
        if(StringUtils.isNotBlank(wxtemp.getKeyword3())){
            WechatTemplateDataDO keyword3 = new WechatTemplateDataDO();
            keyword3.setValue(wxtemp.getKeyword3());
            keyword3.setColor("#000000");
            data.put("keyword3",keyword3);
        }
        //设置keyword4
        if(StringUtils.isNotBlank(wxtemp.getKeyword4())){
            WechatTemplateDataDO keyword4 = new WechatTemplateDataDO();
            keyword4.setValue(wxtemp.getKeyword4());
            keyword4.setColor("#000000");
            data.put("keyword4",keyword4);
        }
        //设置keyword5
        if(StringUtils.isNotBlank(wxtemp.getKeyword5())){
            WechatTemplateDataDO keyword5 = new WechatTemplateDataDO();
            keyword5.setValue(wxtemp.getKeyword5());
            keyword5.setColor("#000000");
            data.put("keyword5",keyword5);
        }
        //设置keyword6
        if(StringUtils.isNotBlank(wxtemp.getKeyword6())){
            WechatTemplateDataDO keyword6 = new WechatTemplateDataDO();
            keyword6.setValue(wxtemp.getKeyword6());
            keyword6.setColor("#000000");
            data.put("keyword1",keyword6);
        }
        //设置keyword7
        if(StringUtils.isNotBlank(wxtemp.getKeyword7())){
            WechatTemplateDataDO keyword7 = new WechatTemplateDataDO();
            keyword7.setValue(wxtemp.getKeyword7());
            keyword7.setColor("#000000");
            data.put("keyword7",keyword7);
        }
        //设置微信内容，头部，备注，项目参数
        wechatTemp.setData(data);
    }


}
