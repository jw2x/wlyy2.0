package com.yihu.jw.wx.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.wx.dao.WxTemplateDao;
import com.yihu.jw.wx.model.WxTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public class WxTemplateService extends BaseJpaService<WxTemplate, WxTemplateDao> {

    @Autowired
    private WxTemplateDao wxTemplateDao;


    public WxTemplate createWxTemplate(WxTemplate wxTemplate) {
        if (StringUtils.isEmpty(wxTemplate.getCode())) {
            throw new ApiException(BaseContants.WxTemplate.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxTemplate.getWechatCode())) {
            throw new ApiException(BaseContants.Function.message_fail_wechatCode_is_null, CommonContants.common_error_params_code);
        }
        return wxTemplateDao.save(wxTemplate);
    }

    public WxTemplate updateWxTemplate(WxTemplate wxTemplate) {
        if (StringUtils.isEmpty(wxTemplate.getCode())) {
            throw new ApiException(BaseContants.WxTemplate.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxTemplate.getWechatCode())) {
            throw new ApiException(BaseContants.Function.message_fail_wechatCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxTemplate.getId())) {
            throw new ApiException(BaseContants.Function.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        return wxTemplateDao.save(wxTemplate);

    }

    public void deleteWxTemplate(String code) {
        WxTemplate wxTemplate = wxTemplateDao.findByCode(code);
        if (wxTemplate == null) {
            throw new ApiException(BaseContants.WxTemplate.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        wxTemplate.setStatus(-1);
        wxTemplateDao.save(wxTemplate);
    }

    public WxTemplate findByCode(String code) {
        WxTemplate wxTemplate = wxTemplateDao.findByCode(code);
        if (wxTemplate == null) {
            throw new ApiException(BaseContants.WxTemplate.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return wxTemplate;
    }

}
