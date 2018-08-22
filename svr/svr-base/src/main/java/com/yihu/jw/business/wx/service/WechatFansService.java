package com.yihu.jw.business.wx.service;

import com.yihu.jw.util.wechat.wxhttp.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Trick on 2018/8/22.
 */
@Service
public class WechatFansService {

    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    /**
     * 创建分组
     * @param wxId
     * @param param
     * @return
     */
    public String createWxTag(String wxId,String param){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token="+wxAccessTokenService.getWxAccessTokenById(wxId).getAccessToken();
        return HttpUtil.sendPost(url,param);
    }

    /**
     * 查询分组
     * @param wxId
     * @return
     */
    public String findWxTag(String wxId){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token="+wxAccessTokenService.getWxAccessTokenById(wxId).getAccessToken();
        return HttpUtil.sendGet(url);
    }

    /**
     * 更新分组
     * @param wxId
     * @param param
     * @return
     */
    public String updateWxTag(String wxId,String param){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token="+wxAccessTokenService.getWxAccessTokenById(wxId).getAccessToken();
        return HttpUtil.sendPost(url,param);
    }

    /**
     * 删除分组
     * @param wxId
     * @param param
     * @return
     */
    public String deleteWxTag(String wxId,String param){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token="+wxAccessTokenService.getWxAccessTokenById(wxId).getAccessToken();
        return HttpUtil.sendPost(url,param);
    }

    /**
     *  批量为用户打标签
     * @param wxId
     * @param param
     * @return
     */
    public String createBatchtagging(String wxId,String param){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token="+wxAccessTokenService.getWxAccessTokenById(wxId).getAccessToken();
        return HttpUtil.sendPost(url,param);
    }

    /**
     * 批量为用户取消标签
     * @param wxId
     * @param param
     * @return
     */
    public String cancelBatchuntagging(String wxId,String param){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token="+wxAccessTokenService.getWxAccessTokenById(wxId).getAccessToken();
        return HttpUtil.sendPost(url,param);
    }

    /**
     * 获取用户身上的标签列表
     * @param wxId
     * @param param
     * @return
     */
    public String getidlist(String wxId,String param){
        String url = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token="+wxAccessTokenService.getWxAccessTokenById(wxId).getAccessToken();
        return HttpUtil.sendPost(url,param);
    }


}
