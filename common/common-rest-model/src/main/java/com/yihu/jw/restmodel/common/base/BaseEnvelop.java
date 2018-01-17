package com.yihu.jw.restmodel.common.base;

/**
 * Created by chenweida on 2018/1/16.
 */
public class BaseEnvelop {

    protected String errorCode;

    protected String errorMsg;

    protected String successMsg;

    protected Integer status;

    public BaseEnvelop() {
    }

    public BaseEnvelop(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
