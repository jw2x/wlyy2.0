package com.yihu.wlyy.figure.label.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.wlyy.figure.label.model.SaveModel;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import com.yihu.wlyy.figure.label.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *@author lith on 2018.03.23
 */
@Component
public class Store2ES implements Storager {

    private Logger logger = LoggerFactory.getLogger(Store2ES.class);

    @Autowired
    private ElastricSearchHelper elastricSearchHelper;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void save(List<SaveModel> modelList) {
        List<String> list = new ArrayList<>();
        int saveCount = 0;
        long start = System.currentTimeMillis();
        String logtitle = "loop(save) data,total size:" + modelList.size();
        TimeUtil.start(logger,logtitle,start);
        for(SaveModel saveModel:modelList){
            try {
                String str =  objectMapper.writeValueAsString(saveModel);
                list.add(str);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //防止内存溢出，一次性存储10W条
            if(list.size() >= 100000){
                elastricSearchHelper.saveBulkWithCustomId(ConstantUtil.figure_label_es_index, ConstantUtil.figure_label_es_type, list,"id");
                list.clear();
                saveCount ++;
            }else if(modelList.size() - saveCount * 100000 == list.size()){
                //剩余的零头
                elastricSearchHelper.saveBulkWithCustomId(ConstantUtil.figure_label_es_index, ConstantUtil.figure_label_es_type, list,"id");
            }
        }
        long finish = System.currentTimeMillis();
        TimeUtil.finish(logger,"loop(save) data",start,finish);
    }
}
