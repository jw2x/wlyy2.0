package com.yihu.ehr.iot.service.common;

import com.yihu.ehr.iot.constant.ServiceApi;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeshijie on 2017/12/7.
 */
@Service
public class FileUploadService extends BaseService{

    /**
     * 文件流上传图片
     * @param file
     * @return
     */
    public Envelop<UploadVO> uploadImg(MultipartFile file) throws IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("file", file);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.FileUpload.UploadImg, params);
        Envelop<UploadVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }


    /**
     * 文件流上传附件
     * @param file
     * @return
     * @throws Exception
     */
    public Envelop<UploadVO> uploadAttachment(MultipartFile file) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("file", file);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.FileUpload.UploadattAchment, params);
        Envelop<UploadVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * 文件流上传文件
     * @param file
     * @return
     * @throws Exception
     */
    public Envelop<UploadVO> uploadStream(MultipartFile file) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("file", file);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.FileUpload.UploadStream, params);
        Envelop<UploadVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

    /**
     * base64上传图片
     * @param jsonData
     * @return
     * @throws Exception
     */
    public Envelop<UploadVO> uploadImages(@ApiParam(name = "jsonData", value = "头像转化后的输入流") @RequestBody String jsonData) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("jsonData", jsonData);
        HttpResponse response = HttpHelper.post(iotUrl + ServiceApi.FileUpload.UploadString, params);
        Envelop<UploadVO> envelop = objectMapper.readValue(response.getBody(),Envelop.class);
        return envelop;
    }

}
