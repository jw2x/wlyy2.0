package com.yihu.jw.entity.base.dict;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 错误码
 * @author yeshijie on 2018/9/26.
 */
@Entity
@Table(name = "base_error_code")
public class ErrorCodeDO extends UuidIdentityEntityWithOperator {

    private String model;//模块
    private String errorCode;//提示码
    private String errorMsg;//提示内容

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
