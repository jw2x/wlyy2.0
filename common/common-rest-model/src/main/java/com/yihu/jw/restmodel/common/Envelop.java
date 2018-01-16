package com.yihu.jw.restmodel.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 信封对象，封装REST接口的返回值内容。包括：
 * - 页码
 * - 页大小
 * - 错误消息
 * - 错误代码
 * - 对象模型
 * <p>
 * 信封对象的返回场景：
 * - API使用者确实无法访问返回头，即一些语言库无法处理HTTP的响应消息，这时候需要以这种形式提供返回值。
 * - API需要支持交叉域请求（通过JSONP）。
 *
 * @author llh
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Envelop<T> implements Serializable {

    private static final long serialVersionUID = 2076324875575488461L;
    private int pageSize = 10;

    private int currPage;

    private int totalPage;

    private int totalCount;

    private List detailModelList;

    private T obj;

    private String errorMsg;

    private String successMsg;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List getDetailModelList() {
        return detailModelList;
    }

    public void setDetailModelList(List detailModelList) {
        this.detailModelList = detailModelList;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalPage() {
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public static Envelop getSuccess(String message) {
        Envelop envelop = new Envelop();
        envelop.setSuccessMsg(message);
        return envelop;
    }

    public static Envelop getSuccess(String message, Object obj) {
        Envelop envelop = new Envelop();
        envelop.setSuccessMsg(message);
        envelop.setObj(obj);
        return envelop;
    }

    public static Envelop getSuccessListWithPage(String message, List detailModelList, int page, int size, Long count) {
        Envelop envelop = new Envelop();
        envelop.setSuccessMsg(message);
        envelop.setPageSize(size);
        envelop.setDetailModelList(detailModelList);
        envelop.setCurrPage(page);
        envelop.setTotalCount(count.intValue());
        return envelop;
    }

    public static Envelop getError(String message, int errorCode) {
        Envelop envelop = new Envelop();
        envelop.setErrorMsg(message);
        return envelop;
    }

    public static Envelop getSuccessList(String message, List objList) {
        Envelop envelop = new Envelop();
        envelop.setSuccessMsg(message);
        envelop.setDetailModelList(objList);
        return envelop;
    }
}