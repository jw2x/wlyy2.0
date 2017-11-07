package com.yihu.jw.es.config.model;

import io.searchbox.annotations.JestId;

/**
 * es保存model的公共父类
 * Created by chenweida on 2017/11/3.
 */
public class SaveModel {

    @JestId
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
