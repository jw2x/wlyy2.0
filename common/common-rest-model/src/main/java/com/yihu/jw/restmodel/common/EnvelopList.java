package com.yihu.jw.restmodel.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel(value = "ListResult", description = "获取实体列表返回定义")
public class EnvelopList<T> extends BaseEnvelop implements Serializable {

    @ApiModelProperty("列表内容")
    private List<T> detailModelList;

    public EnvelopList(String errorMsg, String successMsg) {
        super(errorMsg, successMsg);
    }

    public List<T> getDetailModelList() {
        return detailModelList;
    }

    public void setDetailModelList(List<T> detailModelList) {
        this.detailModelList = detailModelList;
    }
}