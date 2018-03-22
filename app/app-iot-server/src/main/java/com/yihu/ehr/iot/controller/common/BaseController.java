package com.yihu.ehr.iot.controller.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.ehr.util.rest.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller - 基类
 * Created by Progr1mmer on 2017/3/17.
 */
public class BaseController {

    @Value("${app.clientId}")
    protected String clientId;
    @Value("${server.contextPath}")
    protected String contextPath;
    @Autowired
    protected HttpServletRequest request;

    public Envelop failed(String errMsg) {
        Envelop envelop = new Envelop();
        envelop.setSuccessFlg(false);
        envelop.setErrorMsg(errMsg);
        return envelop;
    }

    public Envelop success(Object object) {
        Envelop envelop = new Envelop();
        envelop.setSuccessFlg(true);
        envelop.setObj(object);
        return envelop;
    }

    public Envelop success(List<Object> objectList) {
        Envelop envelop = new Envelop();
        envelop.setSuccessFlg(true);
        envelop.setDetailModelList(objectList);
        return envelop;
    }

    /**
     * 返回接口处理结果
     *
     * @param code  结果码，成功为200
     * @param msg   结果提示信息
     * @return
     */
    public String error(int code, String msg) {
        try {
            Map<Object, Object> map = new HashMap<Object, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("status", code);
            map.put("msg", msg);
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
