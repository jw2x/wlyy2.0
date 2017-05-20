package com.yihu.jw.wx.service;

import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.wx.dao.WxAccessTokenDao;
import com.yihu.jw.wx.model.WxAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
public class WxAccessTokenService {

    @Autowired
    private WxAccessTokenDao wxAccessTokenDao;

    /**
     * 根据wechatCode查找最新一条
     * @param wechatCode
     * @return
     */
    public WxAccessToken getWxAccessTokenByCode(String wechatCode) {
        List<WxAccessToken> wxAccessTokens =  wxAccessTokenDao.getWxAccessTokenByCode(wechatCode);
        if(wxAccessTokens!=null){
            return wxAccessTokens.get(0);
        }
        return null;
    }

    @Transactional
    public WxAccessToken createWxAccessToken(WxAccessToken wxAccessToken) {
        if (StringUtils.isEmpty(wxAccessToken.getWechatCode())) {
            throw new ApiException(BaseContants.WxAccessToken.message_fail_wechatCode_is_null, CommonContants.common_error_params_code);
        }
        if ("".equals(wxAccessToken.getAddTimestamp())){
            wxAccessToken.setAddTimestamp(new Date().getTime());
        }
        return wxAccessTokenDao.save(wxAccessToken);
    }
}
