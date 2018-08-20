package com.yihu.iot.util.http;

import com.yihu.iot.util.encode.Base64;
import com.yihu.iot.util.operator.StringUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hzp on 2016/4/14.
 */
public class HttpClientUtil {

    /**
     * 发送post请求
     * @param url     请求地址
     * @param params  请求参数
     * @return
     */
    public static String postBody(String url, String params,Map<String,Object> header,SSLConnectionSocketFactory ssl, String user, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        org.springframework.http.HttpEntity<String> formEntity = new org.springframework.http.HttpEntity<String>(params, headers);
        String ret = restTemplate.postForObject(url, formEntity, String.class);
        return ret;
    }


    /**************************** 私有方法 *****************************************/
    private static CloseableHttpClient getCloseableHttpClient(SSLConnectionSocketFactory ssl) {
        if(ssl == null)
        {
            return HttpClients.createDefault();
        }
        else{
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(ssl)
                    .build();
            return httpClient;
        }
    }

    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (response != null) {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HttpRequestBase getRequest(String method,String url,Map<String,Object> params,Map<String,Object> header) throws Exception
    {
        List<BasicNameValuePair> jsonParams = new ArrayList<>();
        //配置参数
        if(params!=null) {
            for (String key : params.keySet()) {
                if (!StringUtil.isEmpty(String.valueOf(params.get(key))) && !"null".equals( String.valueOf(params.get(key)))) {
                    jsonParams.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
                }
            }
        }
        HttpRequestBase request;
        if(method.equals("POST"))
        {
            request = new HttpPost(url + "?" + URLEncodedUtils.format(jsonParams, Consts.UTF_8));
        }
        else if(method.equals("PUT"))
        {
            request = new HttpPut(url + "?" + URLEncodedUtils.format(jsonParams, Consts.UTF_8));
        }
        else if(method.equals("DELETE"))
        {
            request = new HttpDelete(url + "?" + URLEncodedUtils.format(jsonParams, Consts.UTF_8));
        }
        else
        {
            request = new HttpGet(url + "?" + URLEncodedUtils.format(jsonParams, Consts.UTF_8));
        }
        //配置头部信息
        if(header!=null)
        {
            for (String key : header.keySet()) {
                request.addHeader(key, header.get(key).toString());
            }
        }
        return request;
    }
    /****************************** 公用方法 *******************************************/
    /**
     * get请求
     */
    public static HttpResponse request(String method, String url, Map<String,Object> params, Map<String,Object> header, SSLConnectionSocketFactory ssl, String user, String password) {
        HttpResponse re = new HttpResponse();
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = getCloseableHttpClient(ssl);

        //设置请求信息
        try {
            RequestConfig requestConfig = RequestConfig.custom().
                    setAuthenticationEnabled(true).build();
            HttpRequestBase request = getRequest(method,url,params,header);

            request.setConfig(requestConfig);
            //需要验证
            if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(password)) {
                HttpClientContext context = HttpClientContext.create();
                //通过http的上下文设置账号密码
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(new org.apache.http.auth.AuthScope(org.apache.http.auth.AuthScope.ANY_HOST, org.apache.http.auth.AuthScope.ANY_PORT),new org.apache.http.auth.UsernamePasswordCredentials(user, password));
                context.setCredentialsProvider(credsProvider);
                response = httpclient.execute(request, context);
            } else {
                response = httpclient.execute(request);
            }
            re.setBody(EntityUtils.toString(response.getEntity(), "UTF-8"));
            re.setStatusCode(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            re.setStatusCode(201);
            re.setBody(e.getMessage());
            e.printStackTrace();
        } finally {
            close(httpclient, response);
        }
        return re;
    }




    /**
     * 发送文件
     *
     * @param url        路径
     * @param formParams 参数
     * @return
     */
    public static HttpResponse postFile(String url,
                                        File file, List<NameValuePair> formParams, SSLConnectionSocketFactory ssl, String username, String password, Map<String,Object> header) {
        HttpResponse re = new HttpResponse();
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = getCloseableHttpClient(ssl);

        try{

            //设置请求信息
            RequestConfig requestConfig = RequestConfig.custom().
                    setAuthenticationEnabled(true).build();
            //创建httppost请求
            HttpPost httpPost = new HttpPost(url);
            if(header!=null) {
                for (String key : header.keySet()) {
                    httpPost.addHeader(key, header.get(key).toString());
                }
            }
            httpPost.setConfig(requestConfig);
            //新建文件对象并且设置文件
            FileBody bin = new FileBody(file);
            MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
            reqEntity.addBinaryBody("pack", file);
            //设置参数
            if (formParams != null && formParams.size() > 0) {
                for (NameValuePair nv : formParams) {
                    reqEntity.addTextBody(nv.getName(), nv.getValue(), ContentType.create("text/plain", Charset.forName(HTTP.UTF_8)));
                }
            }
            httpPost.setEntity(reqEntity.build());
            //设置验证
            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
                //需要验证
                HttpClientContext context = HttpClientContext.create();
                //通过http的上下文设置账号密码
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(new org.apache.http.auth.AuthScope(org.apache.http.auth.AuthScope.ANY_HOST, org.apache.http.auth.AuthScope.ANY_PORT),
                        new org.apache.http.auth.UsernamePasswordCredentials(username, password));
                context.setCredentialsProvider(credsProvider);
                response = httpClient.execute(httpPost, context);
            } else {
                response = httpClient.execute(httpPost);
            }
            re.setStatusCode(response.getStatusLine().getStatusCode());
            re.setBody(EntityUtils.toString(response.getEntity(), "UTF-8"));;
        } catch (Exception e) {
            re.setStatusCode(201);
            re.setBody(e.getMessage());
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return re;
    }

    /**
     * 发送File
     */
    public static File downLoadFileByBase64(String filePath, Map<String, Object> params, String url, String username, String password) {
        File file = null;
        CloseableHttpResponse response = null;
        List<BasicNameValuePair> jsonParams = new ArrayList<>();
        CloseableHttpClient httpclient = getCloseableHttpClient(null);

        try {
            HttpPost httpPost = new HttpPost(url);
            //设置请求信息
            RequestConfig requestConfig = RequestConfig.custom().
                    setAuthenticationEnabled(true).build();
            //设置参数
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                formparams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            httpPost.setEntity(entity);
            httpPost.setConfig(requestConfig);
            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
                //需要验证
                HttpClientContext context = HttpClientContext.create();
                //通过http的上下文设置账号密码
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(new org.apache.http.auth.AuthScope(org.apache.http.auth.AuthScope.ANY_HOST, org.apache.http.auth.AuthScope.ANY_PORT),
                        new org.apache.http.auth.UsernamePasswordCredentials(username, password));
                context.setCredentialsProvider(credsProvider);
                response = httpclient.execute(httpPost, context);
            } else {
                response = httpclient.execute(httpPost);
            }
            HttpEntity httpEntity = response.getEntity();
            String responString = EntityUtils.toString(httpEntity, "UTF-8");
            file = new File(filePath);
            file.getParentFile().mkdirs();
            InputStream i = new ByteArrayInputStream(Base64.decode(responString));
            FileOutputStream fileout = new FileOutputStream(file);
            /**
             * 根据实际运行效果 设置缓冲区大小
             */
            byte[] buffer = new byte[1024];
            int ch = 0;
            while ((ch = i.read(buffer)) != -1) {
                fileout.write(buffer, 0, ch);
            }
            i.close();
            fileout.flush();
            fileout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            close(httpclient, response);
        }
        return file;
    }

