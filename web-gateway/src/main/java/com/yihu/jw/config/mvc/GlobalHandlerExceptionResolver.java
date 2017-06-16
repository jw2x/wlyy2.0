package com.yihu.jw.config.mvc;

import com.yihu.jw.restmodel.exception.SecurityException;
import com.yihu.jw.restmodel.exception.SystemException;
import com.yihu.jw.restmodel.exception.business.ManageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by chenweida on 20170317.
 * 全局异常处理
 */
@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);
    private static Integer status_500 = 500;//后台异常
    private static Integer status_510 = 510;//后台管理系统异常
    private static Integer status_403 = 403;//没权限 未登录 等权限异常


    /**
     * 在这里处理所有得异常信息
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object o, Exception ex) {
        String error = ex.getMessage();
        logger.error(error);
        if (ex instanceof ManageException) {
            //后台管理系统异常
            printWrite(status_510, error, resp);
        } else if (ex instanceof SecurityException) {
            //权限异常
            printWrite(status_403, error, resp);
        } else {
            //系统异常
            printWrite(status_500, error, resp);
        }
        return new ModelAndView();
    }

    /**
     * 将错误信息添加到response中
     */
    public static void printWrite(int status, String msg, HttpServletResponse response) {
        try {
            response.setStatus(status);
            response.setCharacterEncoding("UTF-8");//设置编码
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Content-Type", "Content-Type: text/html; charset=utf-8");
            response.setHeader("Accept Encoding", "utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(msg);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}