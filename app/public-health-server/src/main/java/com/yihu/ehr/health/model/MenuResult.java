package com.yihu.ehr.health.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxw on 2017/11/3.
 */
public class MenuResult {
    private int id;
    private String name;
    private String url;
    private List<MenuResult> children = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuResult> getChildren() {
        return children;
    }

    public void setChildren(List<MenuResult> children) {
        this.children = children;
    }
}
