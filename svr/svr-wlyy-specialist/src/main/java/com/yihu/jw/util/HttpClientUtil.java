package com.yihu.jw.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hmf on 2018/9/3.
 */
@Component
public class HttpClientUtil {

    public String postBody(String url, JSONObject params) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        org.springframework.http.HttpEntity<String> formEntity = new org.springframework.http.HttpEntity<String>(params.toString(), headers);
        String ret = restTemplate.postForObject(url, formEntity, String.class);
        return ret;
    }
}
