package com.yihu.jw.restmodel.base.saas;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 主题风格扩展表（type=2时存多图）
 * @author yeshijie on 2018/10/16.
 */
@ApiModel(value = "SaasThemeExtendVO", description = "主题风格扩展")
public class SaasThemeExtendVO extends UuidIdentityVO {

    @ApiModelProperty(value = "主题id", example = "theme_id")
    private String themeId;
    @ApiModelProperty(value = "图片地址", example = "图片地址")
    private String img;
    @ApiModelProperty(value = "url地址", example = "url地址")
    private String url;

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
