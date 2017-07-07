package com.yihu.jw.wx.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.WechatContants;
import com.yihu.jw.util.HttpUtil;
import com.yihu.jw.wx.dao.WechatDao;
import com.yihu.jw.wx.dao.WxAccessTokenDao;
import com.yihu.jw.wx.model.WxAccessToken;
import com.yihu.jw.wx.model.WxWechat;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Service
public class WxAccessTokenService extends BaseJpaService<WxAccessToken, WxAccessTokenDao> {


    private Logger logger= LoggerFactory.getLogger(WxAccessTokenService.class);

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
            //根据wechatCode查找出appid和appSecret
            WxWechat wxWechat = wechatDao.findByCode(wechatCode);
            if(wxWechat==null){
                throw new ApiException(WechatContants.WxConfig.message_fail_wxWechat_is_no_exist, CommonContants.common_error_params_code);
            }
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
            appId = wxWechat.getAppId();
            appSecret = wxWechat.getAppSecret();
            if (StringUtils.isEmpty(appId)){
                throw new ApiException(WechatContants.WxConfig.message_fail_appId_is_null, CommonContants.common_error_params_code);
            }
            if (StringUtils.isEmpty(appSecret)){
                throw new ApiException(WechatContants.WxConfig.message_fail_appSecret_is_null, CommonContants.common_error_params_code);
            }
            String params = "grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
            String result = HttpUtil.sendGet(token_url, params);
            logger.info("--------------微信返回结果:"+result+"---------------");
            JSONObject json = new JSONObject(result);
            if (json.has("access_token")) {
                String token = json.get("access_token").toString();
                String expires_in = json.get("expires_in").toString();
                WxAccessToken newaccessToken = new WxAccessToken();
                newaccessToken.setAccessToken(token);
                newaccessToken.setExpiresIn(Long.parseLong(expires_in));
                newaccessToken.setAddTimestamp(System.currentTimeMillis());
                newaccessToken.setCzrq(new Date());
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
}
