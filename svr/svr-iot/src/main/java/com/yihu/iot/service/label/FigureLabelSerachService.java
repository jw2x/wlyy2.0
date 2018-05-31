package com.yihu.iot.service.label;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.base.es.config.ElasticFactory;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.iot.datainput.util.ConstantUtils;
import com.yihu.iot.service.common.ElasticSearchQueryGenerator;
import com.yihu.jw.restmodel.iot.device.FigureLabelDataModelVO;
import com.yihu.jw.restmodel.iot.device.LocationDataVO;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
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
    private ElasticFactory elasticFactory;
    @Autowired
    private ElasticSearchQueryGenerator elasticSearchQueryGenerator;

    /**
     * 查询标签
     * @param list
     */
    public void getFigureLabelByList(List<LocationDataVO> list){
        JestClient jestClient = null;
        try {
            jestClient = this.elasticFactory.getJestClient();
            for (LocationDataVO one:list) {
                JSONArray jsonArray = new JSONArray();
                JSONObject json = new JSONObject();
                field("and","idcard","=",one.getIdCard(),jsonArray);
                field("and","labelType","=","3",jsonArray);
                field("and","dictCode","=","people_category_dict",jsonArray);
                json.put("filter",jsonArray);

                boolean isTNB = false;//是否糖尿病
                boolean isGXY = false;//是否高血压
                Search e = (((io.searchbox.core.Search.Builder)(new io.searchbox.core.Search.Builder(json.toJSONString())).addIndex(ConstantUtils.figureLabelIndex)).addType(ConstantUtils.figureLabelType)).build();
                JestResult jestResult = jestClient.execute(e);
                List<FigureLabelDataModelVO> labelList = jestResult.getSourceAsObjectList(FigureLabelDataModelVO.class);
                for (FigureLabelDataModelVO vo:labelList) {
                    if("1".equals(vo.getLabelCode())){
                        isGXY = true;
                    }
                    if("2".equals(vo.getLabelCode())){
                        isTNB = true;
                    }
                }
                //0无，1高血压，2糖尿病，3高血压糖尿病都有
                if(isTNB&&isGXY){
                    one.setLabel("3");
                }else if(isTNB){
                    one.setLabel("2");
                }else if(isGXY){
                    one.setLabel("1");
                }else {
                    one.setLabel("0");
                }
                this.logger.info("search data count:" + labelList.size());
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            if(jestClient != null) {
                jestClient.shutdownClient();
            }
        }
    }

    /**
     * 添加条件
     * 参数格式：[{"andOr":"and|or","condition":">|=|<|>=|<=|?","field":"<field>","value":"<value>"},<{...}>]
     * @param andOr
     * @param field
     * @param condition
     * @param value
     * @param jsonArray
     */
    private void field(String andOr,String field,String condition,String value,JSONArray jsonArray){
        JSONObject json = new JSONObject();
        json.put("andOr",andOr);
        json.put("field",field);
        json.put("condition",condition);
        json.put("value",value);
        jsonArray.add(json);
    }


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
