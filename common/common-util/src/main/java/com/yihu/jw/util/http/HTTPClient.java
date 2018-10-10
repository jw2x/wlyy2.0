package com.yihu.jw.util.http;

import java.util.Map;

/**
 * @created Airhead 2016/8/24.
 */
public interface HTTPClient {
    //主要的几种方法，其他的暂时未使用
    String GET = "GET";
    String POST = "POST";
    String PUT = "PUT";
    String DELETE = "DELETE";

    HTTPResponse get(String url);

    HTTPResponse get(String url, Map<String, String> params);

    HTTPResponse get(String url, Map<String, String> params, Map<String, String> headers);

    HTTPResponse post(String url);

    HTTPResponse post(String url, Map<String, String> params);

    HTTPResponse post(String url, Map<String, String> params, Map<String, String> headers);

    HTTPResponse post(String url, String json, Map<String, String> headers);

    HTTPResponse postFile(String url, String path);

    HTTPResponse postFile(String url, String path, Map<String, String> params);

    HTTPResponse postFile(String url, String path, Map<String, String> params, Map<String, String> headers);

    HTTPResponse postFile(String url, String key, String path, String contentType);

    HTTPResponse put(String url);

    HTTPResponse put(String url, Map<String, String> params);

    HTTPResponse put(String url, String json);

    HTTPResponse put(String url, Map<String, String> params, Map<String, String> headers);

    HTTPResponse delete(String url);

    HTTPResponse delete(String url, Map<String, String> params);

    HTTPResponse delete(String url, String json);

    HTTPResponse delete(String url, Map<String, String> params, Map<String, String> headers);

    HTTPResponse request(String method, String url, Map<String, String> params, Map<String, String> headers);
}
