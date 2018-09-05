package com.yihu.wlyy.statistics.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
public class FilterModel {
    private List<DataModel>  data;//可能是list 也可能是map
    private List<ErrModel> ErrorModels =new ArrayList<>();//

    public FilterModel(List<DataModel> data, List<ErrModel> errorModels) {
        this.data = data;
        ErrorModels = errorModels;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }

    public List<ErrModel> getErrorModels() {
        return ErrorModels;
    }

    public void setErrorModels(List<ErrModel> ErrorModels) {
        this.ErrorModels = ErrorModels;
    }

}
