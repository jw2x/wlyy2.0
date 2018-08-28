package com.yihu.jw.endpoint;

import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.LeaseInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.eureka.EurekaServerContext;
import com.netflix.eureka.cluster.PeerEurekaNode;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by chenweida on 2018/5/5 0005.
 */
@RestController
@RequestMapping(value = "/discovery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DiscoveryEndpoint {
    @Autowired
    private EurekaServerContext eurekaServerContext;

    /**
     * 获取发现服务的信息
     * @return
     */
    @GetMapping("/eurukaMessage")
    public String eurukaMessage() {

        JSONObject jo = new JSONObject();
        jo.put("euruka服务器数", eurekaServerContext.getPeerEurekaNodes().getPeerEurekaNodes().size());
        EurekaInstanceConfig eurekaInstanceConfig = eurekaServerContext.getApplicationInfoManager().getEurekaInstanceConfig();
        jo.put("appGroupName", eurekaInstanceConfig.getAppGroupName());
        jo.put("getAppname", eurekaInstanceConfig.getAppname());
        jo.put("getASGName", eurekaInstanceConfig.getASGName());
        jo.put("DataCenterInfo-name", eurekaInstanceConfig.getDataCenterInfo().getName());
        jo.put("getDefaultAddressResolutionOrder", eurekaInstanceConfig.getDefaultAddressResolutionOrder());
        jo.put("getHealthCheckUrl", eurekaInstanceConfig.getHealthCheckUrl());
        jo.put("getHealthCheckUrlPath", eurekaInstanceConfig.getHealthCheckUrlPath());
        jo.put("getHomePageUrl", eurekaInstanceConfig.getHomePageUrl());
        jo.put("getHomePageUrlPath", eurekaInstanceConfig.getHomePageUrlPath());
        jo.put("getHostName", eurekaInstanceConfig.getHostName(true));
        jo.put("getInstanceId", eurekaInstanceConfig.getInstanceId());
        jo.put("getIpAddress", eurekaInstanceConfig.getIpAddress());
        jo.put("getLeaseExpirationDurationInSeconds", eurekaInstanceConfig.getLeaseExpirationDurationInSeconds());
        jo.put("getLeaseRenewalIntervalInSeconds", eurekaInstanceConfig.getLeaseRenewalIntervalInSeconds());
        jo.put("getMetadataMap", eurekaInstanceConfig.getMetadataMap());
        jo.put("getNamespace", eurekaInstanceConfig.getNamespace());
        jo.put("getNonSecurePort", eurekaInstanceConfig.getNonSecurePort());
        jo.put("getSecureHealthCheckUrl", eurekaInstanceConfig.getSecureHealthCheckUrl());
        jo.put("getSecurePort", eurekaInstanceConfig.getSecurePort());
        jo.put("getSecurePortEnabled", eurekaInstanceConfig.getSecurePortEnabled());
        jo.put("getSecureVirtualHostName", eurekaInstanceConfig.getSecureVirtualHostName());
        jo.put("getSecureHealthCheckUrl", eurekaInstanceConfig.getSecureHealthCheckUrl());

        JSONArray ja = new JSONArray();
        for (int i = 0; i < eurekaServerContext.getPeerEurekaNodes().getPeerNodesView().size(); i++) {
            PeerEurekaNode peerEurekaNode = eurekaServerContext.getPeerEurekaNodes().getPeerNodesView().get(i);
            JSONObject temp = new JSONObject();
            temp.put("服务器地址(serviceUrl)", peerEurekaNode.getServiceUrl());
            temp.put("batcherName", peerEurekaNode.getBatcherName());
            ja.add(temp);
        }
        jo.put("euruka服务器资料", ja);
        return jo.toString();
    }

    /**
     * 获取注册服务的信息
     * @return
     */
    @GetMapping("/eurukaApplicationMessage")
    public String eurukaApplicationMessage() {
        PeerAwareInstanceRegistry peerAwareInstanceRegistry = eurekaServerContext.getRegistry();
        JSONObject jo = new JSONObject();
        jo.put("注册到发现服务的应用数目:", peerAwareInstanceRegistry.getApplications().size());
        JSONArray ja = new JSONArray();
        for (int i = 0; i < peerAwareInstanceRegistry.getApplications().getRegisteredApplications().size(); i++) {
            Application application = peerAwareInstanceRegistry.getApplications().getRegisteredApplications().get(i);
            JSONObject temp = new JSONObject();
            temp.put("名称", application.getName());
            ja.add(temp);
        }
        jo.put("服务列表",ja);
        return jo.toString();
    }

    /**
     * 注册服务
     * @param instanceId
     * @param appName
     * @param appGroupName
     * @param ipAddr
     * @param sid
     * @param port
     * @param securePort
     * @param homePageUrl
     * @param statusPageUrl
     * @param healthCheckUrl
     * @param secureHealthCheckUrl
     * @param vipAddress
     * @param secureVipAddress
     * @param countryId
     * @param dataCenterInfo
     * @param hostName
     * @param status
     * @param overriddenstatus
     * @param leaseInfo
     * @param isCoordinatingDiscoveryServer
     * @param metadata
     * @param lastUpdatedTimestamp
     * @param lastDirtyTimestamp
     * @param actionType
     * @param asgName
     * @return
     */
    @PostMapping("/register")
    public String register(
            @RequestParam("instanceId") String instanceId,
            @RequestParam("app") String appName,
            @RequestParam("appGroupName") String appGroupName,
            @RequestParam("ipAddr") String ipAddr,
            @RequestParam("sid") String sid,
            @RequestParam("port") InstanceInfo.PortWrapper port,
            @RequestParam("securePort") InstanceInfo.PortWrapper securePort,
            @RequestParam("homePageUrl") String homePageUrl,
            @RequestParam("statusPageUrl") String statusPageUrl,
            @RequestParam("healthCheckUrl") String healthCheckUrl,
            @RequestParam("secureHealthCheckUrl") String secureHealthCheckUrl,
            @RequestParam("vipAddress") String vipAddress,
            @RequestParam("secureVipAddress") String secureVipAddress,
            @RequestParam("countryId") int countryId,
            @RequestParam("dataCenterInfo") DataCenterInfo dataCenterInfo,
            @RequestParam("hostName") String hostName,
            @RequestParam("status") InstanceInfo.InstanceStatus status,
            @RequestParam("overriddenstatus") InstanceInfo.InstanceStatus overriddenstatus,
            @RequestParam("leaseInfo") LeaseInfo leaseInfo,
            @RequestParam("isCoordinatingDiscoveryServer") Boolean isCoordinatingDiscoveryServer,
            @RequestParam("metadata") HashMap<String, String> metadata,
            @RequestParam("lastUpdatedTimestamp") Long lastUpdatedTimestamp,
            @RequestParam("lastDirtyTimestamp") Long lastDirtyTimestamp,
            @RequestParam("actionType") InstanceInfo.ActionType actionType,
            @RequestParam("asgName") String asgName) {
        try {
            InstanceInfo info = new InstanceInfo(
                    instanceId, appName, appGroupName, ipAddr,
                    sid, port, securePort, homePageUrl,
                    statusPageUrl, healthCheckUrl, secureHealthCheckUrl, vipAddress,
                    secureVipAddress, countryId, dataCenterInfo, hostName,
                    status, overriddenstatus, leaseInfo, isCoordinatingDiscoveryServer,
                    metadata, lastUpdatedTimestamp, lastDirtyTimestamp, actionType, asgName);
            eurekaServerContext.getRegistry().register(info, true);
            return "注册成功";
        } catch (Exception e) {
            return "注册失败";
        }
    }

    /**
     * 取消注册
     * @param appName
     * @param id
     * @param isReplication
     * @return
     */
    @PostMapping("/cancel")
    public String cancel(
            @RequestParam("appName") String appName,
            @RequestParam("id") String id,
            @RequestParam("isReplication") boolean isReplication) {
        try {
            eurekaServerContext.getRegistry().cancel(appName, id, isReplication);
            return "取消成功";
        } catch (Exception e) {
            return "取消失败";
        }
    }
}
