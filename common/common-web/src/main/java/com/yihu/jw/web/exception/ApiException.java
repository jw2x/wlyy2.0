package com.yihu.jw.web.exception;


import org.springframework.util.Assert;

public class ApiException extends RuntimeException {

    private int errorCode = -10000; //错误码

    public ApiException(String message) {
        this(message, 500);
    }

    public ApiException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
        Assert.state(errorCode != 200, "The error code cannot be equal to 200");
    }

}
