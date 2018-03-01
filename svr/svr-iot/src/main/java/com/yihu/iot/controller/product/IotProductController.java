package com.yihu.iot.controller.product;

import com.yihu.iot.service.dict.IotSystemDictService;
import com.yihu.iot.service.product.IotProductBaseInfoService;
import com.yihu.jw.iot.product.IotProductBaseInfoDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.iot.product.IotMaintenanceUnitVO;
import com.yihu.jw.restmodel.iot.product.IotProductBaseInfoVO;
import com.yihu.jw.restmodel.iot.product.IotProductVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yeshijie on 2018/1/17.
 */
@RestController
@RequestMapping(IotRequestMapping.Common.product)
@Api(tags = "产品管理相关操作", description = "产品管理相关操作")
public class IotProductController extends EnvelopRestController {

    @Autowired
    private IotProductBaseInfoService iotProductBaseInfoService;
    @Autowired
    private IotSystemDictService iotSystemDictService;


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
            String filters = "";
            String semicolon = "";
            if(StringUtils.isNotBlank(name)){
                filters = "name?"+name+" g1;registerCertificate?"+name+" g1";
                semicolon = ";";
            }
            if(StringUtils.isNotBlank(classify)){
                filters += semicolon +"productClassify="+classify;
                semicolon = ";";
            }
            if(StringUtils.isBlank(filters)){
                filters+= semicolon + "del=1";
            }
            String sorts = "-updateTime";
            //得到list数据
            List<IotProductBaseInfoDO> list = iotProductBaseInfoService.search(null, filters, sorts, page, size);
            //获取总数
            long count = iotProductBaseInfoService.getCount(filters);

            //DO转VO
            List<IotProductBaseInfoVO> iotCompanyVOList = convertToModels(list,new ArrayList<>(list.size()),IotProductBaseInfoVO.class);

            return Envelop.getSuccessListWithPage(IotRequestMapping.Company.message_success_find_functions,iotCompanyVOList, page, size,count);
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
//            String filters = "supplierId="+companyId+";productClassify=1;del=1";
            String filters = "supplierId="+companyId+";del=1";
            String semicolon = ";";
            if(StringUtils.isNotBlank(name)){
                filters += semicolon + "name?"+name;
            }
            String sorts = "-updateTime";
            //得到list数据
            List<IotProductBaseInfoDO> list = iotProductBaseInfoService.search(null, filters, sorts, page, size);

            //获取总数
            long count = iotProductBaseInfoService.getCount(filters);

            //DO转VO
            List<IotProductBaseInfoVO> iotCompanyVOList = convertToModels(list,new ArrayList<>(list.size()),IotProductBaseInfoVO.class);
            if(iotCompanyVOList.size()>0){
                //字典翻译
                Map<String,String> product68Map = iotSystemDictService.findByDictName("PRODUCT_68_TYPE");
                Map<String,String> originMap = iotSystemDictService.findByDictName("ORIGIN_TYPE");
                Map<String,String> productSmallMap = iotSystemDictService.findByDictName("PRODUCT_SMALL_TYPE");
                iotCompanyVOList.forEach(infoVO->{
                    if(StringUtils.isNotBlank(infoVO.getType())){
                        infoVO.setTypeName(originMap.get(infoVO.getType()));
                    }
                    if(StringUtils.isNotBlank(infoVO.getInstrumentClassify())){
                        infoVO.setInstrumentClassifyName(product68Map.get(infoVO.getInstrumentClassify()));
                    }
                    if(StringUtils.isNotBlank(infoVO.getProductSubclass())){
                        infoVO.setProductSubclassName(productSmallMap.get(infoVO.getType()));
                    }
                });
            }

            return Envelop.getSuccessListWithPage(IotRequestMapping.Company.message_success_find_functions,iotCompanyVOList, page, size,count);
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
            IotProductVO iotProductVO = toEntity(jsonData, IotProductVO.class);
            iotProductBaseInfoService.addProduct(iotProductVO);
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_create);
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
            IotProductVO vo = iotProductBaseInfoService.findProductById(id);
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_find, vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = IotRequestMapping.Product.maintenanceUnitById)
    @ApiOperation(value = "获取维护单位")
    public Envelop<IotMaintenanceUnitVO> getList(
            @ApiParam(name = "productId", value = "产品", defaultValue = "1")
            @RequestParam(value = "productId", required = true) String productId) throws Exception {
        try {
            List<IotMaintenanceUnitVO> voList = iotProductBaseInfoService.maintenanceUnitById(productId);
            return Envelop.getSuccessList(IotRequestMapping.Company.message_success_find_functions,voList);
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError("查询失败");
        }
    }

    @PostMapping(value = IotRequestMapping.Product.delProduct)
    @ApiOperation(value = "删除产品", notes = "删除产品")
    public Envelop<IotProductVO> delCompany(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            iotProductBaseInfoService.delProduct(id);
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_find);
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
            IotProductVO iotProductVO = toEntity(jsonData, IotProductVO.class);
            iotProductBaseInfoService.updProduct(iotProductVO);
            return Envelop.getSuccess(IotRequestMapping.Common.message_success_find);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

}
