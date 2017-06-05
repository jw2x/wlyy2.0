package com.yihu.jw.quota.etl.save.es;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by chenweida on 2017/6/5.
 */
@Component
public class ElasticFactory {
    /**
     * @param hostAddress "http://localhost:9200"
     * @return
     */
    public JestClient getClient(String hostAddress) {
        // Construct a new Jest client according to configuration via factory
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(hostAddress)
                .multiThreaded(true)
                .readTimeout(30)
                .build());
        return factory.getObject();
    }
}
