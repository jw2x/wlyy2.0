package com.yihu.jw.util.http;

import javafx.util.Pair;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @created Airhead 2016/8/24.
 */
public class HttpClientKit {
    private static Map<String, Pair<String, String>> keyStoreMap = new HashMap<>();
    private static Map<String, SSLConnectionSocketFactory> keyStoreSSLMap = new HashMap<>();

    /**
     * 用于HTTP和受信HTTPS
     *
     * @return
     */
    public static HTTPClient use() {
        return new DefaultClientImpl();
    }

    /**
     * 用于非受信HTTPS
     *
     * @param host host正常情况是不需要包含端口号的。
     *             但在非域名（或开发）时，不同的端口会使用不同证书，
     *             在添加host时，可以增加端口号
     *             格式：
     *             1.ip:port
     *             2.domain
     * @return
     */
    public static HTTPClient custom(String host) {
        if (host != null) {
            Pair<String, String> keyStore = keyStoreMap.get(host);
            if (keyStore != null) {
                return new CustomTrustClientClientImpl(keyStore.getKey(), keyStore.getValue());
            }
        }

        return new CustomTrustClientClientImpl();
    }

    public static void addKeyStore(String host, String file, String password) throws Exception {
        keyStoreMap.put(host, new Pair<>(file, password));
    }

    public static void addKeyStoreSSL(String host, SSLConnectionSocketFactory sslConnectionSocketFactory) throws Exception {
        keyStoreSSLMap.put(host, sslConnectionSocketFactory);
    }

    public static HTTPResponse get(String url) {
        return use().get(url);
    }

    public static HTTPResponse get(String url, Map<String, String> params) {
        return use().get(url, params);
    }

    public static HTTPResponse get(String url, Map<String, String> params, Map<String, String> headers) {
        return use().get(url, params, headers);
    }

    public static HTTPResponse post(String url) {
        return use().post(url);
    }

    public static HTTPResponse post(String url, Map<String, String> params) {
        return use().post(url, params);
    }

    public static HTTPResponse post(String url, String json) {
        return use().post(url, json, null);
    }

    public static HTTPResponse post(String url, String json, Map<String, String> headers) {
        return use().post(url, json, headers);
    }

    public static HTTPResponse post(String url, Map<String, String> params, Map<String, String> headers) {
        return use().post(url, params, headers);
    }

    public static HTTPResponse postFile(String url, String path) {
        return use().postFile(url, path);
    }

    public static HTTPResponse postFile(String url, String path, Map<String, String> params) {
        if (url.startsWith("https")) {
            return custom(url).postFile(url, path, params);
        } else {
            return use().postFile(url, path, params);
        }
    }

    public static HTTPResponse postFile(String url, String key, String path, String contentType) {
        if (url.startsWith("https")) {
            return custom(url).postFile(url, key, path, contentType);
        } else {
            return use().postFile(url, key, path, contentType);
        }
    }

    public static HTTPResponse postFile(String url, String path, Map<String, String> params, Map<String, String> headers) {
        if (url.startsWith("https")) {
            return custom(url).postFile(url, path, params, headers);
        } else {
            return use().postFile(url, path, params, headers);
        }
    }

    public static HTTPResponse put(String url) {
        return use().put(url);
    }

    public static HTTPResponse put(String url, Map<String, String> params) {
        return use().put(url, params);
    }

    public static HTTPResponse put(String url, String json) {
        return use().put(url, json);
    }

    public static HTTPResponse put(String url, Map<String, String> params, Map<String, String> headers) {
        return use().put(url, params, headers);
    }

    public static HTTPResponse delete(String url) {
        return use().delete(url);
    }

    public static HTTPResponse delete(String url, Map<String, String> params) {
        return use().delete(url, params);
    }

    public static HTTPResponse delete(String url, String json) {
        return use().delete(url, json);
    }

    public static HTTPResponse delete(String url, Map<String, String> params, Map<String, String> headers) {
        return use().delete(url, params, headers);
    }

    public static HTTPResponse request(String method, String url, Map<String, String> params, Map<String, String> headers) {
        if (url.startsWith("https")) {
            return custom(url).request(method, url, params, headers);
        } else {
            return use().request(method, url, params, headers);
        }

    }
}
