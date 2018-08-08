package com.yihu.iot.service.common;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author yeshijie on 2017/12/7.
 */
@Service
public class FileUploadService extends BaseService{

    @Value("${neiwang.wlyy}")
    private String neiwangWlyy;  //内网的项目地址


    /**
     * 通用的文件上传
     * @param request
     * @param in
     * @param fileName
     * @return
     */
    public UploadVO request(HttpServletRequest request, InputStream in, String fileName) {
        String url = neiwangWlyy + "/svr-iot/fileUpload/commonUpload";//uri请求路径 http://172.19.103.88/wlyy/upload/chat

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String result = "";
        UploadVO uploadVO = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", in, ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            builder.addTextBody("filename", fileName);// 类似浏览器表单提交，对应input的name和value
            if (!org.springframework.util.StringUtils.isEmpty(request.getParameter("type"))) {
                builder.addTextBody("type", request.getParameter("type")); //发送类型
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(jsonObject.getInteger("status")==200){
                JSONObject obj = jsonObject.getJSONObject("obj");
                uploadVO = new UploadVO();
                uploadVO.setFileName(obj.getString("groupName"));
                uploadVO.setFileType(obj.getString("remoteFileName"));
                uploadVO.setFullUri(obj.getString("fid"));
                uploadVO.setFullUrl(obj.getString("fileUrl"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uploadVO;
    }
}
