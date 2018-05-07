//package com.yihu.jw.business.wx.service;
//
//import com.yihu.jw.base.wx.WxWechatDO;
//import com.yihu.jw.business.wx.dao.WechatDao;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.exception.code.ExceptionCode;
//import com.yihu.base.mysql.query.BaseJpaService;
//import com.yihu.jw.rm.base.WechatRequestMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import javax.persistence.Transient;
//import java.util.*;
//
///**
// * Created by Administrator on 2017/5/20 0020.
// */
//@Service
//public class WechatService extends BaseJpaService<WxWechatDO, WechatDao> {
//
//    @Autowired
//    private WechatDao wechatDao;
//
//    @Transient
//    public WxWechatDO createWechat(WxWechatDO wechat) {
//        String code = UUID.randomUUID().toString().replaceAll("-", "");
//        wechat.setId(code);
//        if (StringUtils.isEmpty(wechat.getSaasId())) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(wechat.getStatus())) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_status_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(wechat.getType())) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_type_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(wechat.getName())) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_name_is_null, ExceptionCode.common_error_params_code);
//        }
//        WxWechatDO wechatTem = wechatDao.findByAppId(wechat.getAppId());
//        if (wechatTem != null) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_appId_exist, ExceptionCode.common_error_params_code);
//        }
//        return wechatDao.save(wechat);
//    }
//
//    @Transient
//    public WxWechatDO updateWxchat(WxWechatDO wechat) {
//        if (StringUtils.isEmpty(wechat.getSaasId())) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(wechat.getStatus())) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_status_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(wechat.getType())) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_type_is_null, ExceptionCode.common_error_params_code);
//        }
//        if (StringUtils.isEmpty(wechat.getName())) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_name_is_null, ExceptionCode.common_error_params_code);
//        }
//        WxWechatDO wechat1 = findById(wechat.getId());
//        if (wechat1 == null) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_wxWechat_is_no_exist, ExceptionCode.common_error_params_code);
//        }
//        WxWechatDO wechatTem = wechatDao.findByAppIdExcludeId(wechat.getAppId(), wechat.getId());
//        if (wechatTem != null) {
//            throw new ApiException(WechatRequestMapping.WxConfig.message_fail_appId_exist, ExceptionCode.common_error_params_code);
//        }
//        return wechatDao.save(wechat);
//    }
//
//
//    public WxWechatDO findById(String id) {
//        return wechatDao.findById(id);
//    }
//
//    @Transient
//    public void deleteWechat(String ids, String userId, String userName) {
//        if (!StringUtils.isEmpty(ids)) {
//            String[] codeArray = ids.split(",");
//            for (String code : codeArray) {
//                WxWechatDO wxWechat = wechatDao.findById(code);
//                if (wxWechat == null) {
//                    throw new ApiException(WechatRequestMapping.WxConfig.message_fail_id_no_exist, ExceptionCode.common_error_params_code);
//                }
//                wxWechat.setStatus(-1);
//                wxWechat.setUpdateUser(userId);
//                wxWechat.setUpdateUserName(userName);
//                wechatDao.save(wxWechat);
//            }
//        }
//    }
//
//    public List<WxWechatDO> findAll() {
//        return wechatDao.findAll();
//    }
//
//    /**
//     * key为code ,value为微信名字
//     *
//     * @return
//     */
//    public Map<String, String> getAllWechatConfig() {
//        List<WxWechatDO> wechats = findAll();
//        Map<String, String> map = new HashMap<>();
//        if (null != wechats) {
//            for (WxWechatDO wx : wechats) {
//                map.put(wx.getId(), wx.getName());
//            }
//        }
//        return map;
//    }
//}
