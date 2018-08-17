package com.yihu.jw.web.handler;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.web.Envelop;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handler - wlyy2.0全局错误
 * Created by progr1mmer on 2018/1/25.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String appName;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public Envelop handle(HttpServletResponse response, Exception e) throws IOException {
        Envelop envelop = new Envelop();
        if (e instanceof NoHandlerFoundException) { //404
            envelop.setMessage(e.getMessage());
            envelop.setStatus(HttpStatus.NOT_FOUND.value());
        } else if (e instanceof HttpRequestMethodNotSupportedException){ //请求方法有误
            envelop.setMessage(e.getMessage());
            envelop.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        } else if (e instanceof MissingServletRequestParameterException) { //参数有误
            envelop.setMessage(e.getMessage());
            envelop.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (e instanceof FeignException) { //执行Feign失败的时候默认当前服务做为网关执行请求的时候，从上游服务器接收到无效的响应
            envelop.setMessage("Execute Feign: " + e.getMessage());
            envelop.setStatus(HttpStatus.BAD_GATEWAY.value());
        } else if (e instanceof ApiException) { //业务逻辑异常
            ApiException apiException = (ApiException) e;
            envelop.setMessage(apiException.getMessage());
            envelop.setStatus(apiException.getErrorCode());
            return envelop; //此异常不进行日志记录
        } else {
            envelop.setMessage(e.getMessage());
            envelop.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        logger.error(e.getMessage(), e);
        return envelop;
    }

}
