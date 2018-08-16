package com.yihu.jw.web.model;

import java.util.HashMap;

/**
 * Created by progr1mmer on 2018/8/15.
 */
public class ObjEnvelop<J> extends Envelop {

    private J data = (J) new HashMap<>(0);

    public J getData() {
        return data;
    }

    public void setData(J data) {
        this.data = data;
    }
}
