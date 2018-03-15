package com.yihu.jw.fegin.iot.product;

import com.yihu.jw.fegin.fallbackfactory.iot.product.IotProductFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
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
    public Envelop<IotProductBaseInfoVO> findCompanyPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "classify", required = false) String classify,
            @RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @GetMapping(value = IotRequestMapping.Product.findProductPageByCompanyId)
    public Envelop<IotProductBaseInfoVO> findProductPageByCompanyId(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "companyId", required = true) String companyId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size);

    @PostMapping(value = IotRequestMapping.Product.addProduct)
    public Envelop<IotProductVO> addProduct(@RequestParam(value = "jsonData", required = false)String jsonData);


    @GetMapping(value = IotRequestMapping.Product.findProductById)
    public Envelop<IotProductVO> findByCode(@RequestParam(value = "id", required = true) String id);

    @GetMapping(value = IotRequestMapping.Product.maintenanceUnitById)
    public Envelop<IotMaintenanceUnitVO> getList(@RequestParam(value = "productId", required = true) String productId);

    @PostMapping(value = IotRequestMapping.Product.delProduct)
    public Envelop<IotProductVO> delCompany(@RequestParam(value = "id", required = true) String id);

    @PostMapping(value = IotRequestMapping.Product.updProduct)
    public Envelop<IotProductVO> updCompany(@RequestParam(value = "jsonData", required = false)String jsonData);

}
