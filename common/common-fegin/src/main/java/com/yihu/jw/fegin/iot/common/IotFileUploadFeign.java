package com.yihu.jw.fegin.iot.common;

import com.yihu.jw.fegin.fallbackfactory.iot.common.IotFileUploadFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yeshijie on 2018/01/20.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotFileUploadFallbackFactory.class
)
@RequestMapping(IotRequestMapping.api_iot_common)
public interface IotFileUploadFeign {

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream_img)
    public MixEnvelop<UploadVO, UploadVO> uploadImg(@RequestParam(value = "file", required = true) MultipartFile file);


    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream_attachment)
    public MixEnvelop<UploadVO, UploadVO> uploadAttachment(@RequestParam(value = "file", required = true) MultipartFile file);

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream)
    public MixEnvelop<UploadVO, UploadVO> uploadStream(@RequestParam(value = "file", required = true) MultipartFile file);

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_string)
    public MixEnvelop<UploadVO, UploadVO> uploadImages(@RequestBody String jsonData) throws Exception;

}
