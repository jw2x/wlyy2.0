package com.yihu.wlyy.statistics.etl.save.es;

import com.alibaba.fastjson.JSONObject;
import com.yihu.wlyy.statistics.vo.SaveModel;
import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Index;
import io.searchbox.core.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

;

/**
 * Created by chenweida on 2017/6/2.
 */
@Component
@Scope("prototype")
public class ElastricSearchSave {
    @Value("${es.type}")
    private String esType;
    @Value("${es.index}")
    private String esIndex;

    private Logger logger = LoggerFactory.getLogger(ElastricSearchSave.class);
    @Autowired
    private ElasticFactory elasticFactory;

    public Boolean save(List<SaveModel> sms) {
        JestClient jestClient = null;
        try {
            //得到链接
            jestClient = elasticFactory.getJestClient();

            int success = 0;
            int error = 0;
            Bulk.Builder bulk = new Bulk.Builder().defaultIndex(esIndex).defaultType(esType);
            for (SaveModel obj : sms) {
                try {
                    obj.setCreateTime(new Date());
                    Index index = new Index.Builder(obj).build();
                    success++;
                    bulk.addAction(index);
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
        } finally {
            if (jestClient != null) {
                jestClient.shutdownClient();
            }
        }
        return null;
    }


    public Boolean update(List<SaveModel> sms) {
        JestClient jestClient = null;
        try {
            //得到链接
            jestClient = elasticFactory.getJestClient();

            int success = 0;
            int error = 0;
            boolean isSuccessed = true;
            Bulk.Builder bulk = new Bulk.Builder().defaultIndex(esIndex).defaultType(esType);
            for (SaveModel obj : sms) {
                try {
                    JSONObject jo = new JSONObject();
                    jo.put("doc", obj);
                    Update index = new Update.Builder(jo.toString()).index(esIndex).type(esType).id(obj.getId()).build();
                    bulk.addAction(index);
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
            jestClient.shutdownClient();
            return isSuccessed;
        } catch (Exception e) {
            logger.error(" update error ：" + e.getMessage());
        } finally {
            if (jestClient != null) {
                jestClient.shutdownClient();
            }
        }
        return null;
    }
}
