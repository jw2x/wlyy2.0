package com.yihu.jw.feign.iot.device;

import com.yihu.jw.feign.fallbackfactory.iot.device.IotDeviceFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
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
    public Envelop<IotDeviceVO> create(@RequestParam(value = "jsonData", required = true) String jsonData);

    @GetMapping(value = IotRequestMapping.Device.api_getById)
    public Envelop<IotDeviceVO> findByCode(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.Device.isSnExist)
    public Envelop<ExistVO> isSnExist(@RequestParam(value = "sn", required = true) String sn);

    @GetMapping(value = IotRequestMapping.Device.isSimExist)
    public Envelop<ExistVO> isSimExist(@RequestParam(value = "sim", required = true) String sim);

    @PostMapping(value = IotRequestMapping.Device.updSim)
    public BaseEnvelop updSim(@RequestParam(value = "sim", required = true) String sim,
                             @RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.Device.api_queryPage)
    public Envelop<IotDeviceVO> findProductPageByCompanyId(
            @RequestParam(value = "sn", required = false) String sn,
            @RequestParam(value = "hospital", required = false) String hospital,
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "purcharseId", required = true) String purcharseId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @GetMapping(value = IotRequestMapping.Device.isImportDevice)
    public Envelop<ExistVO> isImportDevice(@RequestParam(value = "purcharseId", required = true) String purcharseId);

    @PostMapping(value = IotRequestMapping.Device.importDevice)
    public Envelop<IotDeviceImportRecordVO> uploadStream(@RequestBody String jsonData);

    @GetMapping(value = IotRequestMapping.Device.queryImportRecordPage)
    public Envelop<IotDeviceImportRecordVO> queryImportRecordPage(
           @RequestParam(value = "purcharseId", required = true) String purcharseId,
           @RequestParam(value = "page", required = false) Integer page,
           @RequestParam(value = "size", required = false) Integer size);
}
