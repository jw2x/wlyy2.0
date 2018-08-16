package com.yihu.jw.web.exception;


public class ApiException extends RuntimeException {

    private String errorDesc; //错误消息
    private int errorCode; //错误码

    public ApiException(String errorDesc) {
        this(errorDesc, 500);
    }

    public ApiException(String errorDesc, int errorCode) {
        super(errorDesc);
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
