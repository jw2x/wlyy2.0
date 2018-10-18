package com.yihu.jw.restmodel.base.saas;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;

/**
 * 租户账号
 * @author yeshijie on 2018/10/16.
 */
@ApiModel(value = "SaasUserVO", description = "租户账号")
public class SaasUserVO extends UuidIdentityVO {

    private String saasId;//租户ID
    private String userId;//用户账号

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
