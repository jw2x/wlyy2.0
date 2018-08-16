package com.yihu.jw.business.wx.controller;

import com.yihu.jw.business.wx.service.WechatCoreService;
import com.yihu.jw.entity.base.wx.WxWechatDO;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.WechatRequestMapping;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Trick on 2018/8/16.
 */
@RestController
@RequestMapping(WechatRequestMapping.api_common)
@Api(value = "微信回调及事件处理", description = "微信回调及事件处理")
public class WechatCoreController extends EnvelopRestEndpoint {

    private Logger logger = LoggerFactory.getLogger(WechatCoreController.class);

    @Autowired
    private WechatCoreService wechatCoreService;

    /**
     * 微信接口验证
     *
     * @param request
     * @param response
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) {
        try {
            String signature = request.getParameter("signature").toString();
            String timestamp = request.getParameter("timestamp").toString();
            String nonce = request.getParameter("nonce").toString();
            String echostr = request.getParameter("echostr").toString();

            if (validate(signature, timestamp, nonce)) {
                // 验证成功，返回验证码
                response.getWriter().print(echostr);
            } else {
                // 验证失败
                response.setStatus(401);
            }
        } catch (Exception e) {
            // 服务器错误
            response.setStatus(500);
        }
    }

    /**
     * 接收居民微信回复的消息
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void receiveMessages(HttpServletRequest request, HttpServletResponse response) {
        try {
            String signature = request.getParameter("signature").toString();
            String timestamp = request.getParameter("timestamp").toString();
            String nonce = request.getParameter("nonce").toString();

            if (validate(signature, timestamp, nonce)) {
                String xmlStr = wechatCoreService.messageProcess(request);
                // 判断返回值是xml、json格式（取关是空串）
                Boolean flag = wechatCoreService.isXML(xmlStr);
                if (xmlStr == "error") {
                    // 服务器错误
                    response.setStatus(500);
                } else if (!flag && StringUtils.isNotEmpty(xmlStr)) {
                    JSONObject json = new JSONObject(xmlStr);
//                    if (json.has("openId")) {
//                        if (StringUtils.isNotEmpty(json.getString("openId")) && !("undefined".equals(json.getString("openId")))) {
//                            pushMsgTask.putWxMsg(getAccessToken(), json.getInt("type"), json.getString("openId"), null, json);
//                        }
//                    }
                } else {
                    // 返回消息(图文消息)
                    response.setHeader("Content-type", "text/html;charset=UTF-8");
                    //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
                    response.setCharacterEncoding("UTF-8");

                    logger.info(xmlStr);

                    response.getWriter().print(xmlStr);
                }
            } else {
                // 验证失败
                response.setStatus(401);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 服务器错误
            response.setStatus(500);
        }
    }


    /**
     * 微信推送消息验证
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @throws NoSuchAlgorithmException
     */
    private boolean validate(String signature, String timestamp, String nonce) throws NoSuchAlgorithmException {

        //查询平台所有微信token
        List<WxWechatDO> wxs =  wechatCoreService.findAll();

        if(wxs!=null&&wxs.size()>0){

            for(WxWechatDO wx :wxs){

                String token = wx.getToken();
                // 字典序排序
                String[] array = new String[]{token, timestamp, nonce};
                Arrays.sort(array);
                //连接字典序排序后字符串
                String content = "";
                for (String str : array) {
                    content += str;
                }
                // 解析
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                String decodeStr = "";
                byte[] bytes = md.digest(content.getBytes());
                decodeStr = byteToStr(bytes);
                //验证
                if (StringUtils.isNotEmpty(decodeStr) && decodeStr.equals(signature.toUpperCase())) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 将字节转换为字符
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    /**
     * 将字节数组转换为字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
}
