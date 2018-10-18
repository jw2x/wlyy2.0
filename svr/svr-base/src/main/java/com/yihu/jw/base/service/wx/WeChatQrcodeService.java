package com.yihu.jw.base.service.wx;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.fastdfs.FastDFSUtil;
import com.yihu.jw.util.wechat.wxhttp.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    public String getQrcode(HttpServletRequest request, String wechatId, String scene) throws Exception{
        String token_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + wxAccessTokenService.getWxAccessTokenById(wechatId).getAccessToken();
        String params = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + scene + "\"}}}";
        //服务号必须是通过腾讯认证，每年是300元，如果没有认证而导致的错误提示，那就去认证
        //微信登录提示48001,{"errcode":48001,"errmsg":"api unauthorized, hints: [ req_id: 1QoCla0699ns81 ]"}
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

//                String pathFile = request.getSession().getServletContext().getRealPath("/")
//                        + File.separator + "qrcode" + File.separator + wechatId+"_"+scene+".png";
                String path = WeChatQrcodeService.class.getResource("/").getPath().replace("/WEB-INF/classes/", "")
                        + File.separator + "qrcode" ;
                File dir = new File(path);

                if(!dir.exists()){
                    dir.mkdir();
                }
                path+= File.separator + wechatId+"_"+scene+".png";
                File file = new File(path);
                // 保存文件
                FileOutputStream outputStream = new FileOutputStream(file);

                byte[] data = new byte[1024];
                int len = 0;

                while ((len = inputStream.read(data)) != -1) {
                    outputStream.write(data, 0, len);
                }

                InputStream input = new FileInputStream(file);

                ObjectNode objectNode = fastDFSHelper.upload(input,"png","");

                File del = new File(path);

                if(del.exists()&&del.isFile()){
                    del.delete();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
                return objectNode.get("fileId").toString().replaceAll("\"", "");
            }
        }

        return "";
    }

//    public void write(InputStream inputStream)throws Exception{
//        String file = "D:\\workspace\\file\\test.png";
//        // 保存文件
//        FileOutputStream outputStream = new FileOutputStream(file);
//
//        byte[] data = new byte[1024];
//        int len = 0;
//
//        while ((len = inputStream.read(data)) != -1) {
//            outputStream.write(data, 0, len);
//        }
//
//        if (outputStream != null) {
//            outputStream.close();
//        }
//
//    }
}
