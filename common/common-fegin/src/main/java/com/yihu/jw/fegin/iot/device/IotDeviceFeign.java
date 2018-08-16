package com.yihu.jw.fegin.iot.device;

import com.yihu.jw.fegin.fallbackfactory.iot.device.IotDeviceFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.iot.common.ExistVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2017/12/8.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotDeviceFallbackFactory.class
)
@RequestMapping(IotRequestMapping.Common.device)
public interface IotDeviceFeign{

    @PostMapping(value = IotRequestMapping.Device.api_create)
    MixEnvelop<IotDeviceVO, IotDeviceVO> create(@RequestParam(value = "jsonData", required = true) String jsonData);

    @GetMapping(value = IotRequestMapping.Device.api_getById)
    MixEnvelop<IotDeviceVO, IotDeviceVO> findByCode(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.Device.isSnExist)
    MixEnvelop<ExistVO, ExistVO> isSnExist(@RequestParam(value = "sn", required = true) String sn);

    @GetMapping(value = IotRequestMapping.Device.isSimExist)
    MixEnvelop<ExistVO, ExistVO> isSimExist(@RequestParam(value = "sim", required = true) String sim);

    @PostMapping(value = IotRequestMapping.Device.updSim)
    Envelop updSim(@RequestParam(value = "sim", required = true) String sim,
                   @RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.Device.api_queryPage)
    MixEnvelop<IotDeviceVO, IotDeviceVO> findProductPageByCompanyId(
            @RequestParam(value = "sn", required = false) String sn,
            @RequestParam(value = "hospital", required = false) String hospital,
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "purcharseId", required = false) String purcharseId,
            @RequestParam(value = "isBinding", required = false) Integer isBinding,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @GetMapping(value = IotRequestMapping.Device.isImportDevice)
    MixEnvelop<ExistVO, ExistVO> isImportDevice(@RequestParam(value = "purcharseId", required = true) String purcharseId);

    @PostMapping(value = IotRequestMapping.Device.importDevice)
    MixEnvelop<IotDeviceImportRecordVO, IotDeviceImportRecordVO> uploadStream(@RequestBody String jsonData);

    @GetMapping(value = IotRequestMapping.Device.queryImportRecordPage)
    MixEnvelop<IotDeviceImportRecordVO, IotDeviceImportRecordVO> queryImportRecordPage(
           @RequestParam(value = "purcharseId", required = true) String purcharseId,
           @RequestParam(value = "page", required = false) Integer page,
           @RequestParam(value = "size", required = false) Integer size);

    @PostMapping(value = IotRequestMapping.Device.api_delete)
    MixEnvelop<IotDeviceVO, IotDeviceVO> delDevice(@RequestParam(value = "id", required = true) String id);

    @PostMapping(value = IotRequestMapping.Device.api_update)
    Envelop updDevice(@RequestParam(value = "jsonData", required = true) String jsonData);
}
