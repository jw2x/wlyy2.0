package com.yihu.jw.healthyhouse.controller.dfs;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.fastdfs.FastDFSUtil;
import com.yihu.fastdfs.config.FastDFSConfig;
import com.yihu.jw.healthyhouse.model.dfs.FileResource;
import com.yihu.jw.healthyhouse.service.dfs.FileResourceService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.house.HealthyHouseMapping;
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
import java.util.*;

/**
 *文件服务
 */
@RestController
@RequestMapping(HealthyHouseMapping.api_healthyHouse_common)
@Api(value = "FastDFSController", description = "FastDFS服务", tags = {"FastDFS服务"})
public class FastDFSController extends EnvelopRestEndpoint {

    @Value("${fastDFS.fastdfs_file_url}")
    private String fastdfs_file_url;
    @Autowired
    private FastDFSUtil fastDFSUtil;
    @Autowired
    private FileResourceService fileResourceService;

    /**
     * 文件上传 - 返回相关索引信息
     * @param file
     * @param creator
     * @param objectId
     * @return
     */
    @PostMapping(value =HealthyHouseMapping.HealthyHouse.FastDFS.UPLOAD )
    @ApiOperation(value = "文件上传", notes = "返回相关索引信息,以及HttpUrl下载连接")
    public ObjEnvelop<FileResource> upload (
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestPart(value = "file") MultipartFile file,
            @ApiParam(name = "creator", value = "创建者", required = true)
            @RequestParam(value = "creator") String creator,
            @ApiParam(name = "objectId", value = "被创建文件所属对象标识", required = true)
            @RequestParam(value = "objectId") String objectId) throws Exception {
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (fileExtension.equals(fileName)) {
            fileExtension = "file";
        }
        //上传
        ObjectNode objectNode = fastDFSUtil.upload(file.getInputStream(), fileExtension, "svr-dfs");
        String groupName = objectNode.get(FastDFSUtil.GROUP_NAME).toString();
        String remoteFileName = objectNode.get(FastDFSUtil.REMOTE_FILE_NAME).toString();
        int fileSize = objectNode.get(FastDFSUtil.FILE_SIZE).asInt();
        String path = groupName.substring(1, groupName.length() - 1) + ":" + remoteFileName.substring(1, remoteFileName.length() - 1);
        //路径存储到mysql数据库
        FileResource fileResource =new FileResource();
        fileResource.setObjectId(objectId);
        fileResource.setStoragePath(path);
        fileResource.setFileSize(String.valueOf(fileSize));
        fileResource.setFileName(fileName);
        fileResource=  fileResourceService.save(fileResource);
        path = groupName.substring(1, groupName.length() - 1) + "/" + remoteFileName.substring(1, remoteFileName.length() - 1);
        fileResource.setStoragePath( fastdfs_file_url + path);
        return success(fileResource);
    }

    /**
     * 文件上传 - 返回相关索引信息，兼容旧接口
     * @param jsonData
     * @return
     * @throws Exception
     */
    @PostMapping(value = HealthyHouseMapping.HealthyHouse.FastDFS.UPLOADJSON)
    @ApiOperation(value = "文件上传", notes = "返回相关索引信息,以及HttpUrl下载连接(兼容旧接口)")
    public ObjEnvelop<FileResource> upload(
            @ApiParam(name = "jsonData", value = "文件资源", required = true)
            @RequestBody String jsonData) throws Exception {
        Map<String, String> paramMap = toEntity(jsonData, Map.class);
        String fileStr = paramMap.get("fileStr");
        byte[] bytes = Base64.getDecoder().decode(fileStr);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        String fileName = paramMap.get("fileName");
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (fileExtension.equals(fileName)) {
            fileExtension = "file";
        }
        ObjectNode objectNode = fastDFSUtil.upload(inputStream, fileExtension, "svr-dfs");
        int fileSize = objectNode.get(FastDFSUtil.FILE_SIZE).asInt();
        String groupName = objectNode.get(FastDFSUtil.GROUP_NAME).toString();
        String remoteFileName = objectNode.get(FastDFSUtil.REMOTE_FILE_NAME).toString();
        String path = groupName.substring(1, groupName.length() - 1) + ":" + remoteFileName.substring(1, remoteFileName.length() - 1);
        //路径存储到mysql数据库
        FileResource fileResource =new FileResource();
        //TODO
        fileResource.setObjectId("111");
        fileResource.setStoragePath(path);
        fileResource.setFileSize(String.valueOf(fileSize));
        fileResource.setFileName(fileName);
        fileResource=  fileResourceService.save(fileResource);
         path = groupName.substring(1, groupName.length() - 1) + "/" + remoteFileName.substring(1, remoteFileName.length() - 1);
        fileResource.setStoragePath( fastdfs_file_url + path);
        return success(fileResource);
    }

