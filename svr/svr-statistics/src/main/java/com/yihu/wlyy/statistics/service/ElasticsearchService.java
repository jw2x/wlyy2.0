package com.yihu.wlyy.statistics.service;

import com.yihu.wlyy.statistics.util.ElasticsearchUtil;
import com.yihu.wlyy.statistics.vo.SaveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenweida on 2017/7/14.
 */
@Service
public class ElasticsearchService {
    @Autowired
    private ElasticsearchUtil elasticsearchUtil;

    /**
     * 查询一级维度的指标
     *
     * @param quotaCode 指标id
     * @param timeLevel 1增量 2到达量
     * @param areaLevel 1 省 2 市 3 区县 4 机构 5团队
     * @param code      如果areaLevel是3就是区的code 如果是4就是机构的code
     * @param quotaDate 时间 yyyy-MM-dd
     * @return
     */
    public SaveModel findDimension1Quota(String quotaCode, String timeLevel, String areaLevel, String code, String quotaDate) {

        return null;
    }

    public SaveModel findDimension1QuotaList(String quotaCode, String timelevel, String parentAreaLevel, String code, String quotaDate, String chlAreaLevel) {

        return null;
    }


    public List<SaveModel> excute(String sql) {
        return elasticsearchUtil.excute(sql);
    }

}
