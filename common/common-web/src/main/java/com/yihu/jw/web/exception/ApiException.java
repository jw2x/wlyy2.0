package com.yihu.jw.web.exception;


import org.springframework.util.Assert;

public class ApiException extends RuntimeException {

    private String errorDesc; //错误消息
    private int errorCode; //错误码

    public ApiException(String errorDesc) {
        this(errorDesc, 500);
    }

    public ApiException(String errorDesc, int errorCode) {
        super(errorDesc);
        Assert.state(errorCode != 200, "The error code cannot be equal to 200");
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
