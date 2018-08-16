package com.yihu.jw.fegin.iot.company;

import com.yihu.jw.fegin.fallbackfactory.iot.company.IotCompanyFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.company.IotCompanyCertificateVO;
import com.yihu.jw.restmodel.iot.company.IotCompanyVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2018/1/16.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotCompanyFallbackFactory.class
)
@RequestMapping(IotRequestMapping.Common.company)
public interface IotCompanyFeign {


    @GetMapping(value = IotRequestMapping.Company.findCompanyPage)
    public MixEnvelop<IotCompanyVO> findCompanyPage(
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "status", required = false) String status,
                                       @RequestParam(value = "type", required = false) String type,
                                       @RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "size", required = false) Integer size);

    @PostMapping(value = IotRequestMapping.Company.addCompany)
    public MixEnvelop<IotCompanyVO> addCompany(@RequestParam(value = "jsonData", required = true)String jsonData);

    @GetMapping(value = IotRequestMapping.Company.findCompanyById)
    public MixEnvelop<IotCompanyVO> findByCode(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.Company.findByBusinessLicense)
    public MixEnvelop<IotCompanyVO> findByBusinessLicense(@RequestParam(value = "businessLicense", required = true) String businessLicense);

    @PostMapping(value = IotRequestMapping.Company.delCompany)
    public MixEnvelop<IotCompanyVO> delCompany(@RequestParam(value = "id", required = true) String id);

    @PostMapping(value = IotRequestMapping.Company.updCompany)
    public MixEnvelop<IotCompanyVO> updCompany(@RequestParam(value = "jsonData", required = true)String jsonData);

    @GetMapping(value = IotRequestMapping.Company.findCompanyCertPage)
    public MixEnvelop<IotCompanyCertificateVO> findCompanyCertPage
            (@RequestParam(value = "name", required = false) String name,
             @RequestParam(value = "companyId", required = false) String companyId,
             @RequestParam(value = "page", required = false) Integer page,
             @RequestParam(value = "size", required = false) Integer size);

    @GetMapping(value = IotRequestMapping.Company.findCompanyCertById)
    public MixEnvelop<IotCompanyCertificateVO> findCompanyCertById(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.Company.findCompanyCertByCompanyId)
    public MixEnvelop<IotCompanyCertificateVO> findCompanyCertByCompanyId(@RequestParam(value = "companyId", required = true) String companyId);

    @PostMapping(value = IotRequestMapping.Company.addCompanyCert)
    public MixEnvelop<IotCompanyCertificateVO> addCompanyCert(@RequestParam(value = "jsonData", required = true)String jsonData);

    @PostMapping(value = IotRequestMapping.Company.delCompanyCert)
    public MixEnvelop<IotCompanyCertificateVO> delCompanyCert(@RequestParam(value = "id", required = true)String id);
}
