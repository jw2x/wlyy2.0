package com.yihu.jw.exception.business;

/**
 * Created by chenweida on 2017/6/9.
 * 基卫自己的错误异常
 */
public class ManageException extends Exception{
    public ManageException() {
    }

    public ManageException(String message) {
        super(message);
    }

    public ManageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManageException(Throwable cause) {
        super(cause);
    }

    public ManageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
