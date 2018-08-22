package com.yihu.iot.controller.device;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.fastdfs.FastDFSUtil;
import com.yihu.iot.dao.device.IotDeviceImportRecordDao;
import com.yihu.iot.service.device.IotDeviceService;
import com.yihu.jw.entity.iot.device.IotDeviceDO;
import com.yihu.jw.entity.iot.device.IotDeviceImportRecordDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.iot.common.ExistVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.device)
@Api(tags = "设备管理相关操作", description = "设备管理相关操作")
public class IotDeviceController extends EnvelopRestEndpoint {

    private Logger logger = LoggerFactory.getLogger(IotDeviceController.class);
    @Autowired
    private IotDeviceService iotDeviceService;
    @Autowired
    private FastDFSUtil fastDFSHelper;
    @Value("${fastDFS.fastdfs_file_url}")
    private String fastdfs_file_url;
    @Autowired
    private IotDeviceImportRecordDao iotDeviceImportRecordDao;

    @PostMapping(value = IotRequestMapping.Device.api_create)
    @ApiOperation(value = "创建设备", notes = "创建设备")
    public MixEnvelop<IotDeviceVO, IotDeviceVO> create(@ApiParam(name = "jsonData", value = "", defaultValue = "")
                          @RequestParam String jsonData) {
        try {
            IotDeviceDO iotDevice = toEntity(jsonData, IotDeviceDO.class);
            if(StringUtils.isBlank(iotDevice.getDeviceSn())){
                return MixEnvelop.getError("sn码不能为空");
            }
            if(iotDeviceService.findByDeviceSn(iotDevice.getDeviceSn())!=null){
                return MixEnvelop.getError("SN码重复，并不允许新增");
            }
            if(StringUtils.isNotBlank(iotDevice.getSimNo())&&iotDeviceService.findByDeviceSn(iotDevice.getSimNo())!=null){
                return MixEnvelop.getError("SIM卡号重复，并不允许新增");
            }
            iotDeviceService.create(iotDevice);
            return MixEnvelop.getSuccess(IotRequestMapping.Device.message_success_create);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Device.api_delete)
    @ApiOperation(value = "删除设备", notes = "删除设备")
    public MixEnvelop<IotDeviceVO, IotDeviceVO> delDevice(@ApiParam(name = "id", value = "id")
                                           @RequestParam(value = "id", required = true) String id
    ) {
        try {
            Integer re = iotDeviceService.delDevice(id);
            if(re==1){
                return MixEnvelop.getSuccess(IotRequestMapping.Device.message_success_find);
            }else {
                return MixEnvelop.getError(IotRequestMapping.Device.del_message_fail);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Device.api_update)
    @ApiOperation(value = "修改设备", notes = "修改设备")
    public Envelop updDevice(@ApiParam(name = "jsonData", value = "", defaultValue = "")
                                     @RequestParam String jsonData) {
        try {
            IotDeviceVO iotDevice = toEntity(jsonData, IotDeviceVO.class);
            return iotDeviceService.updDevice(iotDevice);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Device.api_getById)
    @ApiOperation(value = "根据code查找设备", notes = "根据code查找设备")
    public MixEnvelop<IotDeviceVO, IotDeviceVO> findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        try {
            IotDeviceDO iotDeviceDO = iotDeviceService.findById(id);
            IotDeviceVO iotDeviceVO = convertToModel(iotDeviceDO,IotDeviceVO.class);
            iotDeviceService.translateDictForOne(iotDeviceVO);
            return MixEnvelop.getSuccess(IotRequestMapping.Device.message_success_find, iotDeviceVO);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Device.isSnExist)
    @ApiOperation(value = "sn码是否存在", notes = "sn码是否存在")
    public MixEnvelop<ExistVO, ExistVO> isSnExist(@ApiParam(name = "sn", value = "sn")
                                           @RequestParam(value = "sn", required = true) String sn
    ) {
        try {
            IotDeviceDO iotDeviceDO = iotDeviceService.findByDeviceSn(sn);
            ExistVO existVO = new ExistVO(iotDeviceDO==null?0:1);
            return MixEnvelop.getSuccess(IotRequestMapping.Device.message_success_find, existVO);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Device.isSimExist)
    @ApiOperation(value = "sim卡号是否存在", notes = "sim卡号是否存在")
    public MixEnvelop<ExistVO, ExistVO> isSimExist(@ApiParam(name = "sim", value = "sim")
                                           @RequestParam(value = "sim", required = true) String sim
    ) {
        try {
            IotDeviceDO iotDeviceDO = iotDeviceService.findBySimNo(sim);
            ExistVO existVO = new ExistVO(iotDeviceDO==null?0:1);
            return MixEnvelop.getSuccess(IotRequestMapping.Device.message_success_find, existVO);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Device.updSim)
    @ApiOperation(value = "修改sim卡号", notes = "修改sim卡号")
    public Envelop updSim(@ApiParam(name = "sim", value = "sim")
                                   @RequestParam(value = "sim", required = true) String sim,
                          @ApiParam(name = "id", value = "设备id")
                                   @RequestParam(value = "id", required = true) String id) {
        try {
            return iotDeviceService.updSim(sim,id);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Device.api_queryPage)
    @ApiOperation(value = "分页查找设备", notes = "分页查找设备")
    public MixEnvelop<IotDeviceVO, IotDeviceVO> findProductPageByCompanyId(@ApiParam(name = "sn", value = "SN码或SIM卡号", defaultValue = "")
                                                           @RequestParam(value = "sn", required = false) String sn,
                                                              @ApiParam(name = "hospital", value = "社区医院", defaultValue = "")
                                                           @RequestParam(value = "hospital", required = false) String hospital,
                                                              @ApiParam(name = "orderId", value = "订单id", defaultValue = "")
                                                           @RequestParam(value = "orderId", required = false) String orderId,
                                                              @ApiParam(name = "purcharseId", value = "采购id", defaultValue = "")
                                                           @RequestParam(value = "purcharseId", required = false) String purcharseId,
                                                              @ApiParam(name = "isBinding", value = "是否绑定（1已绑定，2未绑定）", defaultValue = "")
                                                           @RequestParam(value = "isBinding", required = false) Integer isBinding,
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
            if(isBinding==null){
                return iotDeviceService.queryPage(sn, hospital, orderId, purcharseId, page, size);
            }else {
                return iotDeviceService.queryPage(sn, hospital, orderId, purcharseId, isBinding, page, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Device.isImportDevice)
    @ApiOperation(value = "是否正在导入设备数据", notes = "是否正在导入设备数据")
    public MixEnvelop<ExistVO, ExistVO> isImportDevice(@ApiParam(name = "purcharseId", value = "purcharseId")
                                       @RequestParam(value = "purcharseId", required = true) String purcharseId
    ) {
        try {
            IotDeviceImportRecordDO recordDO = iotDeviceImportRecordDao.findByPurchaseId(purcharseId);
            ExistVO existVO = new ExistVO(recordDO==null?0:1);
            return MixEnvelop.getSuccess(IotRequestMapping.Device.message_success_find, existVO);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Device.importDevice)
    @ApiOperation(value = "设备导入", notes = "设备导入")
    public MixEnvelop<IotDeviceImportRecordVO, IotDeviceImportRecordVO> uploadStream(@ApiParam(value = "jsonData", required = true)
                                          @RequestBody String jsonData) {
        try {
            JSONObject json = JSONObject.parseObject(jsonData);
            String data = json.getString("jsonData");
            String fileName = json.getString("fileName");
            String purcharseId = json.getString("purcharseId");
            String url = json.getString("url");
            IotDeviceImportRecordDO recordDO = iotDeviceImportRecordDao.findByPurchaseId(purcharseId);
            if(recordDO!=null){
                return MixEnvelop.getError("正在导入中，请耐心等待");
            }
            ObjectMapper objectMapper = new ObjectMapper();

            List<IotDeviceImportVO> importVOList = objectMapper.readValue(data, new TypeReference<List<IotDeviceImportVO>>() {});
            IotDeviceImportRecordVO vo = iotDeviceService.importDevice(url,fileName,purcharseId,importVOList);
            return MixEnvelop.getSuccess(IotRequestMapping.Common.message_success_create,vo);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(IotRequestMapping.FileUpload.message_fail_upload, IotRequestMapping.api_iot_fail);
        }
    }

    @GetMapping(value = IotRequestMapping.Device.queryImportRecordPage)
    @ApiOperation(value = "分页查找导入记录", notes = "分页查找导入记录")
    public MixEnvelop<IotDeviceImportRecordVO, IotDeviceImportRecordVO> queryImportRecordPage(@ApiParam(name = "purcharseId", value = "采购id", defaultValue = "")
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
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
