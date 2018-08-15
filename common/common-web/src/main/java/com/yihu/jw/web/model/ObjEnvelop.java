package com.yihu.jw.web.model;

/**
 * Created by progr1mmer on 2018/8/15.
 */
public class ObjEnvelop<T> extends BaseEnvelop {

    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
