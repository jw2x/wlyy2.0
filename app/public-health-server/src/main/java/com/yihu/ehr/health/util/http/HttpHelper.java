package com.yihu.ehr.health.util.http;

import com.yihu.ehr.health.constant.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.util.List;
import java.util.Map;

@Component
public class HttpHelper {
    private static SSLConnectionSocketFactory defaultSSL;
    private static String defaultHttpUser;
    private static String defaultHttpPassword;

    static {
        //默认配置
        try {
            if (Constants.SSL_KEYSOTRE != null && Constants.SSL_KEYSOTRE.length() > 0 && Constants.SSL_PASSWORD != null && Constants.SSL_PASSWORD.length() > 0) {
                SSLContext sslContext = SSLContexts.custom()
                        .loadTrustMaterial(new File(Constants.SSL_KEYSOTRE), Constants.SSL_PASSWORD.toCharArray(),
                                new TrustSelfSignedStrategy())
                        .build();
                defaultSSL = new SSLConnectionSocketFactory(
                        sslContext,
                        new String[]{"TLSv1"},
                        null,
                        SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    /************************** Get方法 ******************************************/
    public static HttpResponse get(String url)
    {
        return get(url,null,null);
    }
    public static HttpResponse get(String url, Map<String,Object> params)
    {
        return get(url,params,null);
    }
    public static HttpResponse get(String url, Map<String,Object> params, Map<String,Object> header)
    {
        if(url.startsWith("https"))
        {
            return get(url,params,header,defaultSSL);
        }
        else{
            //默认http不走ssl和用户密码
            return get(url, params, header, null, null, null);
        }
    }
    public static HttpResponse get(String url, Map<String,Object> params, Map<String,Object> header, Boolean isCheck)
    {
        if(isCheck)
        {
            return get(url, params, header,defaultSSL, defaultHttpUser, defaultHttpPassword);
        }
        else{
            return get(url, params, header, null, null, null);
        }
    }
    public static HttpResponse get(String url, Map<String,Object> params, Map<String,Object> header, SSLConnectionSocketFactory ssl)
    {
        return get(url, params, header, ssl, defaultHttpUser, defaultHttpPassword);
    }
    public static HttpResponse get(String url, Map<String,Object> params, Map<String,Object> header, SSLConnectionSocketFactory ssl, String user, String password)
    {

        return HttpClientUtil.request("GET", url, params, header, ssl, user, password);
    }

    /************************** Post方法 ******************************************/
    public static HttpResponse post(String url)
    {
        return post(url, null, null);
    }
    public static HttpResponse post(String url, Map<String,Object> params)
    {
        return post(url, params, null);
    }
    public static HttpResponse post(String url, Map<String,Object> params, Map<String,Object> header)
    {
        if(url.startsWith("https"))
        {
            return post(url, params, header, defaultSSL);
        }
        else{
            //默认http不走ssl和用户密码
            return post(url, params, header, null, null, null);
        }
    }
    public static HttpResponse post(String url, Map<String,Object> params, Map<String,Object> header, Boolean isCheck)
    {
        if(isCheck)
        {
            return post(url, params, header, defaultSSL, defaultHttpUser, defaultHttpPassword);
        }
        else{
            return post(url, params, header, null, null, null);
        }
    }
    public static HttpResponse post(String url, Map<String,Object> params, Map<String,Object> header, SSLConnectionSocketFactory ssl)
    {
        return post(url, params, header, ssl, defaultHttpUser, defaultHttpPassword);
    }
    public static HttpResponse post(String url, Map<String,Object> params, Map<String,Object> header, SSLConnectionSocketFactory ssl, String user, String password)
    {
        return HttpClientUtil.request("POST",url,params,header,ssl,user,password);
    }
    public static HttpResponse postFile(String url, List<NameValuePair> formParams, String filePath, Map<String,Object> header)
    {
        File file = new File(filePath);
        if(url.startsWith("https"))
        {
            return HttpClientUtil.postFile(url, file, formParams, defaultSSL, defaultHttpUser, defaultHttpPassword, header);
        }
        else{
            //默认http不走ssl和用户密码
            return HttpClientUtil.postFile(url, file, formParams, null, defaultHttpUser, defaultHttpPassword, header);
        }
    }
    public static HttpResponse postFile(String url, List<NameValuePair> formParams, File file, Map<String,Object> header)
    {
        if(url.startsWith("https")) {
            return HttpClientUtil.postFile(url, file, formParams, defaultSSL,defaultHttpUser,defaultHttpPassword, header);
        }
        else{
            //默认http不走ssl和用户密码
            return HttpClientUtil.postFile(url, file, formParams, null,defaultHttpUser,defaultHttpPassword, header);
        }
    }
    /************************** Put方法 ******************************************/
    public static HttpResponse put(String url)
    {
        return put(url, null, null);
    }
    public static HttpResponse put(String url, Map<String,Object> params)
    {
        return put(url, params, null);
    }
    public static HttpResponse put(String url, Map<String,Object> params, Map<String,Object> header)
    {
        if(url.startsWith("https"))
        {
            return put(url, params, header, defaultSSL);
        }
        else{
            //默认http不走ssl和用户密码
            return put(url, params, header, null, null, null);
        }
    }
    public static HttpResponse put(String url, Map<String,Object> params, Map<String,Object> header, Boolean isCheck)
    {
        if(isCheck)
        {
            return put(url, params, header, defaultSSL, defaultHttpUser, defaultHttpPassword);
        }
        else{
            return put(url, params, header, null, null, null);
        }
    }
    public static HttpResponse put(String url, Map<String,Object> params, Map<String,Object> header, SSLConnectionSocketFactory ssl)
    {
        return put(url, params, header, ssl, defaultHttpUser, defaultHttpPassword);
    }
    public static HttpResponse put(String url, Map<String,Object> params, Map<String,Object> header, SSLConnectionSocketFactory ssl, String user, String password)
    {

        return HttpClientUtil.request("PUT",url,params,header,ssl,user,password);
    }

    /************************** Delete方法 **************************************/
    public static HttpResponse delete(String url)
    {
        return delete(url, null, null);
    }
    public static HttpResponse delete(String url, Map<String,Object> params)
    {
        return delete(url, params, null);
    }
    public static HttpResponse delete(String url, Map<String,Object> params, Map<String,Object> header)
    {
        if(url.startsWith("https"))
        {
            return delete(url, params, header, defaultSSL);
        }
        else{
            //默认http不走ssl和用户密码
            return delete(url, params, header, null, null, null);
        }
    }
    public static HttpResponse delete(String url, Map<String,Object> params, Map<String,Object> header, Boolean isCheck)
    {
        if(isCheck)
        {
            return delete(url, params, header, defaultSSL, defaultHttpUser, defaultHttpPassword);
        }
        else{
            return delete(url, params, header, null, null, null);
        }
    }
    public static HttpResponse delete(String url, Map<String,Object> params, Map<String,Object> header, SSLConnectionSocketFactory ssl)
    {
        return delete(url, params, header, ssl, defaultHttpUser, defaultHttpPassword);
    }
    public static HttpResponse delete(String url, Map<String,Object> params, Map<String,Object> header, SSLConnectionSocketFactory ssl, String user, String password)
    {

        return HttpClientUtil.request("DELETE",url,params,header,ssl,user,password);
    }

    /************************************postBody*******************************************************************/
    public static String postBody(String url)
    {
        return postBody(url, null, null);
    }
    public static String postBody(String url, String params)
    {
        return postBody(url, params, null);
    }
    public static String postBody(String url, String params, Map<String,Object> header)
    {
        if(url.startsWith("https"))
        {
            return postBody(url, params, header, defaultSSL);
        }
        else{
            //默认http不走ssl和用户密码
            return postBody(url, params, header, null, null, null);
        }
    }
    public static String postBody(String url, String params, Map<String,Object> header, Boolean isCheck)
    {
        if(isCheck)
        {
            return postBody(url, params, header, defaultSSL, defaultHttpUser, defaultHttpPassword);
        }
        else{
            return postBody(url, params, header, null, null, null);
        }
    }
    public static String postBody(String url, String params, Map<String,Object> header, SSLConnectionSocketFactory ssl)
    {
        return postBody(url, params, header, ssl, defaultHttpUser, defaultHttpPassword);
    }
    public static String postBody(String url, String params, Map<String,Object> header, SSLConnectionSocketFactory ssl, String user, String password)
    {
        return HttpClientUtil.postBody(url,params,header,ssl,user,password);
    }

}
