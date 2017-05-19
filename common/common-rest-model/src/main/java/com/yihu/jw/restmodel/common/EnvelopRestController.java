package com.yihu.jw.restmodel.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.util.date.DateUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * REST风格控控制器基类。此控制器用于对API进行校验，并处理平台根层级的业务，如API参数校验，错误及返回码设定等。
 * <p>
 * 根层级的校验，如果是正确的，直接返回HTTP代码200，若出错，则会将HTTP返回代码设置为1X或2X，并在HTTP响应体中包含响应的信息。
 * HTTP响应体格式为JSON。
 * + 成功：会根据各业务逻辑自行决定要返回的数据，各业务模块的返回结构不同。
 * + 失败：{"code":"错误代码", "message":"错误原因"}
 *
 * @author zhiyong
 * @author Sand
 */
public class EnvelopRestController {

    /**
     * 返回一个信封对象。信封对象的返回场景参见 Envelop.
     *
     * @param modelList
     * @param totalCount
     * @return
     */
    protected Envelop getResult(List modelList, int totalCount) {
        Envelop envelop = new Envelop();
        envelop.setSuccessFlg(true);
        envelop.setDetailModelList(modelList);
        envelop.setTotalCount(totalCount);

        return envelop;
    }
    //Json转实体类
    public <T> T toEntity(String json, Class<T> entityCls) {
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(DateUtil.yyyy_MM_dd_HH_mm_ss));
            T entity = objectMapper.readValue(json, entityCls);
            return entity;
        } catch (IOException ex) {
            throw new ApiException( "Unable to parse json, " + ex.getMessage(),CommonContants.common_error_params_code);
        }
    }
}
