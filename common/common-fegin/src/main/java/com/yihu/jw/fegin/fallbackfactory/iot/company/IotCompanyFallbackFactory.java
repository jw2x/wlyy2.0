package com.yihu.jw.fegin.fallbackfactory.iot.company;

import com.yihu.jw.fegin.iot.company.IotCompanyFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyCertificateVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2018/1/20.
 */
@Component
public class IotCompanyFallbackFactory implements FallbackFactory<IotCompanyFeign> {

    @Autowired
    private Tracer tracer;

    @Override
    public IotCompanyFeign create(Throwable e) {
        return new IotCompanyFeign() {

            @Override
            public MixEnvelop<IotCompanyVO, IotCompanyVO> findCompanyPage(@RequestParam(value = "name", required = false) String name,
                                                            @RequestParam(value = "status", required = false) String status,
                                                            @RequestParam(value = "type", required = false) String type,
                                                            @RequestParam(value = "page", required = false) Integer page,
                                                            @RequestParam(value = "size", required = false) Integer size){
                tracer.getCurrentSpan().logEvent("分页查找企业失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("name:" + name);
                tracer.getCurrentSpan().logEvent("status:" + status);
                tracer.getCurrentSpan().logEvent("type:" + type);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyVO, IotCompanyVO> addCompany(@RequestParam(value = "jsonData", required = true)String jsonData) {
                tracer.getCurrentSpan().logEvent("创建企业失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyVO, IotCompanyVO> findByCode(@RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("根据id查找企业失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyVO, IotCompanyVO> findByBusinessLicense(@RequestParam(value = "businessLicense", required = true) String businessLicense) {
                tracer.getCurrentSpan().logEvent("根据营业执照号查找企业失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("businessLicense:" + businessLicense);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyVO, IotCompanyVO> delCompany(@RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("删除企业失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyVO, IotCompanyVO> updCompany(@RequestParam(value = "jsonData", required = true)String jsonData) {
                tracer.getCurrentSpan().logEvent("修改企业信息失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> findCompanyCertPage
                    (@RequestParam(value = "name", required = false) String name,
                     @RequestParam(value = "companyId", required = false) String companyId,
                     @RequestParam(value = "page", required = false) Integer page,
                     @RequestParam(value = "size", required = false) Integer size){
                tracer.getCurrentSpan().logEvent("分页获取企业证书失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("name:" + name);
                tracer.getCurrentSpan().logEvent("companyId:" + companyId);
                tracer.getCurrentSpan().logEvent("page:" + page);
                tracer.getCurrentSpan().logEvent("size:" + size);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> findCompanyCertById(@RequestParam(value = "id", required = true) String id) {
                tracer.getCurrentSpan().logEvent("根据id查找企业证书失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> findCompanyCertByCompanyId(@RequestParam(value = "companyId", required = true) String companyId) {
                tracer.getCurrentSpan().logEvent("根据企业id查找企业证书失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("companyId:" + companyId);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> addCompanyCert(@RequestParam(value = "jsonData", required = true)String jsonData) {
                tracer.getCurrentSpan().logEvent("创建企业证书失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public MixEnvelop<IotCompanyCertificateVO, IotCompanyCertificateVO> delCompanyCert(@RequestParam(value = "id", required = true)String id) {
                tracer.getCurrentSpan().logEvent("删除企业证书失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("id:" + id);
                return null;
            }

        };
    }



}
