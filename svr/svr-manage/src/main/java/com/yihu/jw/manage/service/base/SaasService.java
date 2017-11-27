package com.yihu.jw.manage.service.base;

import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@Service
public class SaasService {

    @Value("${spring.gateway}")
    private String url;

    @Autowired
    private RestTemplate template;

   public Envelop list(String name, String sorts ,Integer size, Integer page) {

       Envelop forObject = template.getForObject(url + "/saas/getSaass",
               Envelop.class);
       return forObject;

    }

    public List getListNoPage() {
        Envelop envelop = template.getForObject(url +"/"+ BaseRequestMapping.api_base_common +BaseRequestMapping.Saas.api_getSaassNoPage,Envelop.class);
        List list = envelop.getDetailModelList();
        return list;
    }

    /**
     * key为code ,value为微信名字
     * @return
     */
    public Map<String,String> getSaasMap(){
        List saases = getListNoPage();
        Map<String, String> map = new HashMap<>();
        if(null!=saases){
            for(int i=0;i<saases.size();i++){
                LinkedHashMap saas = (LinkedHashMap) saases.get(i);
                map.put(saas.get("code").toString(),saas.get("name").toString());
            }
        }
        return map;
    }
}
