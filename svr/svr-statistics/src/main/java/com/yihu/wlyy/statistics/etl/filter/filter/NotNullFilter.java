package com.yihu.wlyy.statistics.etl.filter.filter;

import com.yihu.wlyy.statistics.vo.FilterModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2017/6/1.
 */
@Component
@Scope("prototype")
public class NotNullFilter {
    private Logger logger = LoggerFactory.getLogger(NotNullFilter.class);


    /**
     * 不判断主维度的数据是否为空，只判断从维度额的数据是否为空
     *
     * @param filterModel
     * @return
     */
    public FilterModel filter(FilterModel filterModel) {
        try {

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }
}
