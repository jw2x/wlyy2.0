package com.yihu.base.es.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenweida on 2017/6/5.
 */
@Component
public class ElasticFactory {
    private static JestClientFactory factory = null;

    @Value("${es.host}")
    private String esHost;
    @Value("${es.port}")
    private String port;
    @Value("${es.tPort}")
    private String tPort;
    @Value("${es.clusterName}")
    private String clusterName;
//-----------------------------------jestClient----------------------------------------

    /**
     * @param "http://localhost:9200"
     * @return
     */
    public JestClient getJestClient() {
        if (factory == null) {
            //初始化链接
            init();
        }
        return factory.getObject();
    }

    /**
     * 初始化链接
     */
    public synchronized void init() {
        // Construct a new Jest client according to configuration via factory
        factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://" + esHost + ":" + port)
                .multiThreaded(true)
                .maxTotalConnection(50)// 最大链接
                .maxConnectionIdleTime(120, TimeUnit.SECONDS)//链接等待时间
                .connTimeout(60*1000)
               // .discoveryEnabled(true)
                .readTimeout(60*1000)//60秒
                .build());//得到链接
    }

    //-----------------------------------TransportClient----------------------------------------
    private Client transportClient;

    public Client getTransportClient() {
        try {
            initTranClient();
            return transportClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private synchronized void initTranClient() throws UnknownHostException {
        if (transportClient == null) {
            Settings settings = Settings.settingsBuilder()
                   // .put("client.transport.sniff", true)//开启嗅探功能
                    .put("cluster.name", StringUtils.isEmpty(clusterName) ? "jkzl" : clusterName)//默认集群名字是jkzl
                    .build();

            transportClient = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), Integer.valueOf(tPort)));
        }
    }
}