    public static HttpResponse postForm(String url, List<NameValuePair> formParams) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(postEntity);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            HttpResponse res = new HttpResponse(response.getStatusLine().getStatusCode(), EntityUtils.toString(entity));
            return res;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }

        return null;
    }

    /**
     * 下载文件
     */
    public static File downLoadFile(String filePath, String url, String username, String password) {
        File file = null;
        CloseableHttpResponse response = null;
        List<BasicNameValuePair> jsonParams = new ArrayList<>();
        CloseableHttpClient httpclient = getCloseableHttpClient(null);

        try {
            HttpGet httpGet = new HttpGet(url);
            //设置请求信息
            RequestConfig requestConfig = RequestConfig.custom().
                    setAuthenticationEnabled(true).build();

            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
                //需要验证
                HttpClientContext context = HttpClientContext.create();
                //通过http的上下文设置账号密码
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(new org.apache.http.auth.AuthScope(org.apache.http.auth.AuthScope.ANY_HOST, org.apache.http.auth.AuthScope.ANY_PORT),
                        new org.apache.http.auth.UsernamePasswordCredentials(username, password));
                context.setCredentialsProvider(credsProvider);
                response = httpclient.execute(httpGet, context);
            } else {
                response = httpclient.execute(httpGet);
            }
            HttpEntity httpEntity = response.getEntity();
            InputStream is = httpEntity.getContent();
            file = new File(filePath);
            file.getParentFile().mkdirs();
            FileOutputStream fileout = new FileOutputStream(file);
            /**
             * 根据实际运行效果 设置缓冲区大小
             */
            byte[] buffer = new byte[1024];
            int ch = 0;
            while ((ch = is.read(buffer)) != -1) {
                fileout.write(buffer, 0, ch);
            }
            is.close();
            fileout.flush();
            fileout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            close(httpclient, response);
        }
        return file;
    }



}
