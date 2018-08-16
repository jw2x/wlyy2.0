package com.yihu.jw.util.wechat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Trick on 2018/8/16.
 */
public class WeiXinMessageReplyUtils {
    /**
     * 回复图文消息
     *
     * @param toUser
     * @param fromUser
     * @param articles
     * @return
     * @throws Exception
     */
    public static String replyNewsMessage(String toUser,String fromUser,List<Map<String,String>> articles) throws Exception {
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
}
