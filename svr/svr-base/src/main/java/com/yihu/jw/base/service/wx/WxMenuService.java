package com.yihu.jw.base.service.wx;

import com.yihu.jw.base.dao.wx.WechatDao;
import com.yihu.jw.base.dao.wx.WxMenuDao;
import com.yihu.jw.base.dao.wx.WxMenuJsonDao;
import com.yihu.jw.base.util.HttpUtil;
import com.yihu.jw.entity.base.wx.WxMenuDO;
import com.yihu.jw.entity.base.wx.WxMenuJsonDO;
import com.yihu.jw.entity.base.wx.WxWechatDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.base.WechatRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@Service
public class WxMenuService extends BaseJpaService<WxMenuDO, WxMenuDao>{

    @Autowired
    private WxAccessTokenService wxAccessTokenService;
    @Autowired
    private WxMenuJsonDao wxMenuJsonDao;
    @Autowired
    private WechatDao wechatDao;

    public MixEnvelop createWxMenu(String wxId){

//        String url = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=" ;
        String url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ wxAccessTokenService.getWxAccessTokenById(wxId).getAccessToken();
        WxMenuJsonDO wxMenuJsonDO = wxMenuJsonDao.findByWechatIdAndStatus(wxId,1);
        String params = wxMenuJsonDO.getContent();
        WxWechatDO wechatDO =  wechatDao.findById(wxId);
        // 替换服务器地址、APPID
        params = params.replaceAll("server_url", wechatDO.getBaseUrl());
        params = params.replaceAll("appId", wechatDO.getAppId());

        String jsonStr = HttpUtil.sendPost(url, params);
        JSONObject result = new JSONObject(jsonStr);
        if (result != null && result.get("errcode").toString().equals("0") && result.getString("errmsg").equals("ok")) {
            MixEnvelop.getSuccess(WechatRequestMapping.WxMenu.message_success_update);
        }
        return MixEnvelop.getError(result.toString());

    }

}
