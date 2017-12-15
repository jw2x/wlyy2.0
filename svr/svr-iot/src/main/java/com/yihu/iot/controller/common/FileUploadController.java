package com.yihu.iot.controller.common;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.base.fastdfs.FastDFSHelper;
import com.yihu.iot.vo.common.UploadVO;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Base64;

/**
 * @author yeshijie on 2017/12/7.
 */
@RestController
@RequestMapping(IotRequestMapping.api_iot_common)
@Api(value = "文件上传相关操作", description = "文件上传相关操作")
public class FileUploadController extends EnvelopRestController{

    @Autowired
    private FastDFSHelper fastDFSHelper;
    @Value("${fastDFS.fastdfs_file_url}")
    private String fastdfs_file_url;

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream)
    @ApiOperation(value = "文件流上传文件", notes = "文件流上传文件")
    public Envelop uploadStream(@ApiParam(value = "文件", required = true) @RequestParam(value = "file", required = true) MultipartFile file) {
        try {
            // 得到文件的完整名称  xxx.txt
            String fullName = file.getOriginalFilename();
            //得到文件类型
            String fileType = fullName.substring(fullName.lastIndexOf(".") + 1).toLowerCase();
            String fileName = fullName.substring(0, fullName.lastIndexOf("."));
            //上传到fastdfs
            ObjectNode objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");
            //解析返回的objectNode
            UploadVO uploadVO = new UploadVO();
            uploadVO.setFileName(fileName);
            uploadVO.setFileType(fileType);
            uploadVO.setFullUri(objectNode.get("fid").toString().replaceAll("\"", ""));
            uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fid").toString().replaceAll("\"", ""));
            return Envelop.getSuccess(IotRequestMapping.DeviceSupplier.message_success_create, uploadVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
        }
    }

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_string)
    @ApiOperation(value = "base64上传图片",notes = "base64上传图片")
    public Envelop uploadImages(@ApiParam(name = "jsonData", value = "头像转化后的输入流") @RequestBody String jsonData) throws Exception {
        try {
            if(jsonData == null){
                return Envelop.getError(IotRequestMapping.FileUpload.message_fail_jsonData_is_null, ExceptionCode.common_error_params_code);
            }
            String date = URLDecoder.decode(jsonData,"UTF-8");
            String[] fileStreams = date.split(",");
            String is = URLDecoder.decode(fileStreams[1],"UTF-8").replace(" ","+");
            byte[] in = Base64.getDecoder().decode(is);

            String pictureName = fileStreams[0].substring(0,fileStreams[0].length()-1);
            String fileExtension = pictureName.substring(pictureName.lastIndexOf(".") + 1).toLowerCase();
            String description = null;
            if ((pictureName != null) && (pictureName.length() > 0)) {
                int dot = pictureName.lastIndexOf('.');
                if ((dot > -1) && (dot < (pictureName.length()))) {
                    description = pictureName.substring(0, dot);
                }
            }
            InputStream inputStream = new ByteArrayInputStream(in);
            ObjectNode objectNode = fastDFSHelper.upload(inputStream, "png", "");
            String groupName = objectNode.get("groupName").toString();
            String remoteFileName = objectNode.get("remoteFileName").toString();
            //解析返回的objectNode
            UploadVO uploadVO = new UploadVO();
            uploadVO.setFileName(remoteFileName);
            uploadVO.setFileType(groupName);
            uploadVO.setFullUri(objectNode.get("fid").toString().replaceAll("\"", ""));
            uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fid").toString().replaceAll("\"", ""));
            //返回文件路径
            return Envelop.getSuccess(IotRequestMapping.FileUpload.message_success_upload, uploadVO);
        }catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

}
