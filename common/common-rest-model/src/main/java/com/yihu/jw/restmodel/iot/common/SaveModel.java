package com.yihu.jw.restmodel.iot.common;

import io.searchbox.annotations.JestId;

/**
 * @author yeshijie on 2018/3/2.
 */
public class SaveModel {

    @JestId
    private String id;

    public SaveModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
