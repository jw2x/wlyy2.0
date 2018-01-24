package com.yihu.jw.controller.iot.common;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.iot.IotCommonContants;
import com.yihu.jw.feign.iot.common.IotFileUploadFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yeshijie on 2017/12/7.
 */
@RestController
@RequestMapping(IotCommonContants.Common.file_upload)
@Api(tags = "文件上传相关操作", description = "文件上传相关操作")
public class FileUploadController {

    @Autowired
    private IotFileUploadFeign iotFileUploadFeign;

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream_img)
    @ApiOperation(value = "文件流上传图片", notes = "文件流上传图片")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<UploadVO> uploadImg(@ApiParam(value = "文件", required = true)
                                       @RequestParam(value = "file", required = true) MultipartFile file){
        return iotFileUploadFeign.uploadImg(file);
    }


    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream_attachment)
    @ApiOperation(value = "文件流上传附件", notes = "文件流上传附件")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<UploadVO> uploadAttachment(@ApiParam(value = "文件", required = true)
                                       @RequestParam(value = "file", required = true) MultipartFile file){
        return iotFileUploadFeign.uploadAttachment(file);
    }

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream)
    @ApiOperation(value = "文件流上传文件", notes = "文件流上传文件")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<UploadVO> uploadStream(@ApiParam(value = "文件", required = true) @RequestParam(value = "file", required = true) MultipartFile file) {
        return iotFileUploadFeign.uploadStream(file);
    }

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_string)
    @ApiOperation(value = "base64上传图片",notes = "base64上传图片")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<UploadVO> uploadImages(@ApiParam(name = "jsonData", value = "头像转化后的输入流") @RequestBody String jsonData) throws Exception {
        return iotFileUploadFeign.uploadImages(jsonData);
    }

}
