package com.yihu.jw.wx.service;

import com.yihu.jw.base.model.Function;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.wx.model.WxTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public class WxTemplateService {

    @Autowired
    private WxTemplateDao wxTemplateDao;


    public WxTemplate createWxMenu(WxTemplate wxTemplate) {
        if (StringUtils.isEmpty(wxTemplate.getCode())) {
            throw new ApiException(BaseContants.WxTemplate.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxTemplate.getWechatCode())) {
            throw new ApiException(BaseContants.Function.message_fail_wechatCode_is_null, CommonContants.common_error_params_code);
        }
        Function functionTmp = functionDao.findByName(wxTemplate.getName());
        if (functionTmp != null) {
            throw new ApiException(BaseContants.Function.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return functionDao.save(wxTemplate);
    }
}
