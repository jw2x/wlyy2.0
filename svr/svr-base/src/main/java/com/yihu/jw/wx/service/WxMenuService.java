package com.yihu.jw.wx.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.wx.dao.WxMenuDao;
import com.yihu.jw.wx.model.WxMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@Service
public class WxMenuService extends BaseJpaService<WxMenu, WxMenuDao> {

    @Autowired
    private WxMenuDao wxMenuDao;

    public WxMenu createWxMenu(WxMenu wxMenu) {
        if (StringUtils.isEmpty(wxMenu.getWechatCode())) {
            throw new ApiException(WxContants.WxMenu.message_fail_wechatCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxMenu.getName())) {
            throw new ApiException(WxContants.WxMenu.message_fail_wxMenuName_is_null, CommonContants.common_error_params_code);
        }
        return wxMenuDao.save(wxMenu);
    }

    @Transient
    public WxMenu updateWxMenu(WxMenu wxMenu) {
        if (StringUtils.isEmpty(wxMenu.getWechatCode())) {
            throw new ApiException(WxContants.WxMenu.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxMenu.getName())) {
            throw new ApiException(WxContants.WxMenu.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxMenu.getId())) {
            throw new ApiException(WxContants.WxMenu.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        return wxMenuDao.save(wxMenu);
    }

    @Transient
    public void deleteWxMenu(String code) {
        WxMenu wxMenu = wxMenuDao.findByCode(code);
        if (wxMenu == null) {
            throw new ApiException(WxContants.WxMenu.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        wxMenu.setStatus(-1);
        wxMenuDao.save(wxMenu);
    }

    public WxMenu findByCode(String code) {
        return wxMenuDao.findByCode(code);
    }
}
