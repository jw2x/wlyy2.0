package com.yihu.jw.feign.iot.device;

import com.yihu.jw.feign.fallbackfactory.iot.device.IotDeviceFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.common.ExistVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yeshijie on 2017/12/8.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotDeviceFallbackFactory.class
)
@RequestMapping(IotRequestMapping.Common.device)
public interface IotDeviceFeign{

    @PostMapping(value = IotRequestMapping.Device.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Envelop<IotDeviceVO> create(@RequestBody String jsonData);

    @GetMapping(value = IotRequestMapping.Device.api_getById)
    public Envelop<IotDeviceVO> findByCode(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.Device.isSnExist)
    public Envelop<ExistVO> isSnExist(@RequestParam(value = "sn", required = true) String sn);

    @GetMapping(value = IotRequestMapping.Device.isSimExist)
    public Envelop<ExistVO> isSimExist(@RequestParam(value = "sim", required = true) String sim);

    @GetMapping(value = IotRequestMapping.Device.api_queryPage)
    public Envelop<IotDeviceVO> findProductPageByCompanyId(
            @RequestParam(value = "sn", required = false) String sn,
            @RequestParam(value = "hospital", required = false) String hospital,
            @RequestParam(value = "orderId", required = false) String orderId,
            @RequestParam(value = "purcharseId", required = true) String purcharseId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @PostMapping(value = IotRequestMapping.Device.importDevice)
    public Envelop<IotDeviceImportRecordVO> uploadStream(
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "purcharseId", required = true) String purcharseId);


    @GetMapping(value = IotRequestMapping.Device.queryImportRecordPage)
    public Envelop<IotDeviceImportRecordVO> queryImportRecordPage(
           @RequestParam(value = "purcharseId", required = true) String purcharseId,
           @RequestParam(value = "page", required = false) Integer page,
           @RequestParam(value = "size", required = false) Integer size);
}
