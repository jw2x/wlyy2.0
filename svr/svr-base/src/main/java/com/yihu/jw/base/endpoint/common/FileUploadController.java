package com.yihu.jw.base.endpoint.common;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.fastdfs.FastDFSUtil;
import com.yihu.jw.base.util.ErrorCodeUtil;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
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
 * 文件上传不在微服务中处理
 * @author yeshijie on 2017/12/7.
 */
@RestController
@RequestMapping(BaseRequestMapping.FileUpload.PREFIX)
@Api(tags = "文件上传相关操作", description = "文件上传相关操作")
public class FileUploadController extends EnvelopRestEndpoint {

    @Autowired
    private FastDFSUtil fastDFSHelper;
    @Value("${fastDFS.fastdfs_file_url}")
    private String fastdfs_file_url;
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @PostMapping(value = BaseRequestMapping.FileUpload.UPLOAD_STREAM_IMG)
    @ApiOperation(value = "文件流上传图片", notes = "文件流上传图片")
    public ObjEnvelop<UploadVO> uploadImg(@ApiParam(value = "文件", required = true)
                                       @RequestParam(value = "file", required = true) MultipartFile file) throws Exception{
        // 得到文件的完整名称  xxx.txt
        String fullName = file.getOriginalFilename();
        if(StringUtils.isBlank(fullName)){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.FileUpload.FAIL_UPLOAD_FORMAT), ObjEnvelop.class);
        }
        //得到文件类型
        String fileType = fullName.substring(fullName.lastIndexOf(".") + 1).toLowerCase();
        if(StringUtils.isBlank(fileType)||!"jpg,jpeg,png".contains(fileType)){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.FileUpload.FAIL_UPLOAD_FORMAT), ObjEnvelop.class);
        }

        long size = file.getSize();
        long max = 5*1024*1024;
        if(size>max){
            String msg = String.format(BaseErrorCode.FileUpload.FAIL_FILE_SIZE, 5);
            return failed(msg, ObjEnvelop.class);
        }

        String fileName = fullName.substring(0, fullName.lastIndexOf("."));
        //上传到fastdfs
        ObjectNode objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");
        //解析返回的objectNode
        UploadVO uploadVO = new UploadVO();
        uploadVO.setFileName(fileName);
        uploadVO.setFileType(fileType);
        uploadVO.setFullUri(objectNode.get("fileId").toString().replaceAll("\"", ""));
        uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fileId").toString().replaceAll("\"", ""));
        return success("上传成功", uploadVO);
    }


    @PostMapping(value = BaseRequestMapping.FileUpload.UPLOAD_STREAM_ATTACHMENT)
    @ApiOperation(value = "文件流上传附件", notes = "文件流上传附件")
    public ObjEnvelop<UploadVO> uploadAttachment(@ApiParam(value = "文件", required = true)
                                       @RequestParam(value = "file", required = true) MultipartFile file) throws Exception{
        // 得到文件的完整名称  xxx.txt
        String fullName = file.getOriginalFilename();
        if(StringUtils.isBlank(fullName)){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.FileUpload.FAIL_UPLOAD_FORMAT), ObjEnvelop.class);
        }
        //得到文件类型
        String fileType = fullName.substring(fullName.lastIndexOf(".") + 1).toLowerCase();
        if(StringUtils.isBlank(fileType)||!"doc、docx、pdf、xls、xlsx、jpg、jpeg、png".contains(fileType)){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.FileUpload.FAIL_UPLOAD_FORMAT), ObjEnvelop.class);
        }

        long size = file.getSize();
        long max = 5*1024*1024;
        if(size>max){
            String msg = String.format(BaseErrorCode.FileUpload.FAIL_FILE_SIZE, 5);
            return failed(msg, ObjEnvelop.class);
        }

        String fileName = fullName.substring(0, fullName.lastIndexOf("."));
        //上传到fastdfs
        ObjectNode objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");
        //解析返回的objectNode
        UploadVO uploadVO = new UploadVO();
        uploadVO.setFileName(fileName);
        uploadVO.setFileType(fileType);
        uploadVO.setFullUri(objectNode.get("fileId").toString().replaceAll("\"", ""));
        uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fileId").toString().replaceAll("\"", ""));
        return success("上传成功", uploadVO);
    }

    @PostMapping(value = BaseRequestMapping.FileUpload.UPLOAD_STREAM)
    @ApiOperation(value = "文件流上传文件", notes = "文件流上传文件")
    public ObjEnvelop<UploadVO> uploadStream(@ApiParam(value = "文件", required = true)
        @RequestParam(value = "file", required = true) MultipartFile file) throws Exception{
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
        uploadVO.setFullUri(objectNode.get("fileId").toString().replaceAll("\"", ""));
        uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fileId").toString().replaceAll("\"", ""));
        return success("上传成功", uploadVO);
    }

    @PostMapping(value = BaseRequestMapping.FileUpload.UPLOAD_STRING)
    @ApiOperation(value = "base64上传图片",notes = "base64上传图片")
    public ObjEnvelop<UploadVO> uploadImages(@ApiParam(name = "jsonData", value = "头像转化后的输入流")
                                                 @RequestBody String jsonData) throws Exception {
        if(jsonData == null){
            return failed(errorCodeUtil.getErrorMsg(BaseErrorCode.FileUpload.FILE_NULL), ObjEnvelop.class);
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
        uploadVO.setFullUri(objectNode.get("fileId").toString().replaceAll("\"", ""));
        uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fileId").toString().replaceAll("\"", ""));
        //返回文件路径
        return success("上传成功", uploadVO);
    }

}
