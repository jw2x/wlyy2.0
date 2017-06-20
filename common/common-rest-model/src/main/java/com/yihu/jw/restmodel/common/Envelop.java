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
public class Envelop implements Serializable {

    private static final long serialVersionUID = 2076324875575488461L;
    private Integer pageSize ;

    private Integer currPage;

    private Integer totalPage;

    private Integer totalCount;

    private List detailModelList;

    private Object obj;

    private String errorMsg;

    private String successMsg;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
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


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getTotalPage() {
        if (totalCount != null && pageSize != null) {
            if (totalCount % pageSize == 0) {
                totalPage = totalCount / pageSize;
            } else {
                totalPage = totalCount / pageSize + 1;
            }
        }
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
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

    public static Envelop getSuccessListWithPage(String message, List detailModelList, Integer page, Integer size, Long count) {
        Envelop envelop = new Envelop();
        envelop.setSuccessMsg(message);
        envelop.setPageSize(size);
        envelop.setDetailModelList(detailModelList);
        envelop.setCurrPage(page);
        envelop.setTotalCount(count.intValue());
        return envelop;
    }

    public static Envelop getError(String message, Integer errorCode) {
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