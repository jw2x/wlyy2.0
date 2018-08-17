package com.yihu.jw.business.wx.service;

import com.yihu.jw.entity.base.wx.WxReplySceneDO;
import com.yihu.jw.business.wx.dao.WechatDao;
import com.yihu.jw.business.wx.dao.WxReplySceneDao;
import com.yihu.jw.entity.base.wx.WxWechatDO;
import com.yihu.jw.util.wechat.WeiXinMessageReplyUtils;
import com.yihu.jw.util.wechat.WeiXinMessageUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Trick on 2018/8/16.
 */
@Service
public class WechatCoreService {

    private Logger logger = LoggerFactory.getLogger(WechatCoreService.class);

    @Autowired
    private WechatDao wechatDao;

    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    @Autowired
    private WxReplySceneDao wxReplySceneDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 微信推送消息处理
     *
     * @param request
     */
    public String messageProcess(HttpServletRequest request) {
        try {
            String returnStr = "";
            // 将解析结果存储在HashMap中
            Map<String, String> message = new HashMap();

            // 从request中取得输入流
            InputStream inputStream = request.getInputStream();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List elementList = root.elements();

            // 遍历所有子节点
            for (Object e : elementList) {
                Element ele = (Element) e;
                message.put(ele.getName(), ele.getText());
            }
            logger.info("weixin  event:" + message.toString());
            // 释放资源
            inputStream.close();
            // 消息处理
            returnStr = messageProcess(message);

            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    /**
     * 对接收到的消息进行处理
     *
     * @param message
     * @return
     */
    private String messageProcess(Map<String, String> message) throws Exception {
        String msgType = message.get("MsgType");
        String result = "";

        switch (msgType) {
            case WeiXinMessageUtils.REQ_MESSAGE_TYPE_EVENT:  // 事件消息
                logger.info("MsgType:event");
                result = eventProcess(message);
                break;
            case WeiXinMessageUtils.RESP_MESSAGE_TYPE_TEXT:  // 回复文本消息
                //回复文本消息 处理
                logger.info("MsgType:text");
                result = textProcess(message);
                break;
            default:
                break;
        }

        return result;
    }

    /**
     * 事件细分处理：
     * 1.扫描二维码事件
     * 2.订阅事件
     * 3.取消订阅事件
     * 4.菜单点击
     * @param message
     * @return
     */
    public String eventProcess(Map<String, String> message) throws Exception{
        String result = "";
        String event = message.get("Event");

        switch (event) {
            case WeiXinMessageUtils.EVENT_TYPE_SCAN: // 扫描二维码事件
                result = scanEventProcess(message);
                break;
            case WeiXinMessageUtils.EVENT_TYPE_SUBSCRIBE: // 订阅事件
                if (message.containsKey("EventKey") && StringUtils.isNotEmpty(message.get("EventKey")) && message.containsKey("Ticket")
                        && StringUtils.isNotEmpty(message.get("Ticket"))) {
                    result = scanEventProcess(message);
                } else {
                    result = subscribeEventProcess(message);
                }
                break;
            case WeiXinMessageUtils.EVENT_TYPE_UNSUBSCRIBE: // 取消订阅事件
                result = unsubscribeEventProcess(message);
                break;
            case WeiXinMessageUtils.EVENT_TYPE_CLICK: //菜单点击
                result = this.clickProcess(message);
            default:
                break;
        }

        return result;
    }

    public String scanEventProcess(Map<String, String> message){

        return "";
    }

    /**
     * 搜索关注事件
     * @param message
     * @return
     */
    public String subscribeEventProcess(Map<String, String> message) throws Exception{
        String toUserName = message.get("ToUserName");
        List<WxReplySceneDO> scenes = wxReplySceneDao.findByAppOriginIdAndMsgTypeAndEventAndStatus(toUserName,WeiXinMessageUtils.RESP_MESSAGE_TYPE_TEXT,WeiXinMessageUtils.EVENT_TYPE_SUBSCRIBE,1);
        if(scenes!=null&&scenes.size()>0){
            WxReplySceneDO scene = scenes.get(0);
            if(StringUtils.isNotBlank(scene.getScene())){
                return getGraphicXMl(scene.getScene(),scene.getWechatId(),message);
            }
        }
        return "";
    }

    public String unsubscribeEventProcess(Map<String, String> message){
        return "";
    }

    public String clickProcess(Map<String, String> message){
        return "";
    }


    /**
     * 文本消息动态生成图文消息
     * @param message
     * @return
     * @throws Exception
     */
    public String  textProcess(Map<String, String> message) throws Exception{
        String toUserName = message.get("ToUserName");
        String content = message.get("Content");
        List<WxReplySceneDO> scenes = wxReplySceneDao.findByAppOriginIdAndMsgTypeAndStatus(toUserName,WeiXinMessageUtils.RESP_MESSAGE_TYPE_TEXT,1);

        if(scenes!=null&&scenes.size()>0){
            for(WxReplySceneDO scene:scenes){
                if(StringUtils.isNotBlank(scene.getContent())
                        &&content.equals(scene.getContent())
                        &&StringUtils.isNotBlank(scene.getScene())){
                    //根据场景值，动态生成图文消息回复文斌
                    return getGraphicXMl(scene.getScene(),scene.getWechatId(),message);
                }
            }
        }

        return null;
    }

    /**
     * 构造图文消息XML
     * @param secene
     * @param wxId
     * @param message
     * @return
     * @throws Exception
     */
    public String getGraphicXMl(String secene,String wxId,Map<String, String> message)throws Exception{
        List<Map<String,Object>> group = getGraphicGroupByScene(secene,wxId);
        String result = "";
        if(group!=null&&group.size()>0){
            List<Map<String, String>> articles = new ArrayList<>();

            for(Map<String,Object> graphic : group){
                // 图文信息
                Map<String, String> article = new HashMap<>();
                String url = (String)graphic.get("url");
                String baseUrl = (String)graphic.get("baseUrl");
                String appId = (String)graphic.get("appId");
                String title = (String)graphic.get("title");
                String description = (String)graphic.get("description");
                String picUrl = (String)graphic.get("picUrl");

                url = url.replace("{server}",baseUrl).replace("{appId}",appId);

                article.put("Url", url);
                article.put("Title", title);
                article.put("Description", description);
                article.put("PicUrl", picUrl);
            }
            result = WeiXinMessageReplyUtils.replyNewsMessage(message.get("FromUserName"), message.get("ToUserName"), articles);
        }
        return result;
    }

    /**
     * 获取指定场景下所有图文消息分组
     * @param secene
     * @param wxId
     * @return
     */
    public List<Map<String,Object>> getGraphicGroupByScene(String secene,String wxId) {
        String sql = "SELECT " +
                " m.title, " +
                " m.description, " +
                " m.url, " +
                " m.pic_url AS picUrl," +
                " w.app_id AS appId," +
                " w.base_url AS baseUrl" +
                " FROM " +
                " wx_graphic_scene_group g " +
                " JOIN wx_graphic_message m ON g.graphic_id = m.id " +
                " JOIN wx_wechat w ON w.id = g.wechat_id" +
                " WHERE " +
                " m.wechat_id = '"+wxId+"' " +
                " AND g.wechat_id = '"+wxId+"' " +
                " AND g.scene = '"+secene+"' " +
                " AND m.`status` = 1 " +
                " ORDER BY m.sort ASC";
        List<Map<String,Object>> group = jdbcTemplate.queryForList(sql);
        return group;
    }

    /**
     * 判断是否是xml结构
     */
    public boolean isXML(String value) {
        try {
            DocumentHelper.parseText(value);
        } catch (DocumentException e) {
            return false;
        }
        return true;
    }

    public List<WxWechatDO> findAll(){
        return wechatDao.findAll();
    }


}
