package com.yihu.iot.datainput.service;

import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.base.hbase.HBaseHelper;
import com.yihu.iot.datainput.util.ConstantUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSearchService {

    private Logger logger = LoggerFactory.getLogger(DataSearchService.class);

    @Autowired
    private ElastricSearchHelper elastricSearchHelper;

    @Autowired
    private HBaseHelper hBaseHelper;

    /**
     * 获取数据
     * @param json
     * @return
     */
    public String getData(String json){
        String result = elastricSearchHelper.search(ConstantUtils.esIndex,ConstantUtils.esType,json);
        if(null == result){
            return "no data";
        }
        return result;
    }

}