    /**
     * 删除资源表对应关系，并且删除fastDfs相对应当文件
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.FastDFS.DELETE_BY_ID)
    @ApiOperation(value = "删除资源表对应关系，并且删除fastDfs相对应文件")
    public Envelop deleteById(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id) throws Exception {
      FileResource fileResource=  fileResourceService.findById(id);
      if(null==fileResource){
          return failed("无相关文件资源");
      }

        String storagePath = fileResource.getStoragePath();
        String groupName = storagePath.split(":")[0];
        String remoteFileName = storagePath.split(":")[1];
        // 删除文件
        fastDFSUtil.delete(groupName, remoteFileName);
        fileResourceService.delete(fileResource);
        return success("success");
    }

    /**
     * 删除资源表对应关系，并且删除fastDfs相对应当文件
     * @param path
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = HealthyHouseMapping.HealthyHouse.FastDFS.DELETE_BY_PATH)
    @ApiOperation(value = "删除资源表对应关系，并且删除fastDfs相对应文件")
    public Envelop deleteByPath(
            @ApiParam(name = "path", value = "文件路径", required = true)
            @RequestParam(value = "path") String path) throws Exception {
        if (path.split(":").length < 2) {
            return failed("参数有误");
        }
        // 删除文件
        fastDFSUtil.delete(path.split(":")[0], path.split(":")[1]);
        List<FileResource> fileResourceList=  fileResourceService.findByField("storagePath",path);
        StringBuilder ids = new StringBuilder();
        for (FileResource fileResource : fileResourceList) {
            String id= fileResource.getId();
            ids.append(id + ",");
        }
        // 删除mysql数据
        fileResourceService.delete(ids);
        return success("success");
    }

    /**
     * 下载文件
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FastDFS.DOWNLOAD_BY_ID)
    @ApiOperation(value = "下载文件(byId)")
    public Envelop downloadById (
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id) throws Exception {
        FileResource fileResource=  fileResourceService.findById(id);
        String storagePath = fileResource.getStoragePath();
        String groupName = storagePath.split(":")[0];
        String remoteFileName = storagePath.split(":")[1];
        byte[] bytes = fastDFSUtil.download(groupName, remoteFileName);
        String fileStream = new String(Base64.getEncoder().encode(bytes));
        if (!StringUtils.isEmpty(fileStream)) {
            return success(fileStream);
        }
        return failed("FileStream Is Empty");
    }

    /**
     * 下载文件
     * @param path
     * @return
     * @throws Exception
     */
    @GetMapping(value = HealthyHouseMapping.HealthyHouse.FastDFS.DOWNLOAD_BY_PATH)
    @ApiOperation(value = "下载文件(byPath)")
    public Envelop downloadByPath(
            @ApiParam(name = "path", value = "文件路径", required = true)
            @RequestParam(value = "path") String path) throws Exception {
        if (path.split(":").length < 2) {
            return failed("参数有误");
        }
        String groupName = path.split(":")[0];
        String remoteFileName = path.split(":")[1];
        byte[] bytes = fastDFSUtil.download(groupName, remoteFileName);
        String fileStream = new String(Base64.getEncoder().encode(bytes));
        if (!StringUtils.isEmpty(fileStream)) {
            return success(fileStream);
        }
        return failed("FileStream Is Empty");
    }


}