package com.yihu.health.controller.common;

import com.yihu.ehr.util.rest.Envelop;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Controller - 基类
 * Created by Progr1mmer on 2017/3/17.
 */
public class BaseController {

    @Value("${app.clientId}")
    protected String clientId;
    @Value("${server.contextPath}")
    protected String contextPath;

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

}
