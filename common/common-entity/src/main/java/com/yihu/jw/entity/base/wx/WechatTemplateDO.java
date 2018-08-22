package com.yihu.jw.entity.base.wx;

import java.io.Serializable;
import java.util.Map;

/**
 * 微信消息模板推送内容
 * @author George
 */
public class WechatTemplateDO implements Serializable {

	private static final long serialVersionUID = 3877107913397496785L;

    private String touser;//用户openId

    private String template_id;//模板消息id

    private String url;//URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）

    private Miniprogram miniprogram; //小程序跳转

    private Map<String,WechatTemplateDataDO> data;//详细内容


    public Miniprogram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Miniprogram miniprogram) {
        this.miniprogram = miniprogram;
    }
    
    public String getTemplate_id() {
        return template_id;
    }
    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
    public String getTouser() {
        return touser;
    }
    public void setTouser(String touser) {
        this.touser = touser;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Map<String, WechatTemplateDataDO> getData() {
        return data;
    }
    public void setData(Map<String, WechatTemplateDataDO> data) {
        this.data = data;
    }
    
}
