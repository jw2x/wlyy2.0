package com.yihu.jw.feign.fallbackfactory.iot.product;

import com.yihu.jw.feign.iot.product.IotProductFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.iot.product.IotProductBaseInfoVO;
import com.yihu.jw.restmodel.iot.product.IotProductVO;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2018/1/20.
 */
@Component
public class IotProductFallbackFactory implements FallbackFactory<IotProductFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public IotProductFeign create(Throwable e) {
        return new IotProductFeign() {

            @Override
            public Envelop<IotProductBaseInfoVO> findCompanyPage(
                    @RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "classify", required = false) String classify,
                    @RequestParam(value = "page", required = false) Integer page,
                    @RequestParam(value = "size", required = false) Integer size){
                tracer.getCurrentSpan().logEvent("分页查找产品失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("name:" + name);
                tracer.getCurrentSpan().logEvent("classify:" + classify);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }

            @Override
            public Envelop<IotProductBaseInfoVO> findProductPageByCompanyId(
                    @RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "companyId", required = true) String companyId,
                    @RequestParam(value = "page", required = false) Integer page,
                    @RequestParam(value = "size", required = false) Integer size){
                tracer.getCurrentSpan().logEvent("分页查找产品(按企业id)失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("name:" + name);
                tracer.getCurrentSpan().logEvent("companyId:" + companyId);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }

            @Override
            public Envelop<IotProductVO> addProduct(@RequestParam(value = "jsonData", required = false)String jsonData) {
                tracer.getCurrentSpan().logEvent("创建产品失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }


            @Override
            public Envelop<IotProductVO> findByCode(@RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("根据id查找产品失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public Envelop<IotProductVO> delCompany(@RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("删除产品失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public Envelop<IotProductVO> updCompany(@RequestParam(value = "jsonData", required = false)String jsonData) {
                tracer.getCurrentSpan().logEvent("修改产品信息失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

        };
    }

}
