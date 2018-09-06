package com.yihu.wlyy.statistics.etl.filter;

import com.yihu.wlyy.statistics.vo.DataModel;
import com.yihu.wlyy.statistics.vo.ErrModel;
import com.yihu.wlyy.statistics.vo.FilterModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/6/1.
 */
@Component
@Scope("prototype")
public class FilterHelper {

    public FilterHelper() {
    }

    /**
     * 根据过滤规则过滤数据
     *
     * @param dataModels
     * @return
     */
    public FilterModel filter(List<DataModel> dataModels) {
        //成功的list
        List<DataModel> newList = new ArrayList<>();
        //失败的list
        List<ErrModel> errorList = new ArrayList<>();

        dataModels.stream().forEach(one -> {
            String returnStr=one.isNull();
            if (StringUtils.isEmpty(returnStr)) {
                newList.add(one);
            }else{
                //脏数据
                errorList.add(new ErrModel(one.getBusinessId(),returnStr));
            }
        });
        return new FilterModel(newList, errorList);
    }

}
