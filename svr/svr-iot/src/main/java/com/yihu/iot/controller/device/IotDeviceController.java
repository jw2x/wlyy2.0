package com.yihu.iot.controller.device;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.base.fastdfs.FastDFSHelper;
import com.yihu.iot.dao.device.IotDeviceImportRecordDao;
import com.yihu.iot.service.device.IotDeviceService;
import com.yihu.jw.iot.device.IotDeviceDO;
import com.yihu.jw.iot.device.IotDeviceImportRecordDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.common.ExistVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.device)
@Api(tags = "设备管理相关操作", description = "设备管理相关操作")
public class IotDeviceController extends EnvelopRestController{

    private Logger logger = LoggerFactory.getLogger(IotDeviceController.class);
    @Autowired
    private IotDeviceService iotDeviceService;
    @Autowired
    private FastDFSHelper fastDFSHelper;
    @Value("${fastDFS.fastdfs_file_url}")
    private String fastdfs_file_url;
    @Autowired
    private IotDeviceImportRecordDao iotDeviceImportRecordDao;

    @PostMapping(value = IotRequestMapping.Device.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建设备", notes = "创建设备")
    public Envelop<IotDeviceVO> create(@ApiParam(name = "json_data", value = "", defaultValue = "")
                          @RequestBody String jsonData) {
        try {
            IotDeviceDO iotDevice = toEntity(jsonData, IotDeviceDO.class);
            if(StringUtils.isBlank(iotDevice.getDeviceSn())){
                return Envelop.getError("sn码不能为空");
            }
            if(iotDeviceService.findByDeviceSn(iotDevice.getDeviceSn())!=null){
                return Envelop.getError("SN码重复，并不允许新增");
            }
            if(StringUtils.isNotBlank(iotDevice.getSimNo())&&iotDeviceService.findByDeviceSn(iotDevice.getSimNo())!=null){
                return Envelop.getError("SIM卡号重复，并不允许新增");
            }
            iotDeviceService.create(iotDevice);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_create);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }


    @GetMapping(value = IotRequestMapping.Device.api_getById)
    @ApiOperation(value = "根据code查找设备", notes = "根据code查找设备")
    public Envelop<IotDeviceVO> findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        try {
            IotDeviceDO iotDeviceDO = iotDeviceService.findById(id);
            IotDeviceVO iotDeviceVO = convertToModel(iotDeviceDO,IotDeviceVO.class);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_find, iotDeviceVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Device.isSnExist)
    @ApiOperation(value = "sn码是否存在", notes = "sn码是否存在")
    public Envelop<ExistVO> isSnExist(@ApiParam(name = "sn", value = "sn")
                                           @RequestParam(value = "sn", required = true) String sn
    ) {
        try {
            IotDeviceDO iotDeviceDO = iotDeviceService.findByDeviceSn(sn);
            ExistVO existVO = new ExistVO(iotDeviceDO==null?0:1);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_find, existVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Device.isSimExist)
    @ApiOperation(value = "sim卡号是否存在", notes = "sim卡号是否存在")
    public Envelop<ExistVO> isSimExist(@ApiParam(name = "sim", value = "sim")
                                           @RequestParam(value = "sim", required = true) String sim
    ) {
        try {
            IotDeviceDO iotDeviceDO = iotDeviceService.findBySimNo(sim);
            ExistVO existVO = new ExistVO(iotDeviceDO==null?0:1);
            return Envelop.getSuccess(IotRequestMapping.Device.message_success_find, existVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Device.api_queryPage)
    @ApiOperation(value = "分页查找设备", notes = "分页查找设备")
    public Envelop<IotDeviceVO> findProductPageByCompanyId(@ApiParam(name = "sn", value = "SN码或SIM卡号", defaultValue = "")
                                                           @RequestParam(value = "sn", required = false) String sn,
                                                           @ApiParam(name = "hospital", value = "社区医院", defaultValue = "")
                                                           @RequestParam(value = "hospital", required = false) String hospital,
                                                           @ApiParam(name = "orderId", value = "订单id", defaultValue = "")
                                                           @RequestParam(value = "orderId", required = false) String orderId,
                                                           @ApiParam(name = "purcharseId", value = "采购id", defaultValue = "")
                                                           @RequestParam(value = "purcharseId", required = true) String purcharseId,
                                                           @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                           @RequestParam(value = "page", required = false) Integer page,
                                                           @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                           @RequestParam(value = "size", required = false) Integer size){
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            String filters = "";
            String semicolon = "";
            if(StringUtils.isNotBlank(orderId)){
                filters += semicolon +"orderId="+orderId;
                semicolon = ";";
            }
            if(StringUtils.isNotBlank(purcharseId)){
                filters += semicolon +"purcharseId="+purcharseId;
                semicolon = ";";
            }
            if(StringUtils.isNotBlank(hospital)){
                filters += semicolon +"hospital="+hospital;
                semicolon = ";";
            }
            if(StringUtils.isNotBlank(sn)){
                filters = "deviceSn?"+sn+" g1;simNo?"+sn+" g1";
                semicolon = ";";
            }
            if(StringUtils.isBlank(filters)){
                filters+= semicolon + "del=1";
            }
            String sorts = "-updateTime";
            //得到list数据
            List<IotDeviceDO> list = iotDeviceService.search(null, filters, sorts, page, size);
            //获取总数
            long count = iotDeviceService.getCount(filters);

            //DO转VO
            List<IotDeviceVO> iotDeviceVOList = convertToModels(list,new ArrayList<>(list.size()),IotDeviceVO.class);

            return Envelop.getSuccessListWithPage(IotRequestMapping.Company.message_success_find_functions,iotDeviceVOList, page, size,count);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Device.importDevice)
    @ApiOperation(value = "设备导入", notes = "设备导入")
    public Envelop<IotDeviceImportRecordVO> uploadStream(@ApiParam(value = "文件", required = true)
                                          @RequestParam(value = "file", required = true) MultipartFile file,
                                          @ApiParam(name = "purcharseId", value = "采购id", defaultValue = "")
                                          @RequestParam(value = "purcharseId", required = true) String purcharseId) {
        try {
            // 得到文件的完整名称  xxx.txt
            String fullName = file.getOriginalFilename();
            //得到文件类型
            String fileType = fullName.substring(fullName.lastIndexOf(".") + 1).toLowerCase();
            if(!"xls".equals(fileType)){
                return Envelop.getError("文件格式不正确");
            }
            IotDeviceImportRecordDO recordDO = iotDeviceImportRecordDao.findByPurchaseId(purcharseId);
            if(recordDO!=null){
                return Envelop.getError("正在导入中，请耐心等待");
            }
            String fileName = fullName.substring(0, fullName.lastIndexOf("."));
            HSSFWorkbook wb = null;
            IotDeviceImportRecordVO vo = null;
            try {
                wb = new HSSFWorkbook(file.getInputStream());
                HSSFSheet sheet = wb.getSheetAt(0);
                if(sheet==null||sheet.getLastRowNum()<2){
                    return Envelop.getError("文件内容不正确");
                }
                HSSFRow row = sheet.getRow(0);
                if(row.getLastCellNum()<3){
                    return Envelop.getError("文件内容不正确");
                }
                //上传到fastdfs
                ObjectNode objectNode = fastDFSHelper.upload(file.getInputStream(), fileType, "");
                vo = iotDeviceService.importDevice(objectNode.get("fid").toString().replaceAll("\"", ""),fileName,purcharseId,wb);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
            }
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_create,vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
        }
    }

    @GetMapping(value = IotRequestMapping.Device.queryImportRecordPage)
    @ApiOperation(value = "分页查找导入记录", notes = "分页查找导入记录")
    public Envelop<IotDeviceImportRecordVO> queryImportRecordPage(@ApiParam(name = "purcharseId", value = "采购id", defaultValue = "")
                                                           @RequestParam(value = "purcharseId", required = true) String purcharseId,
                                                                       @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                           @RequestParam(value = "page", required = false) Integer page,
                                                                       @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                           @RequestParam(value = "size", required = false) Integer size){
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return iotDeviceService.queryImportRecordPage(page,size,purcharseId);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }
}
