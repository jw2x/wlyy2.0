package com.yihu.jw.business.wx.service;

import com.yihu.jw.base.wx.WxWechat;
import com.yihu.jw.business.wx.dao.WechatDao;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rm.base.WechatRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.util.*;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
@Service
public class WechatService extends BaseJpaService<WxWechat, WechatDao> {

    @Autowired
    private WechatDao wechatDao;

    @Transient
    public WxWechat createWechat(WxWechat wechat) {
        String code = UUID.randomUUID().toString().replaceAll("-", "");
        wechat.setId(code);
        if (StringUtils.isEmpty(wechat.getSaasId())) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getStatus())) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_status_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getType())) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_type_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getName())) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        WxWechat wechatTem = wechatDao.findByAppId(wechat.getAppId());
        if (wechatTem != null) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_appId_exist, ExceptionCode.common_error_params_code);
        }
        return wechatDao.save(wechat);
    }

    @Transient
    public WxWechat updateWxchat(WxWechat wechat) {
        if (StringUtils.isEmpty(wechat.getSaasId())) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getStatus())) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_status_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getType())) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_type_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getName())) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        WxWechat wechat1 = findById(wechat.getId());
        if (wechat1 == null) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_wxWechat_is_no_exist, ExceptionCode.common_error_params_code);
        }
        WxWechat wechatTem = wechatDao.findByAppIdExcludeId(wechat.getAppId(), wechat.getId());
        if (wechatTem != null) {
            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_appId_exist, ExceptionCode.common_error_params_code);
        }
        return wechatDao.save(wechat);
    }


    public WxWechat findById(String id) {
        return wechatDao.findById(id);
    }

    @Transient
    public void deleteWechat(String ids, String userId, String userName) {
        if (!StringUtils.isEmpty(ids)) {
            String[] codeArray = ids.split(",");
            for (String code : codeArray) {
                WxWechat wxWechat = wechatDao.findById(code);
                if (wxWechat == null) {
                    throw new ApiException(WechatRequestMapping.WxConfig.message_fail_id_no_exist, ExceptionCode.common_error_params_code);
                }
                wxWechat.setStatus(-1);
                wxWechat.setUpdateUser(userId);
                wxWechat.setUpdateUserName(userName);
                wechatDao.save(wxWechat);
            }
        }
    }

    public List<WxWechat> findAll() {
        return wechatDao.findAll();
    }

    /**
     * key为code ,value为微信名字
     *
     * @return
     */
    public Map<String, String> getAllWechatConfig() {
        List<WxWechat> wechats = findAll();
        Map<String, String> map = new HashMap<>();
        if (null != wechats) {
            for (WxWechat wx : wechats) {
                map.put(wx.getId(), wx.getName());
            }
        }
        return map;
    }
}
