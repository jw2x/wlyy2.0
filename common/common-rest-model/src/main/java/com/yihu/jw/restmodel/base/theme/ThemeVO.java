package com.yihu.jw.restmodel.base.theme;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VO - 主题风格
 * Created by progr1mmer on 2018/8/14.
 */
@ApiModel(value = "ThemeVO", description = "主题风格")
public class ThemeVO extends IntegerIdentityVO {

    //主题风格
    @ApiModelProperty(value = "主题风格", example = "dark")
    private String style;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
