package com.yihu.jw.entity.base.saas;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 租户主题风格
 * @author yeshijie on 2018/10/16.
 */
@Entity
@Table(name = "base_saas_theme")
public class SaasThemeDO extends UuidIdentityEntity{

    private String saasId;//saas_id
    private String type;//组件类型（1单张图文广告，2多图图文广告，3标题，4中心指标1，5中心指标2）
    private String img;//图片url
    private String title;//标题
    private String content;//内容/解释说明
    private String url;//跳转url
    private String font;//字体大小
    private Integer sort;//排序

    List<SaasThemeExtendDO> themeExtendList;//主题扩展

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "font")
    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Transient
    public List<SaasThemeExtendDO> getThemeExtendList() {
        return themeExtendList;
    }

    public void setThemeExtendList(List<SaasThemeExtendDO> themeExtendList) {
        this.themeExtendList = themeExtendList;
    }
}
