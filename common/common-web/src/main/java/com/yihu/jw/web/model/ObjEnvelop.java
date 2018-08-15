package com.yihu.jw.web.model;

/**
 * Created by progr1mmer on 2018/8/15.
 */
public class ObjEnvelop<J> extends Envelop {

    private J obj;

    public J getObj() {
        return obj;
    }

    public void setObj(J obj) {
        this.obj = obj;
    }
}
