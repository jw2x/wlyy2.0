package com.yihu.ehr.iot.controller.common;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.base.fastdfs.FastDFSHelper;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(IotRequestMapping.Common.file_upload)
@Api(tags = "文件上传相关操作", description = "文件上传相关操作")
public class FileUploadController extends BaseController {

    @Autowired
    private FastDFSHelper fastDFSHelper;
    @Value("${fast-dfs.public-server}")
    private String fastdfs_file_url;

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream_img)
    @ApiOperation(value = "文件流上传图片", notes = "文件流上传图片")
    public Envelop<UploadVO> uploadImg(@ApiParam(value = "文件", required = true)
                                       @RequestParam(value = "file", required = true) MultipartFile file){
        try {
            // 得到文件的完整名称  xxx.txt
            String fullName = file.getOriginalFilename();
            if(StringUtils.isBlank(fullName)){
                return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload_format,IotRequestMapping.api_iot_fail);
            }
            //得到文件类型
            String fileType = fullName.substring(fullName.lastIndexOf(".") + 1).toLowerCase();
            if(StringUtils.isBlank(fileType)||!"jpg,jpeg,png".contains(fileType)){
                return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload_format,IotRequestMapping.api_iot_fail);
            }

            long size = file.getSize();
            long max = 5*1024*1024;
            if(size>max){
                return Envelop.getError("文件大小不超过5M",IotRequestMapping.api_iot_fail);
            }

            String fileName = fullName.substring(0, fullName.lastIndexOf("."));
            //上传到fastdfs
            ObjectNode objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");
            //解析返回的objectNode
            UploadVO uploadVO = new UploadVO();
            uploadVO.setFileName(fileName);
            uploadVO.setFileType(fileType);
            uploadVO.setFullUri(objectNode.get("fid").toString().replaceAll("\"", ""));
            uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fid").toString().replaceAll("\"", ""));
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_create, uploadVO);
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
        }
    }


    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream_attachment)
    @ApiOperation(value = "文件流上传附件", notes = "文件流上传附件")
    public Envelop<UploadVO> uploadAttachment(@ApiParam(value = "文件", required = true)
                                              @RequestParam(value = "file", required = true) MultipartFile file){
        try {
            // 得到文件的完整名称  xxx.txt
            String fullName = file.getOriginalFilename();
            if(StringUtils.isBlank(fullName)){
                return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload_format,IotRequestMapping.api_iot_fail);
            }
            //得到文件类型
            String fileType = fullName.substring(fullName.lastIndexOf(".") + 1).toLowerCase();
            if(StringUtils.isBlank(fileType)||!"doc、docx、pdf、xls、xlsx、jpg、jpeg、png".contains(fileType)){
                return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload_format,IotRequestMapping.api_iot_fail);
            }

            long size = file.getSize();
            long max = 5*1024*1024;
            if(size>max){
                return Envelop.getError("文件大小不超过5M",IotRequestMapping.api_iot_fail);
            }

            String fileName = fullName.substring(0, fullName.lastIndexOf("."));
            //上传到fastdfs
            ObjectNode objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");
            //解析返回的objectNode
            UploadVO uploadVO = new UploadVO();
            uploadVO.setFileName(fileName);
            uploadVO.setFileType(fileType);
            uploadVO.setFullUri(objectNode.get("fid").toString().replaceAll("\"", ""));
            uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fid").toString().replaceAll("\"", ""));
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_create, uploadVO);
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
        }
    }

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_stream)
    @ApiOperation(value = "文件流上传文件", notes = "文件流上传文件")
    public Envelop<UploadVO> uploadStream(@ApiParam(value = "文件", required = true) @RequestParam(value = "file", required = true) MultipartFile file) {
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
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_create, uploadVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
        }
    }

    @PostMapping(value = IotRequestMapping.FileUpload.api_upload_string)
    @ApiOperation(value = "base64上传图片",notes = "base64上传图片")
    public Envelop<UploadVO> uploadImages(@ApiParam(name = "jsonData", value = "头像转化后的输入流") @RequestBody String jsonData) throws Exception {
        try {

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
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
        }
    }

}
