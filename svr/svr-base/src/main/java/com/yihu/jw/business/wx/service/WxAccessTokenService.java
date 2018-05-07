//package com.yihu.jw.business.wx.service;
//
//import com.yihu.jw.base.wx.WxAccessTokenDO;
//import com.yihu.jw.base.wx.WxWechatDO;
//import com.yihu.jw.business.wx.dao.WechatDao;
//import com.yihu.jw.business.wx.dao.WxAccessTokenDao;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.exception.code.ExceptionCode;
//import com.yihu.base.mysql.query.BaseJpaService;
//import com.yihu.jw.rm.base.WechatRequestMapping;
//import com.yihu.jw.util.HttpUtil;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by Administrator on 2017/5/18 0018.
// */
//@Service
//public class WxAccessTokenService extends BaseJpaService<WxAccessTokenDO, WxAccessTokenDao> {
//
//
//    private Logger logger= LoggerFactory.getLogger(WxAccessTokenService.class);
//
//    @Autowired
//    private WxAccessTokenDao wxAccessTokenDao;
//
//    @Autowired
//    private WechatDao wechatDao;
//
//    /**
//     * 根据wechatCode查找最新一条
//     * @param wechatId
//     * @return
//     */
//    public WxAccessTokenDO getWxAccessTokenById(String wechatId) {
//        try {
//            //根据wechatCode查找出appid和appSecret
//            WxWechatDO wxWechat = wechatDao.findById(wechatId);
//            if(wxWechat==null){
//                throw new ApiException(WechatRequestMapping.WxConfig.message_fail_wxWechat_is_no_exist, ExceptionCode.common_error_params_code);
//            }
//            List<WxAccessTokenDO> wxAccessTokens =  wxAccessTokenDao.getWxAccessTokenById(wechatId);
//            if(wxAccessTokens!=null&&wxAccessTokens.size()>0){
//                for (WxAccessTokenDO accessToken : wxAccessTokens) {
//                    if ((System.currentTimeMillis() - accessToken.getAddTimestamp()) < (accessToken.getExpiresIn() * 1000)) {
//                        return accessToken;
//                    } else {
//                        wxAccessTokenDao.delete(accessToken);
//                        break;
//                    }
//                }
//            }
//            String token_url = "https://api.weixin.qq.com/cgi-bin/token";
//            String appId="";
//            String appSecret="";
//            appId = wxWechat.getAppId();
//            appSecret = wxWechat.getAppSecret();
//            if (StringUtils.isEmpty(appId)){
//                throw new ApiException(WechatRequestMapping.WxConfig.message_fail_appId_is_null, ExceptionCode.common_error_params_code);
//            }
//            if (StringUtils.isEmpty(appSecret)){
//                throw new ApiException(WechatRequestMapping.WxConfig.message_fail_appSecret_is_null, ExceptionCode.common_error_params_code);
//            }
//            String params = "grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
//            String result = HttpUtil.sendGet(token_url, params);
//            logger.info("--------------微信返回结果:"+result+"---------------");
//            JSONObject json = new JSONObject(result);
//            if (json.has("access_token")) {
//                String token = json.get("access_token").toString();
//                String expires_in = json.get("expires_in").toString();
//                WxAccessTokenDO newaccessToken = new WxAccessTokenDO();
//                newaccessToken.setAccessToken(token);
//                newaccessToken.setExpiresIn(Long.parseLong(expires_in));
//                newaccessToken.setAddTimestamp(System.currentTimeMillis());
//                newaccessToken.setCzrq(new Date());
//                newaccessToken.setCode(UUID.randomUUID().toString().replace("-",""));
//                newaccessToken.setWechatId(wechatId);
//                wxAccessTokenDao.save(newaccessToken);
//                return newaccessToken;
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
