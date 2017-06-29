package com.yihu.jw.wx.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.util.MessageUtil;
import com.yihu.jw.wx.dao.WxGraphicMessageDao;
import com.yihu.jw.wx.model.WxGraphicMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
@Service
public class WxGraphicMessageService extends BaseJpaService<WxGraphicMessage, WxGraphicMessageDao> {

    @Autowired
    private WxGraphicMessageDao wxGraphicMessageDao;

    @Transient
    public WxGraphicMessage createWxGraphicMessage(WxGraphicMessage wxGraphicMessage) {
        String code = UUID.randomUUID().toString().replaceAll("-", "");
        wxGraphicMessage.setCode(code);
        if (StringUtils.isEmpty(wxGraphicMessage.getStatus())) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxGraphicMessage.getTitle())) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_title_is_null, CommonContants.common_error_params_code);
        }
        WxGraphicMessage wxGraphicMessageTem = wxGraphicMessageDao.findByCode(wxGraphicMessage.getCode());
        if (wxGraphicMessageTem != null) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_code_exist, CommonContants.common_error_params_code);
        }
        return wxGraphicMessageDao.save(wxGraphicMessage);
    }

    @Transient
    public WxGraphicMessage updateWxGraphicMessage(WxGraphicMessage wxGraphicMessage) {
        if (StringUtils.isEmpty(wxGraphicMessage.getCode())) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wxGraphicMessage.getStatus())) {
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        Long id = wxGraphicMessage.getId();
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(WxContants.Wechat.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        //根据id查找
        WxGraphicMessage wxGraphicMessage1 = findById(id);
        if(wxGraphicMessage1==null){
            throw new ApiException(WxContants.WxGraphicMessage.message_fail_wxGraphicMessage_is_no_exist, CommonContants.common_error_params_code);
        }
        return wxGraphicMessageDao.save(wxGraphicMessage);
    }

    public WxGraphicMessage findByCode(String code) {
        WxGraphicMessage WxGraphicMessage = wxGraphicMessageDao.findByCode(code);
        return WxGraphicMessage;
    }

    public WxGraphicMessage findById(Long id) {
      return wxGraphicMessageDao.findById(id);
    }

    @Transient
    public void deleteWxGraphicMessage(String codes, String userCode, String userName) {
        if(!StringUtils.isEmpty(codes)){
            String[] codeArray = codes.split(",");
            for(String code:codeArray){
                WxGraphicMessage wxGraphicMessage = findByCode(code);
                wxGraphicMessage.setStatus(-1);
                wxGraphicMessage.setUpdateUser(userCode);
                wxGraphicMessage.setUpdateUserName(userName);
                wxGraphicMessageDao.save(wxGraphicMessage);
            }
        }
    }



    private String replyNewsMessage(String toUser,String fromUser,List<Map<String,String>> articles) throws Exception {
        if(articles == null || articles.size() < 1){
            throw new Exception("图文信息不能为空!");
        }

        StringBuilder result = new StringBuilder();
        result.append("<xml>");
        result.append("<ToUserName>" + toUser + "</ToUserName>");
        result.append("<FromUserName>" + fromUser + "</FromUserName>");
        result.append("<CreateTime>" + new Date().getTime() + "</CreateTime>");
        result.append("<MsgType>news</MsgType>");
        result.append("<ArticleCount>" + articles.size() + "</ArticleCount>");
        result.append("<Articles>");

        for(Map<String,String>  article : articles){
            result.append("<item>");
            result.append("<Title>" +  article.get("Title") +"</Title>");
            result.append("<Description>" + article.get("Description") + "</Description>");
            result.append("<PicUrl>" + article.get("PicUrl") + "</PicUrl>");
            result.append("<Url>" + article.get("Url") + "</Url>");
            result.append("</item>");
        }

        result.append("</Articles>");
        result.append("</xml>");

        return result.toString();
    }

    /**
     * 发送图文消息
     * @param codes
     * @param request
     * @return
     */
    public String sendGraphicMessages(String codes, HttpServletRequest request) {
        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            //用户openid
            String fromUserName = requestMap.get("FromUserName");
            //微信公众号
            String toUserName = requestMap.get("ToUserName");
            // 图文信息
            List<Map<String,String>> articles =  new ArrayList<>();
            if(codes!=null){
                String[] codeArray = codes.split(",");
                for(String code: codeArray){
                    WxGraphicMessage graphicMessage = findByCode(code);
                    Map<String,String> article = new HashMap<>();
                    article.put("Url",graphicMessage.getUrl());
                    article.put("Title", graphicMessage.getTitle());
                    article.put("Description",graphicMessage.getDescription());
                    article.put("PicUrl",graphicMessage.getPicUrl());
                    articles.add(article);
                }
            }
            // 构建回复消息XML
            return replyNewsMessage(fromUserName, toUserName, articles);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 发送图文消息
     * @param codes
     * @param fromUserName   用户的openId
     * @param toUserName
     * @return
     */
    public String sendGraphicMessages(String codes, String fromUserName,String toUserName) {
        try {
        // 图文信息
            List<Map<String,String>> articles =  new ArrayList<>();
            if(codes!=null){
                String[] codeArray = codes.split(",");
                for(String code: codeArray){
                    WxGraphicMessage graphicMessage = findByCode(code);
                    Map<String,String> article = new HashMap<>();
                    article.put("Url",graphicMessage.getUrl());
                    article.put("Title", graphicMessage.getTitle());
                    article.put("Description",graphicMessage.getDescription());
                    article.put("PicUrl",graphicMessage.getPicUrl());
                    articles.add(article);
                }
            }
            // 构建回复消息XML
            return replyNewsMessage(fromUserName, toUserName, articles);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
