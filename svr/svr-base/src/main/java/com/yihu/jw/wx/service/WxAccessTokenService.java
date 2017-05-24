package com.yihu.jw.wx.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.util.HttpUtil;
import com.yihu.jw.wx.dao.WechatDao;
import com.yihu.jw.wx.dao.WxAccessTokenDao;
import com.yihu.jw.wx.model.WxAccessToken;
import com.yihu.jw.wx.model.WxWechat;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springside.modules.utils.Clock;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Service
public class WxAccessTokenService extends BaseJpaService<WxAccessToken, WxAccessTokenDao> {

    @Autowired
    private WxAccessTokenDao wxAccessTokenDao;

    @Autowired
    private WechatDao wechatDao;

    /**
     * 根据wechatCode查找最新一条
     * @param wechatCode
     * @return
     */
    public WxAccessToken getWxAccessTokenByCode(String wechatCode) {
        try {
            List<WxAccessToken> wxAccessTokens =  wxAccessTokenDao.getWxAccessTokenByCode(wechatCode);
            if(wxAccessTokens!=null&&wxAccessTokens.size()>0){
                for (WxAccessToken accessToken : wxAccessTokens) {
                    if ((System.currentTimeMillis() - accessToken.getAddTimestamp()) < (accessToken.getExpiresIn() * 1000)) {
                        return accessToken;
                    } else {
                        wxAccessTokenDao.delete(accessToken);
                        break;
                    }
                }
            }
            String token_url = "https://api.weixin.qq.com/cgi-bin/token";
            String appId="";
            String appSecret="";
            //根据wechatCode查找出appid和appSecret
            WxWechat wxWechat = wechatDao.findByCode(wechatCode);
            if(wxWechat==null){
                return null;
            }
            appId = wxWechat.getAppId();
            appSecret = wxWechat.getAppSecret();
            String params = "grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
            String result = HttpUtil.sendGet(token_url, params);
            JSONObject json = new JSONObject(result);
            if (json.has("access_token")) {
                String token = json.get("access_token").toString();
                String expires_in = json.get("expires_in").toString();
                WxAccessToken newaccessToken = new WxAccessToken();
                newaccessToken.setAccessToken(token);
                newaccessToken.setExpiresIn(Long.parseLong(expires_in));
                newaccessToken.setAddTimestamp(System.currentTimeMillis());
                newaccessToken.setCzrq(new Clock.DefaultClock().getCurrentDate());
                newaccessToken.setCode(UUID.randomUUID().toString().replace("-",""));
                newaccessToken.setWechatCode(wechatCode);
                wxAccessTokenDao.save(newaccessToken);
                return newaccessToken;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public WxAccessToken createWxAccessToken(WxAccessToken wxAccessToken) {
        if (StringUtils.isEmpty(wxAccessToken.getWechatCode())) {
            throw new ApiException(WxContants.WxAccessToken.message_fail_wechatCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxAccessToken.getExpiresIn())) {
            throw new ApiException(WxContants.WxAccessToken.message_fail_expiresIn_is_null, CommonContants.common_error_params_code);
        }
        return wxAccessTokenDao.save(wxAccessToken);
    }
}
