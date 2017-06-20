package com.yihu.jw.manage.service.base;

import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
