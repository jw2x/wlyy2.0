package com.yihu.jw.fegin.iot.product;

import com.yihu.jw.fegin.fallbackfactory.iot.product.IotProductFallbackFactory;
import com.yihu.jw.common.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.iot.product.IotMaintenanceUnitVO;
import com.yihu.jw.restmodel.iot.product.IotProductBaseInfoVO;
import com.yihu.jw.restmodel.iot.product.IotProductVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2018/1/17.
 */
@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = IotProductFallbackFactory.class
)
@RequestMapping(IotRequestMapping.Common.product)
public interface IotProductFeign{

    @GetMapping(value = IotRequestMapping.Product.findProductPage)
    public MixEnvelop<IotProductBaseInfoVO, IotProductBaseInfoVO> findCompanyPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "classify", required = false) String classify,
            @RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @GetMapping(value = IotRequestMapping.Product.findProductPageByCompanyId)
    public MixEnvelop<IotProductBaseInfoVO, IotProductBaseInfoVO> findProductPageByCompanyId(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "companyId", required = true) String companyId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @PostMapping(value = IotRequestMapping.Product.addProduct)
    public MixEnvelop<IotProductVO, IotProductVO> addProduct(@RequestParam(value = "jsonData", required = false)String jsonData);


    @GetMapping(value = IotRequestMapping.Product.findProductById)
    public MixEnvelop<IotProductVO, IotProductVO> findByCode(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.Product.maintenanceUnitById)
    public MixEnvelop<IotMaintenanceUnitVO, IotMaintenanceUnitVO> getList(@RequestParam(value = "productId", required = true) String productId);

    @PostMapping(value = IotRequestMapping.Product.delProduct)
    public MixEnvelop<IotProductVO, IotProductVO> delCompany(@RequestParam(value = "id", required = true) String id);

    @PostMapping(value = IotRequestMapping.Product.updProduct)
    public MixEnvelop<IotProductVO, IotProductVO> updCompany(@RequestParam(value = "jsonData", required = false)String jsonData);

}
