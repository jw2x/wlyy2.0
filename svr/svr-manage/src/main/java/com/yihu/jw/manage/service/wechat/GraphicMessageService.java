package com.yihu.jw.manage.service.wechat;

import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
@Service
public class GraphicMessageService {
    @Value("${spring.gateway}")
    private String url;

    @Autowired
    private RestTemplate template;

   public Page<ManageUser> list(String title, String description, Integer size, Integer page) {
       Object forObject = null;
       String fields = "id,code,title,description,url";
       String filters="";//&filters={filters}
       //forObject = template.getForObject(url + "/graphicMessage/getWxGraphicMessages?fields={fields}&filters={filters}&sorts={sorts}&size={size}&page={page}",
       //        Object.class,fields,null,null,size,page);
       forObject = template.getForObject(url + "/graphicMessage/getWxGraphicMessages?fields="+fields+"&size="+size+"&page="+page,//&filters="+null+"&sorts="+null+
                       Object.class);
       System.out.print(forObject.toString());
       System.out.print("-----------------------------");
       return null;
    }

    public Envelop findByCode(String code) {
        //Object forObject = template.getForObject(url + "/graphicMessage/getByCode?code=1", Object.class);
        Object forObject = template.getForObject(url + "/graphicMessage/getByCodea?code={code}", Object.class,code);
        System.out.print(forObject.toString());
        System.out.print("-----------------------------");
        return null;
    }
    public Envelop findByCodea(String code) {
        //Object forObject = template.getForObject(url + "/graphicMessage/getByCode?code=1", Object.class);
        Object forObject = template.getForObject(url + "/graphicMessage/getByCodea/{code}", Object.class,code);
        System.out.print(forObject.toString());
        System.out.print("-----------------------------");
        return null;
    }
}
