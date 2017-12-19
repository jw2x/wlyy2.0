package com.yihu.ehr.iot.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.ehr.iot.util.ObjectMapperUtil;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.ehr.util.rest.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lincl
 * @version 1.0
 * @created 2016/7/22
 */

/**
 * �����ɫ��Ϣ
 */
@Service
public class RoleCache {
    //    private final static Map<String, CopyOnWriteArrayList<String>> resourceMap = new ConcurrentHashMap<>();
    @Value("${service-gateway.profileInnerUrl}")
    private String comUrl;
    @Value("${app.baseClientId}")
    private String baseClientId;
    @Autowired
    private ObjectMapper objectMapper;


    private final static CopyOnWriteArrayList<String> resourceList = new CopyOnWriteArrayList<>();
    //    private final static Set<String> resourceList = Collections.synchronizedSet(new HashSet<String>());
    public RoleCache() throws Exception {
        //loadRole();
    }

    private List<Map<String,Object>> getAppFeatures() throws Exception {
        Map params = new HashMap<>();
        String url = "/filterFeatureNoPage";
        params.put("filters", "appId=" + baseClientId);
        HttpResponse rs = HttpHelper.get(comUrl + url, params);
        //ObjectMapperUtil objectMapperUtil = new ObjectMapperUtil();
        Envelop envelop = (Envelop) ObjectMapperUtil.toModel(rs.getBody(), new TypeReference<Envelop>() {});
        return envelop.getDetailModelList();
        //return null;
    }

    public boolean contains(String url){
        return resourceList.contains(url);
    }

    public void addRes(String res){
        synchronized (resourceList){
            resourceList.add(res);
        }
    }

    public void removeRes(String res){
        synchronized (resourceList) {
            resourceList.remove(res);
        }
    }

    /**
     * ��ʼ�����Ȩ����Ϣ
     */
    @PostConstruct
    private void loadRole() throws Exception {
        List<Map<String,Object>> appFeatureModelList = getAppFeatures();
        for(Map<String,Object> feature: appFeatureModelList){
            if(feature.get("url")!=null)
                resourceList.add(feature.get("url").toString());
        }

//        CopyOnWriteArrayList<String> cas = new CopyOnWriteArrayList<>();
//        cas.add("dataCenterAdmin");
//        resourceMap.put("appDel", cas );
//
//        cas = new CopyOnWriteArrayList<>();
//        cas.add("dataCenterAdmin");
//        resourceMap.put("/app/platform/initial", cas );
//
//        cas = new CopyOnWriteArrayList<>();
//        cas.add("dataCenterAdmin");
//        resourceMap.put("/app/platform/list", cas );
//
//        cas = new CopyOnWriteArrayList<>();
//        cas.add("admin2");
//        resourceMap.put("/app/api/initial", cas );
//
//        cas = new CopyOnWriteArrayList<>();
//        cas.add("admin2");
//        resourceMap.put("appAdd", cas );
    }

//    /**
//     * ��ȡ�ɷ���key��Դ�Ľ�ɫ
//     * @param key ��Դ
//     * @return ��ɫ��
//     */
//    public CopyOnWriteArrayList getConfigAttributes(String key){
//        return resourceMap.get(key);
//    }

//    /**
//     * �ж��Ƿ�ca�Ƿ񱻸���key����Ȩ��
//     * @param key ��Դ��Ϣ
//     * @param ca  ��ɫ
//     * @return
//     */
//    public boolean hasRole(String key, String ca){
//        Collection c = resourceMap.get(key);
//        if(c!=null)
//            return c.contains(ca);
//        return true;
//    }
//
//    /**
//     * �ж��Ƿ�ca�Ƿ񱻸���key����Ȩ��
//     * @param key ��Դ��Ϣ
//     * @param c  ��ɫ��
//     * @return
//     */
//    public boolean hasRole(String key, Collection<String> c) {
//        CopyOnWriteArrayList elements = resourceMap.get(key);
//        for (Object e: c) {
//            if(elements.indexOf(e)>=0)
//                return true;
//        }
//        return false;
//    }
//
//    /**
//     * ���Ȩ����Ϣ
//     * @param key ��Դ��Ϣ
//     * @param ca  ��ɫ
//     */
//    public void addCache(String key, String ca){
//        if(resourceMap.get(key)==null){
//            resourceMap.put(key, new CopyOnWriteArrayList<>());
//        }
//        resourceMap.get(key).add(ca);
//    }
//
//    /**
//     * ɾ��Ȩ����Ϣ
//     * @param key ��Դ��Ϣ
//     * @param ca ��ɫ
//     */
//    public void removeCache(String key, String ca){
//        if(resourceMap.get(key)!=null){
//            resourceMap.get(key).remove(ca);
//        }
//    }

}
