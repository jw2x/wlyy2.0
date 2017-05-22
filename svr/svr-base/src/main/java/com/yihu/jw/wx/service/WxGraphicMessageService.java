package com.yihu.jw.wx.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.wx.dao.WxGraphicMessageDao;
import com.yihu.jw.wx.model.WxGraphicMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
@Service
public class WxGraphicMessageService extends BaseJpaService<WxGraphicMessage, WxGraphicMessageDao> {

    @Autowired
    private WxGraphicMessageDao wxGraphicMessageDao;

    @Transient
    public WxGraphicMessage createWxGraphicMessage(WxGraphicMessage wxGraphicMessage) {
        if (StringUtils.isEmpty(wxGraphicMessage.getCode())) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxGraphicMessage.getValue())) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_value_is_null, CommonContants.common_error_params_code);
        }
        WxGraphicMessage wxGraphicMessageTem = wxGraphicMessageDao.findByCode(wxGraphicMessage.getCode());
        if (wxGraphicMessageTem != null) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_code_exist, CommonContants.common_error_params_code);
        }
        return wxGraphicMessageDao.save(wxGraphicMessage);
    }

    @Transient
    public WxGraphicMessage updateWxchat(WxGraphicMessage wxGraphicMessage) {
        if (StringUtils.isEmpty(wxGraphicMessage.getCode())) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxGraphicMessage.getValue())) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_value_is_null, CommonContants.common_error_params_code);
        }
        return wxGraphicMessageDao.save(wxGraphicMessage);
    }

    public WxGraphicMessage findByCode(String code) {
        WxGraphicMessage WxGraphicMessage = wxGraphicMessageDao.findByCode(code);
        if (WxGraphicMessage == null) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return WxGraphicMessage;
    }

    @Transient
    public void deleteWxGraphicMessage(String code) {
        WxGraphicMessage WxGraphicMessage = findByCode(code);
        WxGraphicMessage.setStatus(-1);
        wxGraphicMessageDao.save(WxGraphicMessage);
    }
}
