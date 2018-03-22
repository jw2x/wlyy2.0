package com.yihu.ehr.iot.controller.common;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.base.fastdfs.FastDFSHelper;
import com.yihu.ehr.iot.service.common.FileUploadService;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.Map;

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
    @Autowired
    private FileUploadService fileUploadService;
    @Value("${neiwang.enable}")
    private Boolean isneiwang;  //如果不是内网项目要转到到内网在上传

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
            ObjectNode objectNode = null;
            //解析返回的objectNode
            UploadVO uploadVO = null;
            if(isneiwang){
                objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");
                uploadVO = new UploadVO();
                uploadVO.setFileName(fileName);
                uploadVO.setFileType(fileType);
                uploadVO.setFullUri(objectNode.get("fid").toString().replaceAll("\"", ""));
                uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fid").toString().replaceAll("\"", ""));
            }else {
                uploadVO = fileUploadService.request(request,file.getInputStream(),fullName);
                if(uploadVO==null){
                    return Envelop.getError("文件上传失败",IotRequestMapping.api_iot_fail);
                }
            }
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
            ObjectNode objectNode = null;
            //解析返回的objectNode
            UploadVO uploadVO = null;
            if(isneiwang){
                objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");
                uploadVO = new UploadVO();
                uploadVO.setFileName(fileName);
                uploadVO.setFileType(fileType);
                uploadVO.setFullUri(objectNode.get("fid").toString().replaceAll("\"", ""));
                uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fid").toString().replaceAll("\"", ""));
            }else {
                uploadVO = fileUploadService.request(request,file.getInputStream(),fullName);
                if(uploadVO==null){
                    return Envelop.getError("文件上传失败",IotRequestMapping.api_iot_fail);
                }
            }

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
            ObjectNode objectNode = null;
            //解析返回的objectNode
            UploadVO uploadVO = null;
            if(isneiwang){
                objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");
                uploadVO = new UploadVO();
                uploadVO.setFileName(fileName);
                uploadVO.setFileType(fileType);
                uploadVO.setFullUri(objectNode.get("fid").toString().replaceAll("\"", ""));
                uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fid").toString().replaceAll("\"", ""));
            }else {
                uploadVO = fileUploadService.request(request,file.getInputStream(),fullName);
                if(uploadVO==null){
                    return Envelop.getError("文件上传失败",IotRequestMapping.api_iot_fail);
                }
            }

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

            InputStream inputStream = new ByteArrayInputStream(in);
            ObjectNode objectNode = null;
            //上传到fastdfs
            String fileType = "png";
            //解析返回的objectNode
            UploadVO uploadVO = null;
            if(isneiwang){
                objectNode = fastDFSHelper.upload(inputStream, fileType, "");
                String groupName = objectNode.get("groupName").toString();
                String remoteFileName = objectNode.get("remoteFileName").toString();
                uploadVO = new UploadVO();
                uploadVO.setFileName(remoteFileName);
                uploadVO.setFileType(groupName);
                uploadVO.setFullUri(objectNode.get("fid").toString().replaceAll("\"", ""));
                uploadVO.setFullUrl(fastdfs_file_url + objectNode.get("fid").toString().replaceAll("\"", ""));
            }else {
                uploadVO = fileUploadService.request(request,inputStream,"");
                if(uploadVO ==null){
                    return Envelop.getError("文件上传失败",IotRequestMapping.api_iot_fail);
                }
            }

            //返回文件路径
            return Envelop.getSuccess(IotRequestMapping.FileUpload.message_success_upload, uploadVO);
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
        }
    }

    @RequestMapping(value = "commonUpload", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("公共的文件上传")
    public Envelop<UploadVO> commonUpload(HttpServletRequest request) {
        InputStream in = null;
        try {
            String paths = request.getParameter("filePaths");
            ObjectNode result = null;
            if (StringUtils.isBlank(paths)) {
                //为空是文件上传
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                // 文件保存的临时路径
                Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
                String fileName = null;
                for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                    // 上传文件
                    MultipartFile mf = entity.getValue();
                    fileName = mf.getOriginalFilename();
                    in = mf.getInputStream();
                    result = fastDFSHelper.upload(in, fileName.substring(fileName.lastIndexOf(".") + 1), "");
                }
            } else {
                String[] pathArr = paths.split(",");
                for (String path : pathArr) {
                    //传路径自己去路径上传
                    File file = new File(path);
                    String fileName = null;
                    if (file.exists()) {
                        fileName = file.getName();
                        in = new FileInputStream(file);
                        result = fastDFSHelper.upload(in, fileName.substring(fileName.lastIndexOf(".") + 1), "");
                    }
                }
            }
            //解析返回的objectNode
            UploadVO uploadVO = new UploadVO();
            uploadVO.setFullUri(result.get("fid").toString().replaceAll("\"", ""));
            uploadVO.setFullUrl(fastdfs_file_url + result.get("fid").toString().replaceAll("\"", ""));
            return Envelop.getSuccess(IotRequestMapping.FileUpload.message_success_upload, result);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
