package com.yihu.jw.controller.iot.product;

import com.yihu.jw.commnon.iot.IotCommonContants;
import com.yihu.jw.feign.iot.product.IotProductFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.product.IotMaintenanceUnitVO;
import com.yihu.jw.restmodel.iot.product.IotProductBaseInfoVO;
import com.yihu.jw.restmodel.iot.product.IotProductVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yeshijie on 2018/1/17.
 */
@RestController
@RequestMapping(IotCommonContants.Common.product)
@Api(tags = "产品管理相关操作", description = "产品管理相关操作")
public class IotProductController extends EnvelopRestController {

    @Autowired
    private IotProductFeign iotProductFeign;


    @GetMapping(value = IotRequestMapping.Product.findProductPage)
    @ApiOperation(value = "分页查找产品", notes = "分页查找产品")
    public Envelop<IotProductBaseInfoVO> findCompanyPage(@ApiParam(name = "name", value = "注册证号或产品名称", defaultValue = "")
                                                 @RequestParam(value = "name", required = false) String name,
                                                         @ApiParam(name = "classify", value = "产品分类", defaultValue = "")
                                                 @RequestParam(value = "classify", required = false) String classify,
                                                         @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                 @RequestParam(value = "page", required = false) Integer page,
                                                         @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                 @RequestParam(value = "size", required = false) Integer size){
        return iotProductFeign.findCompanyPage(name,classify,page,size);
    }

    @GetMapping(value = IotRequestMapping.Product.findProductPageByCompanyId)
    @ApiOperation(value = "分页查找产品(按企业id)", notes = "分页查找产品")
    public Envelop<IotProductBaseInfoVO> findProductPageByCompanyId(@ApiParam(name = "name", value = "产品名称", defaultValue = "")
                                                         @RequestParam(value = "name", required = false) String name,
                                                         @ApiParam(name = "companyId", value = "企业id", defaultValue = "")
                                                         @RequestParam(value = "companyId", required = true) String companyId,
                                                         @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                         @RequestParam(value = "page", required = false) Integer page,
                                                         @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                         @RequestParam(value = "size", required = false) Integer size){
        return iotProductFeign.findProductPageByCompanyId(name,companyId,page,size);
    }

    @PostMapping(value = IotRequestMapping.Product.addProduct)
    @ApiOperation(value = "创建产品", notes = "创建产品")
    public Envelop<IotProductVO> addProduct(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
        return iotProductFeign.addProduct(jsonData);
    }


    @GetMapping(value = IotRequestMapping.Product.findProductById)
    @ApiOperation(value = "根据id查找产品", notes = "根据id查找产品")
    public Envelop<IotProductVO> findByCode(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        return iotProductFeign.findByCode(id);
    }

    @GetMapping(value = IotRequestMapping.Product.maintenanceUnitById)
    @ApiOperation(value = "获取维护单位")
    public Envelop<IotMaintenanceUnitVO> getList(
            @ApiParam(name = "productId", value = "产品", defaultValue = "1")
            @RequestParam(value = "productId", required = true) String productId) throws Exception {
        return iotProductFeign.getList(productId);
    }

    @PostMapping(value = IotRequestMapping.Product.delProduct)
    @ApiOperation(value = "删除产品", notes = "删除产品")
    public Envelop<IotProductVO> delCompany(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        return iotProductFeign.delCompany(id);
    }

    @PostMapping(value = IotRequestMapping.Product.updProduct)
    @ApiOperation(value = "修改产品信息", notes = "修改产品信息")
    public Envelop<IotProductVO> updCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
        return iotProductFeign.updCompany(jsonData);
    }

}
