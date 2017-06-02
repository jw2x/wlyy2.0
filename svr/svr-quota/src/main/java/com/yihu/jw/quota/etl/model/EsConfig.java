package com.yihu.jw.quota.etl.model;

/**
 * Created by chenweida on 2017/6/2.
 */
public class EsConfig {

    private String host;//地址
    private String port;//端口
    private String clusterName;//集群的名字
    private String index;// 索引 es相当与数据库
    private String type;// 类型 es 相当于表

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
