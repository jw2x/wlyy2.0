package com.yihu.jw.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by progr1mmer on 2018/8/15.
 */
public class ListEnvelop<T> extends Envelop {

    private List<T> contents = new ArrayList<>(0);

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }
}
