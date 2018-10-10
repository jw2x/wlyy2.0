package com.yihu.jw.util.http;

import okhttp3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Airhead
 * @since 2016/8/24.
 */
class DefaultClientImpl implements HTTPClient {
    private static final Log log = LogFactory.getLog(DefaultClientImpl.class);

    private OkHttpClient httpClient;

    DefaultClientImpl() {
        this.httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).build();
    }

    public HTTPResponse get(String url) {
        return get(url, null);
    }

    public HTTPResponse get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    public HTTPResponse get(String url, Map<String, String> params, Map<String, String> headers) {
        try {
            Request.Builder builder = new Request.Builder();
            if (headers != null) {
                builder.headers(Headers.of(headers));
            }
            Request request = builder
                    .url(formatURL(url, params))
                    .build();
            Response response = httpClient.newCall(request).execute();

            return new HTTPResponse(response.code(), response.body().string());
        } catch (IOException ex) {
            // ?? 怎么把异常处理了??  应该交由调用者处理
            ex.printStackTrace();
            log.error(ex.getMessage());
            return new HTTPResponse(417,ex.getMessage());
        }
    }

    public HTTPResponse post(String url) {
        return post(url, (Map<String, String>) null);
    }

    public HTTPResponse post(String url, Map<String, String> params) {
        return post(url, params, null);
    }

    public HTTPResponse post(String url, Map<String, String> params, Map<String, String> headers) {
        try {
            FormBody.Builder fromBodyBuilder = new FormBody.Builder();
            if (params != null) {
                params.forEach(fromBodyBuilder::add);
            }
            RequestBody requestBody = fromBodyBuilder
                    .build();

            Request.Builder builder = new Request.Builder();
            if (headers != null) {
                builder.headers(Headers.of(headers));
            }
            Request request = builder
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = httpClient.newCall(request).execute();

            return new HTTPResponse(response.code(), response.body().string());
        } catch (IOException ex) {
            //讲异常信息返回,交由调用者处理
            log.error(ex.getMessage());
            return new HTTPResponse(417,ex.getMessage());
        }

    }

    @Override
    public HTTPResponse post(String url, String json, Map<String, String> headers) {
        try {
            Request.Builder builder = new Request.Builder();
            if (headers != null) {
                builder.headers(Headers.of(headers));
            }

            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, json);
            Request request = builder
                    .url(url)
                    .post(body)
                    .build();

            Response response = httpClient.newCall(request).execute();

            return new HTTPResponse(response.code(), response.body().string());
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return new HTTPResponse(417, ex.getMessage());
        }
    }

    public HTTPResponse postFile(String url, String path) {
        return postFile(url, path, null);
    }

    public HTTPResponse postFile(String url, String path, Map<String, String> params) {
        return postFile(url, path, params, null);
    }

    public HTTPResponse postFile(String url, String path, Map<String, String> params, Map<String, String> headers) {
        try {
            File file = new File(path);

            final MediaType type = MediaType.parse("application/zip");
            Request.Builder requestBuilder = new Request.Builder();
            if (headers != null) {
                requestBuilder.headers(Headers.of(headers));
            }
            MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
            multipartBuilder.setType(MultipartBody.FORM)
                    .addFormDataPart("pack", file.getName(), RequestBody.create(type, file));
            for (String key : params.keySet()) {
                multipartBuilder.addFormDataPart(key, params.get(key));
            }
            Request request = requestBuilder
                    .url(url)
                    .post(multipartBuilder.build())
                    .build();

            Response response = httpClient.newCall(request).execute();

            return new HTTPResponse(response.code(), response.body().string());
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return new HTTPResponse(417, ex.getMessage());
        }
    }

    @Override
    public HTTPResponse postFile(String url, String key, String path, String contentType) {
        try {
            File file = new File(path);

            final MediaType type = MediaType.parse(contentType);
            Request.Builder requestBuilder = new Request.Builder();
            MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
            multipartBuilder.setType(MultipartBody.FORM)
                    .addFormDataPart(key, file.getName(), RequestBody.create(type, file));
            Request request = requestBuilder
                    .url(url)
                    .post(multipartBuilder.build())
                    .build();

            Response response = httpClient.newCall(request).execute();

            return new HTTPResponse(response.code(), response.body().string());
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return new HTTPResponse(417, ex.getMessage());
        }

    }

    public HTTPResponse put(String url) {
        return put(url, (Map<String, String>) null);
    }

    public HTTPResponse put(String url, Map<String, String> params) {
        return put(url, params, null);
    }

    @Override
    public HTTPResponse put(String url, String json) {
        try {
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .build();

            Response response = httpClient.newCall(request).execute();

            return new HTTPResponse(response.code(), response.body().string());
        } catch (IOException ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            return new HTTPResponse(417, ex.getMessage());
        }

    }

    public HTTPResponse put(String url, Map<String, String> params, Map<String, String> headers) {
        try {
            FormBody.Builder fromBodyBuilder = new FormBody.Builder();
            if (params != null) {
                params.forEach(fromBodyBuilder::add);
            }
            RequestBody requestBody = fromBodyBuilder
                    .build();

            Request.Builder builder = new Request.Builder();
            if (headers != null) {
                builder.headers(Headers.of(headers));
            }
            Request request = builder
                    .url(url)
                    .put(requestBody)
                    .build();

            Response response = httpClient.newCall(request).execute();

            return new HTTPResponse(response.code(), response.body().string());
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return new HTTPResponse(417, ex.getMessage());
        }

    }

    public HTTPResponse delete(String url) {
        return delete(url, (Map<String, String>) null);
    }

    public HTTPResponse delete(String url, Map<String, String> params) {
        return delete(url, params, null);
    }

    @Override
    public HTTPResponse delete(String url, String json) {
        try {
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .delete(body)
                    .build();

            Response response = httpClient.newCall(request).execute();

            return new HTTPResponse(response.code(), response.body().string());
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return new HTTPResponse(417, ex.getMessage());
        }

    }

    public HTTPResponse delete(String url, Map<String, String> params, Map<String, String> headers) {
        try {
            FormBody.Builder fromBodyBuilder = new FormBody.Builder();
            if (params != null) {
                params.forEach(fromBodyBuilder::add);
            }
            RequestBody requestBody = fromBodyBuilder
                    .build();

            Request.Builder builder = new Request.Builder();
            if (headers != null) {
                builder.headers(Headers.of(headers));
            }
            Request request = builder
                    .url(url)
                    .delete(requestBody)
                    .build();

            Response response = httpClient.newCall(request).execute();

            return new HTTPResponse(response.code(), response.body().string());
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return new HTTPResponse(417, ex.getMessage());
        }

    }

    public HTTPResponse request(String method, String url, Map<String, String> params, Map<String, String> headers) {
        if (method.equals(POST)) {
            return post(url, params, headers);
        }

        if (method.equals(GET)) {
            return get(url, params, headers);
        }

        if (method.equals(PUT)) {
            return put(url, params, headers);
        }

        if (method.equals(DELETE)) {
            return delete(url, params, headers);
        }

        return get(url, params, headers);
    }

    protected void setHttpClient(OkHttpClient okHttpClient) {
        this.httpClient = okHttpClient;
    }

    private String formatURL(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        }

        final String[] query = {""};
        params.forEach((name, value) -> {
            try {
                query[0] += "&" + name + "=" + URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("encode url ");
            }
        });

        if (url.contains("?")) {
            return url + query[0];
        }

        return url + "?" + query[0].substring(1);
    }
}
