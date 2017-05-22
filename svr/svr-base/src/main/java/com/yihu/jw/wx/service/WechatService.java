package com.yihu.jw.wx.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.wx.dao.WechatDao;
import com.yihu.jw.wx.model.WxWechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
@Service
public class WechatService extends BaseJpaService<WxWechat, WechatDao> {

    @Autowired
    private WechatDao wechatDao;


    public WxWechat createWechat(WxWechat wechat) {//Wechat     //WxWechat
        if (StringUtils.isEmpty(wechat.getCode())) {
            throw new ApiException(WxContants.Wechat.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getAppId())) {
            throw new ApiException(WxContants.Wechat.message_fail_appId_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getAppSecret())) {
            throw new ApiException(WxContants.Wechat.message_fail_appSecret_is_null, CommonContants.common_error_params_code);
        }
        WxWechat wechatTem = wechatDao.findByAppId(wechat.getAppId());
        if (wechatTem != null) {
            throw new ApiException(WxContants.Wechat.message_fail_appId_exist, CommonContants.common_error_params_code);
        }
        return wechatDao.save(wechat);
    }

    public WxWechat updateWxchat(WxWechat wechat) {
        if (StringUtils.isEmpty(wechat.getCode())) {
            throw new ApiException(WxContants.Wechat.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getAppId())) {
            throw new ApiException(WxContants.Wechat.message_fail_appId_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getAppSecret())) {
            throw new ApiException(WxContants.Wechat.message_fail_appSecret_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getCode())) {
            throw new ApiException(WxContants.Wechat.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        WxWechat wechatTem = wechatDao.findByAppIdExcludeCode(wechat.getAppId(),wechat.getCode());
        if(wechatTem!=null){
            throw new ApiException(WxContants.Wechat.message_fail_appId_exist, CommonContants.common_error_params_code);
        }
        return wechatDao.save(wechat);
    }

    public WxWechat findByCode(String code) {
        WxWechat wxWechat = wechatDao.findByCode(code);
        if (wxWechat == null) {
            throw new ApiException(WxContants.Wechat.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return wxWechat;
    }

    public void deleteWechat(String code) {
        WxWechat wxWechat = wechatDao.findByCode(code);
        if (wxWechat == null) {
            throw new ApiException(WxContants.Wechat.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        wxWechat.setStatus("-1");
        wechatDao.save(wxWechat);
    }
}
