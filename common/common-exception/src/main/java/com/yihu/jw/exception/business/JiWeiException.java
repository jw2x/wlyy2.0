package com.yihu.jw.exception.business;

/**
 * Created by chenweida on 2017/6/16.
 */
public class JiWeiException extends Exception{
    public JiWeiException() {
    }

    public JiWeiException(String message) {
        super(message);
    }

    public JiWeiException(String message, Throwable cause) {
        super(message, cause);
    }

    public JiWeiException(Throwable cause) {
        super(cause);
    }

    public JiWeiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
