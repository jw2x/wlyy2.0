package com.yihu.jw.restmodel.base.saas;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 租户主题风格
 * @author yeshijie on 2018/10/16.
 */
@ApiModel(value = "SaasThemeVO", description = "租户主题风格")
public class SaasThemeVO extends UuidIdentityVO{

    @ApiModelProperty(value = "saas_id", example = "saas_id")
    private String saasId;
    @ApiModelProperty(value = "组件类型", example = "（1单张图文广告，2多图图文广告，3标题，4中心指标1，5中心指标2）")
    private String type;
    @ApiModelProperty(value = "图片url", example = "图片url")
    private String img;
    @ApiModelProperty(value = "标题", example = "标题")
    private String title;
    @ApiModelProperty(value = "内容/解释说明", example = "解释说明")
    private String content;
    @ApiModelProperty(value = "跳转url", example = "跳转url")
    private String url;
    @ApiModelProperty(value = "字体大小", example = "12")
    private String font;
    @ApiModelProperty(value = "排序", example = "1")
    private Integer sort;

    @ApiModelProperty(value = "type=2,才有值", example = "1")
    List<SaasThemeExtendVO> themeExtendList;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<SaasThemeExtendVO> getThemeExtendList() {
        return themeExtendList;
    }

    public void setThemeExtendList(List<SaasThemeExtendVO> themeExtendList) {
        this.themeExtendList = themeExtendList;
    }
}
