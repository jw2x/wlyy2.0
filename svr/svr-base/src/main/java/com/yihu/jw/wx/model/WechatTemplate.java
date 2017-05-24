package com.yihu.jw.wx.model;

import java.io.Serializable;
import java.util.Map;

/**
 * 微信消息模板推送内容
 * @author George
 */
public class WechatTemplate implements Serializable {

	private static final long serialVersionUID = 3877107913397496785L;

    /**
     * 用户openId
     */
    private String touser;

	/**
     * 模板消息id
     */
    private String template_id;

    /**
     * URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）
     */
    private String url;

    private Miniprogram miniprogram;

    public Miniprogram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Miniprogram miniprogram) {
        this.miniprogram = miniprogram;
    }

    /**
     * 详细内容
     */
    private Map<String,WechatTemplateData> data;
    
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
    public Map<String, WechatTemplateData> getData() {
        return data;
    }
    public void setData(Map<String, WechatTemplateData> data) {
        this.data = data;
    }
    
}
