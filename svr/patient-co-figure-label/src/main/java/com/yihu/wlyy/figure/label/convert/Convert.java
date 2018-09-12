package com.yihu.wlyy.figure.label.convert;

import com.yihu.figure_label.entity.FlLabelDict;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.model.SaveModel;

import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2018/3/7.
 * 数据转换
 */
public interface Convert {

    List<SaveModel> convert(List<DataModel> modelList, List<Map<String, Object>> hbaseDatas, List<FlLabelDict> flLabelDictList, String sourceType, String source);
}
