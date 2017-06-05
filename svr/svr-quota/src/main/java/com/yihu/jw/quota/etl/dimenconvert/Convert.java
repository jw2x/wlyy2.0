package com.yihu.jw.quota.etl.dimenconvert;

import com.yihu.jw.quota.vo.DataModel;

import java.util.List;

/**
 * Created by chenweida on 2017/6/5.
 */
public interface Convert {
    public List<DataModel> convert(List<DataModel> oneList, String slaveLevel);
}
