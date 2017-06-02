package com.yihu.jw.quota.etl.save.es;

import com.yihu.jw.quota.etl.model.EsConfig;
import com.yihu.jw.quota.vo.SaveModel;
import net.sf.json.JSONObject;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Scope;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/2.
 */
@Component
@Scope("prototype")
public class ElastricSearchSave {

    private ElasticsearchTemplate elasticsearchTemplate;
    private EsConfig esConfig;

    public void save(List<SaveModel> sms, String jsonConfig) {
        //初始化链接
        initTemplate(jsonConfig);
        //判断索引是否存在
//        if (!elasticsearchTemplate.indexExists(esConfig.getIndex())) {
//            //不存在就新增缩影
//            Map<String, String> setting = new HashMap<>();
//            setting.put("number_of_replicas", "2");// 设置备份
//            setting.put("number_of_shards", "6");//设置分片
//            elasticsearchTemplate.createIndex(esConfig.getIndex(), setting);
//        }
//        //判断type是否存在
//        if(!elasticsearchTemplate.typeExists(esConfig.getIndex(),esConfig.getType())){
//
//        }

        List<IndexQuery> queries = new ArrayList<IndexQuery>();
        for (SaveModel saveModel : sms) {
            IndexQuery indexQuery = new IndexQueryBuilder().withObject(saveModel).build();
            queries.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(queries);
    }

    private void initTemplate(String jsonConfig) {
        try {
            esConfig = (EsConfig) JSONObject.toBean(JSONObject.fromObject(jsonConfig), EsConfig.class);
            Settings esSettings = Settings.settingsBuilder()
                    .put("cluster.name", esConfig.getClusterName())
                    .build();
            Client client = TransportClient.builder()
                    .settings(esSettings)
                    .build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esConfig.getHost()), Integer.valueOf(esConfig.getPort())));

            elasticsearchTemplate = new ElasticsearchTemplate(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
