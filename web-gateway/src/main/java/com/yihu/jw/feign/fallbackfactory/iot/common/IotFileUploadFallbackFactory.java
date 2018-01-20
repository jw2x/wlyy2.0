package com.yihu.jw.feign.fallbackfactory.iot.common;

import com.yihu.jw.feign.iot.common.IotFileUploadFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yeshijie on 2018/01/20.
 */
@Component
public class IotFileUploadFallbackFactory implements FallbackFactory<IotFileUploadFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public IotFileUploadFeign create(Throwable e) {
        return new IotFileUploadFeign() {
            @Override
            public Envelop<UploadVO> uploadImg(@RequestParam(value = "file", required = true) MultipartFile file) {
                tracer.getCurrentSpan().logEvent("文件流上传图片失败:原因:" + e.getMessage());
                return null;
            }

            @Override
            public Envelop<UploadVO> uploadAttachment(@RequestParam(value = "file", required = true) MultipartFile file) {
                tracer.getCurrentSpan().logEvent("文件流上传附件失败:原因:" + e.getMessage());
                return null;
            }

            @Override
            public Envelop<UploadVO> uploadStream(@RequestParam(value = "file", required = true) MultipartFile file) {
                tracer.getCurrentSpan().logEvent("文件流上传文件失败:原因:" + e.getMessage());
                return null;
            }

            @Override
            public Envelop<UploadVO> uploadImages(@RequestBody String jsonData) {
                tracer.getCurrentSpan().logEvent("base64上传图片失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

        };
    }

}
