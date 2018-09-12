package com.yihu.wlyy.figure.label.extract;

import com.yihu.base.es.config.ElasticsearchUtil;
import com.yihu.wlyy.figure.label.model.DataModel;
import com.yihu.wlyy.figure.label.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author humingfen on 2018.04.19
 * ES数据抽取器
 */
@Component
public class ESExtracter implements Extracter{


    private Logger logger = LoggerFactory.getLogger(ESExtracter.class);

    @Autowired
    private ElasticsearchUtil elasticsearchUtil;

    @Override
    public  List<DataModel> extractData(String sql, String datasource){
        List<DataModel> saveModels = new ArrayList<>();
        List<Map<String,Object>> list = elasticsearchUtil.excuteDataModel(sql);
        for(Map<String,Object> tempMap:list){
            try {
                DataModel dataModel = (DataModel)BeanUtil.mapToBean(tempMap,DataModel.class);
               /* for (String s : tempMap.keySet()) {
                    String key = null;
                    Object value = tempMap.get(s);
                    if (s.startsWith("_")) {
                        continue;
                    }else if((s.equals("id") || s.equals("id1") || s.equals("id2")) && value != null) {
                        value = Integer.parseInt(String.valueOf(value));
                    }
                    key = "set" + UpFirstStr(s);

                    try {
                        if (value instanceof String) {
                            DataModel.class.getMethod(key, String.class).invoke(dataModel, value);
                        } else if (value instanceof Integer) {
                            DataModel.class.getMethod(key, Integer.class).invoke(dataModel, value);
                        } else if (value instanceof Double) {
                            DataModel.class.getMethod(key, Double.class).invoke(dataModel, value);
                        } else if (value instanceof java.util.Date) {
                            DataModel.class.getMethod(key, java.util.Date.class).invoke(dataModel, value);
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }*/
                saveModels.add(dataModel);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return saveModels;
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    private String UpFirstStr(String str) {
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
    }

}
