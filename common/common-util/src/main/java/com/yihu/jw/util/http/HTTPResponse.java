package com.yihu.jw.util.http;

/**
 * @created Airhead 2016/8/24.
 */
public class HTTPResponse {

    private final int statusCode;// e.g. 200
    private final String body;

    public HTTPResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }
}
