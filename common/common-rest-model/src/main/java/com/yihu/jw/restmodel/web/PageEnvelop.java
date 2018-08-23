package com.yihu.jw.restmodel.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
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
 * 快速集成 {@link com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint}
 * @author llh
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "PageEnvelop<T>", description = "分页查询返回")
public class PageEnvelop<T> extends Envelop implements Serializable {

    private static final long serialVersionUID = 2076324875575488461L;

    @ApiModelProperty("当前页")
    private int currPage = 1;

    @ApiModelProperty("每页大小 默认10")
    private int pageSize = 10;

    @ApiModelProperty("总共多少页")
    private int totalPage;

    @ApiModelProperty("总共多少数据")
    private int totalCount;

    @ApiModelProperty("所有满足条件的实体")
    private List<T> detailModelList = new ArrayList<>(0);

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDetailModelList() {
        return detailModelList;
    }

    public void setDetailModelList(List<T> detailModelList) {
        this.detailModelList = detailModelList;
    }

    public static PageEnvelop getSuccess(String message) {
        PageEnvelop envelop = new PageEnvelop();
        envelop.setMessage(message);
        return envelop;
    }

    public static PageEnvelop getSuccessListWithPage(String message, List detailModelList, int page, int size, Long count) {
        PageEnvelop envelop = new PageEnvelop();
        envelop.setMessage(message);
        envelop.setPageSize(size);
        envelop.setDetailModelList(detailModelList);
        envelop.setCurrPage(page);
        envelop.setTotalCount(count.intValue());
        return envelop;
    }

    public static PageEnvelop getError(String message, int errorCode) {
        PageEnvelop envelop = new PageEnvelop();
        envelop.setMessage(message);
        return envelop;
    }

    public static PageEnvelop getSuccessList(String message, List objList) {
        PageEnvelop envelop = new PageEnvelop();
        envelop.setMessage(message);
        envelop.setDetailModelList(objList);
        return envelop;
    }
}