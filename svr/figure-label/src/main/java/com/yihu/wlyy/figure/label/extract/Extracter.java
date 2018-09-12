package com.yihu.wlyy.figure.label.extract;

import com.yihu.wlyy.figure.label.model.DataModel;

import java.util.List;

/**
 * @author lith on 2018.03.14 -- Created by chenweida on 2018/3/7.
 * 数据抽取器
 */
public interface Extracter {
    /**
     * 数据抽取
     *
     * @return
     */
//    List<ExtractModel> extract();

    List<DataModel> extractData(String sql,String datasource);


}
