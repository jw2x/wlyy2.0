package com.yihu.jw.base.service.wx;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.fastdfs.FastDFSUtil;
import com.yihu.jw.util.wechat.wxhttp.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 微信二维码
 * Created by Trick on 2018/9/7.
 */
@Service
public class WeChatQrcodeService {

    @Autowired
    private FastDFSUtil fastDFSHelper;
    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    public String getQrcode(String wechatId,String scene) throws Exception{
        String token_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + wxAccessTokenService.getWxAccessTokenById(wechatId);
        String params = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + scene + "\"}}}";
        String result = HttpUtil.sendPost(token_url, params);

        if (!StringUtils.isEmpty(result)) {
            JSONObject json = new JSONObject(result);
            if (json.has("ticket")) {
                // 请求输入流
                InputStream inputStream = null;
                // 下载二维码图片
                URL urlGet = new URL("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="
                        + URLEncoder.encode(json.get("ticket").toString(), "UTF-8"));
                HttpURLConnection connection = (HttpURLConnection) urlGet.openConnection();
                connection.connect();
                inputStream = connection.getInputStream();
                ObjectNode objectNode = fastDFSHelper.upload(inputStream,"png","微信二维码");
                return objectNode.get("fileId").toString().replaceAll("\"", "");
            }
        }

        return null;
    }
}
