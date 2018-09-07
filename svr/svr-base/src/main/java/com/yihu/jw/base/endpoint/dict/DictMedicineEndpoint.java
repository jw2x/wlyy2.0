package com.yihu.jw.base.endpoint.dict;

import com.yihu.jw.base.service.dict.DictMedicineDistributeOrgService;
import com.yihu.jw.base.service.dict.DictMedicineService;
import com.yihu.jw.entity.base.dict.DictMedicineDO;
import com.yihu.jw.restmodel.base.dict.DictMedicineVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 药品字典控制器
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * Administrator 	1.0  		2018年09月07日 	Created
 *
 * </pre>
 * @since 1.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.DictMedicine.PREFIX)
@Api(value = "药品字典管理", description = "药品字典管理服务接口", tags = {"wlyy基础服务 - 药品字典管理服务接口"})
public class DictMedicineEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private DictMedicineService dictMedicineService;
    @Autowired
    private DictMedicineDistributeOrgService dictMedicineDistributeOrgService;

    @PostMapping(value = BaseRequestMapping.DictMedicine.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<DictMedicineVO> create(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        DictMedicineDO dictMedicine = toEntity(jsonData, DictMedicineDO.class);
        dictMedicine = dictMedicineService.save(dictMedicine);
        return success(dictMedicine, DictMedicineVO.class);
    }

    @PostMapping(value = BaseRequestMapping.DictMedicine.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        dictMedicineService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.DictMedicine.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<DictMedicineVO> update(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        DictMedicineDO dictMedicine = toEntity(jsonData, DictMedicineDO.class);
        if (null == dictMedicine.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        dictMedicine = dictMedicineService.save(dictMedicine);
        return success(dictMedicine, DictMedicineVO.class);
    }

    @GetMapping(value = BaseRequestMapping.DictMedicine.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<DictMedicineVO> page(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        List<DictMedicineDO> dictMedicines = dictMedicineService.search(fields, filters, sorts, page, size);
        int count = (int) dictMedicineService.getCount(filters);
        return success(dictMedicines, count, page, size, DictMedicineVO.class);
    }

    @GetMapping(value = BaseRequestMapping.DictMedicine.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<DictMedicineVO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<DictMedicineDO> dictMedicines = dictMedicineService.search(fields, filters, sorts);
        return success(dictMedicines, DictMedicineVO.class);
    }

}
