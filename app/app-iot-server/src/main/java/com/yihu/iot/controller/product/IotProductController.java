package com.yihu.iot.controller.product;

import com.yihu.iot.controller.common.BaseController;
import com.yihu.iot.model.ehr.MStdDataSet;
import com.yihu.iot.service.product.ProductService;
import com.yihu.jw.restmodel.web.MixEnvelop;
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
@RequestMapping(IotRequestMapping.Common.product)
@Api(tags = "产品管理相关操作", description = "产品管理相关操作")
public class IotProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = IotRequestMapping.Product.data_sets)
    @ApiOperation(value = "测量数据", notes = "测量数据")
    public MixEnvelop<MStdDataSet, MStdDataSet> data_sets(@ApiParam(name = "name", value = "名称", defaultValue = "")
                                         @RequestParam(value = "name", required = false) String name,
                                             @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                         @RequestParam(value = "page", required = false) Integer page,
                                             @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                         @RequestParam(value = "size", required = false) Integer size){
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return productService.data_sets(page, size, name);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }


    @GetMapping(value = IotRequestMapping.Product.findProductPage)
    @ApiOperation(value = "分页查找产品", notes = "分页查找产品")
    public MixEnvelop<IotProductBaseInfoVO, IotProductBaseInfoVO> findCompanyPage(
            @ApiParam(name = "name", value = "注册证号或产品名称", defaultValue = "")
            @RequestParam(value = "name", required = false) String name,
            @ApiParam(name = "classify", value = "产品分类", defaultValue = "")
            @RequestParam(value = "classify", required = false) String classify,
            @ApiParam(name = "companyId", value = "企业id", defaultValue = "")
            @RequestParam(value = "companyId", required = false) String companyId,
            @ApiParam(name = "page", value = "第几页", defaultValue = "")
            @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
            @RequestParam(value = "size", required = false) Integer size){
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return productService.findCompanyPage(name,classify,companyId,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Product.findProductPageByCompanyId)
    @ApiOperation(value = "分页查找产品(按企业id)", notes = "分页查找产品")
    public MixEnvelop<IotProductBaseInfoVO, IotProductBaseInfoVO> findProductPageByCompanyId(@ApiParam(name = "name", value = "产品名称", defaultValue = "")
                                                         @RequestParam(value = "name", required = false) String name,
                                                                       @ApiParam(name = "companyId", value = "企业id", defaultValue = "")
                                                         @RequestParam(value = "companyId", required = true) String companyId,
                                                                       @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                         @RequestParam(value = "page", required = false) Integer page,
                                                                       @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                         @RequestParam(value = "size", required = false) Integer size){
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return productService.findProductPageByCompanyId(name,companyId,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Product.addProduct)
    @ApiOperation(value = "创建产品", notes = "创建产品")
    public MixEnvelop<IotProductVO, IotProductVO> addProduct(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
        try {
            return productService.addProduct(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }


    @GetMapping(value = IotRequestMapping.Product.findProductById)
    @ApiOperation(value = "根据id查找产品", notes = "根据id查找产品")
    public MixEnvelop<IotProductVO, IotProductVO> findByCode(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            return productService.findByCode(id);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }


    @GetMapping(value = IotRequestMapping.Product.maintenanceUnitById)
    @ApiOperation(value = "根据id查找产品的维护单位", notes = "根据id查找产品的维护单位")
    public MixEnvelop<IotMaintenanceUnitVO, IotMaintenanceUnitVO> maintenanceUnitById(@ApiParam(name = "productId", value = "productId")
                                            @RequestParam(value = "productId", required = true) String productId) {
        try {
            return productService.maintenanceUnitById(productId);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Product.delProduct)
    @ApiOperation(value = "删除产品", notes = "删除产品")
    public MixEnvelop<IotProductVO, IotProductVO> delCompany(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            return productService.delCompany(id);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Product.updProduct)
    @ApiOperation(value = "修改产品信息", notes = "修改产品信息")
    public MixEnvelop<IotProductVO, IotProductVO> updCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
        try {
            return productService.updCompany(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

}
