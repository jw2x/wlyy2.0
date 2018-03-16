package com.yihu.iot.service.label;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.iot.datainput.util.ConstantUtils;
import com.yihu.iot.service.common.ElasticSearchQueryGenerator;
import com.yihu.jw.restmodel.iot.device.FigureLabelDataModelVO;
import io.searchbox.client.JestResult;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lith on 2018/03/16
 */
@Service
public class FigureLabelSerachService implements Serializable {

    private Logger logger = LoggerFactory.getLogger(FigureLabelSerachService.class);

    @Autowired
    private ElastricSearchHelper elastricSearchHelper;

    @Autowired
    private ElasticSearchQueryGenerator elasticSearchQueryGenerator;

    /**
     *根据idcard查询标签
     * @param json
     * @return
     */
    public List<FigureLabelDataModelVO> getFigureLabelByIdcard(String json){
        List<FigureLabelDataModelVO> list = new ArrayList<>();
        if(!json.contains("idcard")){
            logger.error("invalid elasticserach query condition,no parameter [idcard]!");
            return list;
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        //elasticsearch 默认查询上限为10000条，但是我们根据条件查询，不知道数据会有几条，此处给最大值
        jsonObject.put("size",10000);
        SearchSourceBuilder queryStr = elasticSearchQueryGenerator.getQueryBuilder("",jsonObject.toJSONString());
        JestResult jestResult = elastricSearchHelper.search(ConstantUtils.figureLabelIndex,ConstantUtils.figureLabelType,queryStr.toString());
        list = jestResult.getSourceAsObjectList(FigureLabelDataModelVO.class);
        return list;
    }

    /**
     * 根据标签类型和标签code查询标签
     * @param json
     * @return
     */
    public List<FigureLabelDataModelVO> getFigureLabelByLabel(String json){
        List<FigureLabelDataModelVO> list = new ArrayList<>();
        if(!json.contains("labelType") || !json.contains("labelCode")){
            logger.error("invalid elasticserach query condition,no parameter [labelType] or [labelCode]!");
            return list;
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        //elasticsearch 默认查询上限为10000条，但是我们根据条件查询，不知道数据会有几条，此处给最大值
        jsonObject.put("size",10000);
        SearchSourceBuilder queryStr = elasticSearchQueryGenerator.getQueryBuilder("",jsonObject.toJSONString());
        JestResult jestResult = elastricSearchHelper.search(ConstantUtils.figureLabelIndex,ConstantUtils.figureLabelType,queryStr.toString());
        list = jestResult.getSourceAsObjectList(FigureLabelDataModelVO.class);
        return list;
    }


}
