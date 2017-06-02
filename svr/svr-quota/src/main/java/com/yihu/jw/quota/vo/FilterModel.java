package com.yihu.jw.quota.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
public class FilterModel {
    private Object data;//可能是list 也可能是map
    private List<ErrModel> ErrorModels =new ArrayList<>();

    public FilterModel(Object data) {
        this.data = data;
    }
    public FilterModel() {
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<ErrModel> getErrorModels() {
        return ErrorModels;
    }

    public void setErrorModels(List<ErrModel> ErrorModels) {
        this.ErrorModels = ErrorModels;
    }
}
