package com.yihu.jw.es.config;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.es.config.model.SaveModel;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

;

/**
 * Created by chenweida on 2017/6/2.
 */
@Component
@Scope("prototype")
public class ElastricSearchSave {

    private Logger logger = LoggerFactory.getLogger(ElastricSearchSave.class);
    @Autowired
    private ElasticFactory elasticFactory;

    public Boolean save(String index, String type, List<SaveModel> sms) {
        try {
            //得到链接
            JestClient jestClient = elasticFactory.getJestClient();

            int success = 0;
            int error = 0;
            Bulk.Builder bulk = new Bulk.Builder().defaultIndex(index).defaultType(type);
            for (SaveModel obj : sms) {
                try {
                    Index indexObj = new Index.Builder(obj).build();
                    success++;
                    bulk.addAction(indexObj);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    error++;
                }
            }
            BulkResult br = jestClient.execute(bulk.build());
            logger.info("save flag:" + br.isSucceeded());
            logger.info("save success:" + success);
            logger.info("save error:" + error);
            return br.isSucceeded();
        } catch (Exception e) {
            logger.error(" save error ：" + e.getMessage());
        }
        return null;
    }


    public Boolean update(String index, String type, List<SaveModel> sms) {
        try {
            //得到链接
            JestClient jestClient = elasticFactory.getJestClient();

            int success = 0;
            int error = 0;
            boolean isSuccessed = true;
            Bulk.Builder bulk = new Bulk.Builder().defaultIndex(index).defaultType(type);
            for (SaveModel obj : sms) {
                try {
                    JSONObject jo = new JSONObject();
                    jo.put("doc", obj);
                    Update indexObj = new Update.Builder(jo.toString()).index(index).type(type).id(obj.getId()).build();
                    bulk.addAction(indexObj);
                    success++;
                } catch (Exception e) {
                    error++;
                    isSuccessed = false;
                }
            }

            BulkResult br = jestClient.execute(bulk.build());
            logger.info("update flag:" + br.isSucceeded());
            logger.info("update success:" + success);
            logger.info("update error:" + error);
            return isSuccessed;
        } catch (Exception e) {
            logger.error(" update error ：" + e.getMessage());
        }
        return null;
    }

    /**
     * 删除
     */
    private void deleteData(String index, String type, List<SaveModel> saveModels) {
        try {
            JestClient jestClient = elasticFactory.getJestClient();

            //根据id批量删除
            Bulk.Builder bulk = new Bulk.Builder().defaultIndex(index).defaultType(type);
            for (SaveModel obj : saveModels) {
                Delete indexObj = new Delete.Builder(obj.getId()).build();
                bulk.addAction(indexObj);
            }
            BulkResult br = jestClient.execute(bulk.build());

            logger.info("delete data count:" + saveModels.size());
            logger.info("delete flag:" + br.isSucceeded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
