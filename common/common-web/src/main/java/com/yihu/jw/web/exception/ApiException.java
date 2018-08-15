package com.yihu.jw.web.exception;

import org.springframework.http.HttpStatus;

/**
 * API 异常。使用错误代码初始化，并可接收用于补充错误消息的参数。
 * 用于描述错误代码的信息配置在各服务配置文件中，并由服务配置中心统一管理。
 *
 * 错误描述结构，结构(字段errors对资源而言，REST规范错误不包含此结构)：
 * {
 *    "message": "Validation Failed",
 *    "document_url": "https://ehr.yihu.com/docs/api/somewhere"
 *    "errors": [
 *        {
 *            "resource": "User",
 *            "field": "title",
 *            "code": "missing_field"
 *        }
 *    ]
 * }
 *
 * @author Sand
 * @version 1.0
 * @created 2015.12.20 16:05
 */
public class ApiException extends RuntimeException {

    private HttpStatus httpStatus; //Http 状态码，默认请求成功
    private int errorCode; //用于从配置环境中提取错误信息
    private String message; //错误消息
    private String documentURL; //文档连接

    public ApiException(String message) {
        this(-1, message);
    }

    public ApiException(int errorCode, String message) {
        this(HttpStatus.OK, errorCode, message);
    }

    public ApiException(HttpStatus httpStatus, String message){
        this(httpStatus, -1, message, null);
    }

    public ApiException(HttpStatus httpStatus, int errorCode, String message){
        this(httpStatus, errorCode, message, null);
    }

    public ApiException(HttpStatus httpStatus, int errorCode, String message, String documentURL){
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
        this.documentURL = documentURL;
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }

    public Integer errorCode() {
        return errorCode;
    }

    public String documentURL() {
        return documentURL;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
