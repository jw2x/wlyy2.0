package com.yihu.jw.controller.iot.device;

import com.yihu.jw.commnon.iot.IotCommonContants;
import com.yihu.jw.feign.iot.device.IotDeviceFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.restmodel.iot.common.ExistVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2017/12/8.
 */
@RestController
@RequestMapping(IotCommonContants.Common.device)
@Api(tags = "设备管理相关操作", description = "设备管理相关操作")
public class IotDeviceController extends EnvelopRestController{

    @Autowired
    private IotDeviceFeign iotDeviceFeign;

    @PostMapping(value = IotRequestMapping.Device.api_create)
    @ApiOperation(value = "创建设备", notes = "创建设备")
    public Envelop<IotDeviceVO> create(@ApiParam(name = "jsonData", value = "", defaultValue = "")
                          @RequestParam(value = "jsonData", required = true) String jsonData) {
        return iotDeviceFeign.create(jsonData);
    }


    @GetMapping(value = IotRequestMapping.Device.api_getById)
    @ApiOperation(value = "根据code查找设备", notes = "根据code查找设备")
    public Envelop<IotDeviceVO> findByCode(@ApiParam(name = "id", value = "id")
                              @RequestParam(value = "id", required = true) String id
    ) {
        return iotDeviceFeign.findByCode(id);
    }

    @GetMapping(value = IotRequestMapping.Device.isSnExist)
    @ApiOperation(value = "sn码是否存在", notes = "sn码是否存在")
    public Envelop<ExistVO> isSnExist(@ApiParam(name = "sn", value = "sn")
                                           @RequestParam(value = "sn", required = true) String sn
    ) {
        return iotDeviceFeign.isSnExist(sn);
    }

    @GetMapping(value = IotRequestMapping.Device.isSimExist)
    @ApiOperation(value = "sim卡号是否存在", notes = "sim卡号是否存在")
    public Envelop<ExistVO> isSimExist(@ApiParam(name = "sim", value = "sim")
                                           @RequestParam(value = "sim", required = true) String sim) {
        return iotDeviceFeign.isSimExist(sim);
    }

    @PostMapping(value = IotRequestMapping.Device.updSim)
    @ApiOperation(value = "修改sim卡号", notes = "修改sim卡号")
    public BaseEnvelop updSim(@ApiParam(name = "sim", value = "sim")
                              @RequestParam(value = "sim", required = true) String sim,
                              @ApiParam(name = "id", value = "设备id")
                              @RequestParam(value = "id", required = true) String id) {
        return iotDeviceFeign.updSim(sim,id);
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
                                                           @RequestParam(value = "purcharseId", required = false) String purcharseId,
                                                           @ApiParam(name = "isBinding", value = "是否绑定（1已绑定，2未绑定）", defaultValue = "")
                                                           @RequestParam(value = "isBinding", required = false) Integer isBinding,
                                                           @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                           @RequestParam(value = "page", required = false) Integer page,
                                                           @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                           @RequestParam(value = "size", required = false) Integer size){
        return iotDeviceFeign.findProductPageByCompanyId(sn,hospital,orderId,purcharseId,isBinding,page,size);
    }

    @GetMapping(value = IotRequestMapping.Device.isImportDevice)
    @ApiOperation(value = "是否正在导入设备数据", notes = "是否正在导入设备数据")
    public Envelop<ExistVO> isImportDevice(@ApiParam(name = "purcharseId", value = "purcharseId")
                                           @RequestParam(value = "purcharseId", required = true) String purcharseId
    ) {
        return iotDeviceFeign.isImportDevice(purcharseId);
    }

    @PostMapping(value = IotRequestMapping.Device.importDevice)
    @ApiOperation(value = "设备导入", notes = "设备导入")
    public Envelop<IotDeviceImportRecordVO> uploadStream(@ApiParam(value = "jsonData", required = true)
                                                         @RequestBody String jsonData) {
        return iotDeviceFeign.uploadStream(jsonData);
    }

    @GetMapping(value = IotRequestMapping.Device.queryImportRecordPage)
    @ApiOperation(value = "分页查找导入记录", notes = "分页查找导入记录")
    public Envelop<IotDeviceImportRecordVO> queryImportRecordPage(@ApiParam(name = "purcharseId", value = "采购id", defaultValue = "")
                                                           @RequestParam(value = "purcharseId", required = true) String purcharseId,
                                                                       @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                           @RequestParam(value = "page", required = false) Integer page,
                                                                       @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                           @RequestParam(value = "size", required = false) Integer size){
        return iotDeviceFeign.queryImportRecordPage(purcharseId,page,size);
    }
}
