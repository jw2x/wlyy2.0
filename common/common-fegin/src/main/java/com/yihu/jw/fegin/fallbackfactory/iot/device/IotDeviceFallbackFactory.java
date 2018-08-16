package com.yihu.jw.fegin.fallbackfactory.iot.device;

import com.yihu.jw.fegin.iot.device.IotDeviceFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.iot.common.ExistVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceImportRecordVO;
import com.yihu.jw.restmodel.iot.device.IotDeviceVO;
import feign.hystrix.FallbackFactory;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2017/12/8.
 */
@Component
public class IotDeviceFallbackFactory implements FallbackFactory<IotDeviceFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public IotDeviceFeign create(Throwable e) {
        return new IotDeviceFeign() {

            @Override
            public MixEnvelop<IotDeviceVO, IotDeviceVO> create(@RequestParam(value = "jsonData", required = true) String jsonData) {
                tracer.getCurrentSpan().logEvent("创建设备失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop<IotDeviceVO, IotDeviceVO> findByCode(@RequestParam(value = "id", required = true) String id
            ) {
                tracer.getCurrentSpan().logEvent("根据id查找设备失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public MixEnvelop<IotDeviceVO, IotDeviceVO> delDevice(@RequestParam(value = "id", required = true) String id
            ) {
                tracer.getCurrentSpan().logEvent("删除设备失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public Envelop updDevice(@RequestParam String jsonData) {
                tracer.getCurrentSpan().logEvent("修改设备失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop<ExistVO, ExistVO> isSnExist(@RequestParam(value = "sn", required = true) String sn
            ) {
                tracer.getCurrentSpan().logEvent("sn码是否存在失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("sn:" + sn);
                return null;
            }

            @Override
            public MixEnvelop<ExistVO, ExistVO> isSimExist(@RequestParam(value = "sim", required = true) String sim
            ) {
                tracer.getCurrentSpan().logEvent("sim卡号是否存在失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("sim:" + sim);
                return null;
            }

            @Override
            public Envelop updSim(@RequestParam(value = "sim", required = true) String sim,
                                  @RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("修改sim卡号失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("sim:" + sim);
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public MixEnvelop<IotDeviceVO, IotDeviceVO> findProductPageByCompanyId(@RequestParam(value = "sn", required = false) String sn,
                                                                      @RequestParam(value = "hospital", required = false) String hospital,
                                                                      @RequestParam(value = "orderId", required = false) String orderId,
                                                                      @RequestParam(value = "purcharseId", required = false) String purcharseId,
                                                                      @RequestParam(value = "isBinding", required = false) Integer isBinding,
                                                                      @RequestParam(value = "page", required = false) Integer page,
                                                                      @RequestParam(value = "size", required = false) Integer size){
                tracer.getCurrentSpan().logEvent("分页查找设备失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("sn:" + sn);
                tracer.getCurrentSpan().logEvent("hospital:" + hospital);
                tracer.getCurrentSpan().logEvent("orderId:" + orderId);
                tracer.getCurrentSpan().logEvent("purcharseId:" + purcharseId);
                tracer.getCurrentSpan().logEvent("isBinding:" + isBinding);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }

            @Override
            public MixEnvelop<ExistVO, ExistVO> isImportDevice(@ApiParam(name = "purcharseId", value = "purcharseId")
                                                   @RequestParam(value = "purcharseId", required = true) String purcharseId) {
                tracer.getCurrentSpan().logEvent("是否正在导入设备数据失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("purcharseId:" + purcharseId);
                return null;
            }

            @Override
            public MixEnvelop<IotDeviceImportRecordVO, IotDeviceImportRecordVO> uploadStream(@RequestBody String jsonData) {
                tracer.getCurrentSpan().logEvent("设备导入失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop<IotDeviceImportRecordVO, IotDeviceImportRecordVO> queryImportRecordPage(@RequestParam(value = "purcharseId", required = true) String purcharseId,
                                                                             @RequestParam(value = "page", required = false) Integer page,
                                                                             @RequestParam(value = "size", required = false) Integer size){
                tracer.getCurrentSpan().logEvent("分页查找导入记录失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("purcharseId:" + purcharseId);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }

        };
    }


}
