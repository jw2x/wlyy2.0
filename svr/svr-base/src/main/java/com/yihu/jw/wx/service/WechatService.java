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

import javax.persistence.Transient;
import java.util.Date;
import java.util.UUID;

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
        wechat.setCode(code);
        if (StringUtils.isEmpty(wechat.getSaasId())) {
            throw new ApiException(WxContants.Wechat.message_fail_saasId_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getStatus())) {
            throw new ApiException(WxContants.Wechat.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getType())) {
            throw new ApiException(WxContants.Wechat.message_fail_type_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getName())) {
            throw new ApiException(WxContants.Wechat.message_fail_name_is_null, CommonContants.common_error_params_code);
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
        //设置创建时间和修改时间
        Date date = new Date();
        wechat.setCreateTime(date);
        wechat.setUpdateTime(date);

        return wechatDao.save(wechat);
    }

    @Transient
    public WxWechat updateWxchat(WxWechat wechat) {
        if (StringUtils.isEmpty(wechat.getCode())) {
            throw new ApiException(WxContants.Wechat.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getSaasId())) {
            throw new ApiException(WxContants.Wechat.message_fail_saasId_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getStatus())) {
            throw new ApiException(WxContants.Wechat.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getType())) {
            throw new ApiException(WxContants.Wechat.message_fail_type_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getName())) {
            throw new ApiException(WxContants.Wechat.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getAppId())) {
            throw new ApiException(WxContants.Wechat.message_fail_appId_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wechat.getAppSecret())) {
            throw new ApiException(WxContants.Wechat.message_fail_appSecret_is_null, CommonContants.common_error_params_code);
        }
        Long id = wechat.getId();
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(WxContants.Wechat.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        WxWechat wechat1 = findById(id);
        if(wechat1 == null){
            throw new ApiException(WxContants.Wechat.message_fail_wxWechat_is_no_exist, CommonContants.common_error_params_code);
        }
        //设置修改时间创建时间
        wechat.setCreateTime(wechat1.getCreateTime());
        wechat.setUpdateTime(new Date());
        WxWechat wechatTem = wechatDao.findByAppIdExcludeCode(wechat.getAppId(),wechat.getCode());
        if(wechatTem!=null){
            throw new ApiException(WxContants.Wechat.message_fail_appId_exist, CommonContants.common_error_params_code);
        }
        return wechatDao.save(wechat);
    }

    public WxWechat findByCode(String code) {
        WxWechat wxWechat = wechatDao.findByCode(code);
        return wxWechat;
    }

    public WxWechat findById(Long id){
        return wechatDao.findById(id);
    }

    @Transient
    public void deleteWechat(String codes) {
        if(!StringUtils.isEmpty(codes)){
            String[] codeArray = codes.split(",");
            for(String code:codeArray){
                WxWechat wxWechat = wechatDao.findByCode(code);
                if (wxWechat == null) {
                    throw new ApiException(WxContants.Wechat.message_fail_code_no_exist, CommonContants.common_error_params_code);
                }
                wxWechat.setStatus(-1);
                wechatDao.save(wxWechat);
            }
        }
    }
}
