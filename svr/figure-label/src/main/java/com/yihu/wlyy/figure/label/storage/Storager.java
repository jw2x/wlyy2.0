package com.yihu.wlyy.figure.label.storage;

import com.yihu.wlyy.figure.label.model.SaveModel;

import java.util.List;

/**
 * @author lith on 2018.03.14 -- Created by chenweida on 2018/3/7.
 * 存储器
 */
public interface Storager {

    void save(List<SaveModel> modelList);
}
