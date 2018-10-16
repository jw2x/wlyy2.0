package com.yihu.jw.entity.base.saas;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 主题风格扩展表（type=2时存多图）
 * @author yeshijie on 2018/10/16.
 */
@Entity
@Table(name = "base_saas_theme_extend")
public class SaasThemeExtendDO extends UuidIdentityEntity {

    private String themeId;//theme_id
    private String img;//图片地址
    private String url;//url地址

    @Column(name = "theme_id")
    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
