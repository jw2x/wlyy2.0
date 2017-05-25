package com.yihu.jw.quota.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by chenweida on 2017/5/23.
 */
@Configuration
public class ElasticsearchConfig {
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        ElasticsearchTemplate elasticsearchTemplate= new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
        return elasticsearchTemplate;
    }

//    @Bean
//    public Client client() {
//        TransportClient client = TransportClient.builder().build();
//        try {
//            InetAddress[] i = InetAddress.getAllByName(hostname);
//            TransportAddress address = new InetSocketTransportAddress(i[0], port);
//
//            client.addTransportAddress(address);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return client;
//    }
}
