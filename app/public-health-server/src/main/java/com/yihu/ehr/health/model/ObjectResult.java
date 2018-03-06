package com.yihu.ehr.health.model;


/**
 * Created by hzp on 20170315.
 */
public class ObjectResult extends Result {
    private Object data;
    private Object obj;
    private String errorMsg;
    private int errorCode;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ObjectResult()
    {}

    public ObjectResult(boolean successFlg,String message){
        super.setSuccessFlg(successFlg);
        super.setMessage(message);
    }

}
