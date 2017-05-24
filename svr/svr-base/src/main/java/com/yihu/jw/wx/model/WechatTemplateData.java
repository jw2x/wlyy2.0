package com.yihu.jw.wx.model;


import com.yihu.jw.base.model.base.IdEntity;

/**
 * 微信消息模板
 * @author George
 */
public class WechatTemplateData extends IdEntity {
	private static final long serialVersionUID = -7399054549159698617L;
	private String value; 
    private String color;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
