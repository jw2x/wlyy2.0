package com.yihu.ehr.iot.controller.product;

import com.yihu.ehr.iot.controller.common.BaseController;
import com.yihu.ehr.iot.service.product.ProductService;
import com.yihu.jw.restmodel.common.Envelop;
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
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return productService.findCompanyPage(name,classify,page,size);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
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
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Product.addProduct)
    @ApiOperation(value = "创建产品", notes = "创建产品")
    public Envelop<IotProductVO> addProduct(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
        try {
            return productService.addProduct(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }


    @GetMapping(value = IotRequestMapping.Product.findProductById)
    @ApiOperation(value = "根据id查找产品", notes = "根据id查找产品")
    public Envelop<IotProductVO> findByCode(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            return productService.findByCode(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Product.delProduct)
    @ApiOperation(value = "删除产品", notes = "删除产品")
    public Envelop<IotProductVO> delCompany(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            return productService.delCompany(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = IotRequestMapping.Product.updProduct)
    @ApiOperation(value = "修改产品信息", notes = "修改产品信息")
    public Envelop<IotProductVO> updCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = false)String jsonData) {
        try {
            return productService.updCompany(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

}
