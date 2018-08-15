package com.yihu.jw.web.model;

import java.util.List;

/**
 * Created by progr1mmer on 2018/8/15.
 */
public class ListEnvelop<T> extends Envelop {

    private List<T> detailModelList;

    public List<T> getDetailModelList() {
        return detailModelList;
    }

    public void setDetailModelList(List<T> detailModelList) {
        this.detailModelList = detailModelList;
    }

}
