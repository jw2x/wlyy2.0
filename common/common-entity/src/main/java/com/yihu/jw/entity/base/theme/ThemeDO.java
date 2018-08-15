package com.yihu.jw.entity.base.theme;

import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_theme")
public class ThemeDO extends UuidIdentityEntityWithOperation {

    private String backgroundColor; //主体背景颜色
    private String fontColor; //主体字体颜色
    private String fontFamily; //主体字体系列
    private Integer fontSize; //主体字体大小

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }
}
