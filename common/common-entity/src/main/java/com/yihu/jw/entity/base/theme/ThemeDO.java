package com.yihu.jw.entity.base.theme;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_theme")
public class ThemeDO extends IntegerIdentityEntity {

    //主题风格
    private String style;

    @Column(name = "style", nullable = false)
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
