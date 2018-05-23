package com.yihu.jw.exception.advice;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.cloud.sleuth.Tracer;
import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理器
 * Created by 刘文彬 on 2018/5/9.
 */
@ControllerAdvice
public class MyControllerAdvice {
    @Autowired
    private Tracer tracer;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public BaseEnvelop myErrorHandler(ApiException ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        tracer.getCurrentSpan().tag("ApiExcepiton.Tag",new JSONObject(request.getParameterMap()).toString());
        tracer.getCurrentSpan().logEvent(ex.getMessage());
        logger.error(ex.getMessage());
        return BaseEnvelop.getError(ex.getMessage(),ex.getErrorCode());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseEnvelop myErrorHandler2(Exception ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        tracer.getCurrentSpan().tag("Excepiton.Tag",new JSONObject(request.getParameterMap()).toString());
        tracer.getCurrentSpan().logEvent(ex.getMessage());
        logger.error(ex.getMessage());
        return BaseEnvelop.getError(ex.getMessage());
    }


}
