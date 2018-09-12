package com.yihu.jw.base.endpoint.dict;

import com.yihu.jw.base.service.dict.DictMedicineDistributeOrgService;
import com.yihu.jw.entity.base.dict.DictMedicineDistributeOrgDO;
import com.yihu.jw.restmodel.base.dict.DictMedicineDistributeOrgVO;
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
*
* 机构药品分发字典控制器
*
* @version
* <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年09月11日 	Created
 *
 * </pre>
* @since 1.
*/
@RestController
@RequestMapping(value = BaseRequestMapping.DictMedicineDistributeOrg.PREFIX)
@Api(value = "机构药品分发字典管理", description = "机构药品分发字典管理服务接口", tags = {"wlyy基础服务 - 机构药品分发字典管理服务接口"})
public class DictMedicineDistributeOrgEndpoint extends EnvelopRestEndpoint {

@Autowired
private DictMedicineDistributeOrgService dictMedicineDistributeOrgService;

@PostMapping(value = BaseRequestMapping.DictMedicineDistributeOrg.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<DictMedicineDistributeOrgVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    DictMedicineDistributeOrgDO dictMedicineDistributeOrg = toEntity(jsonData, DictMedicineDistributeOrgDO.class);
    dictMedicineDistributeOrg = dictMedicineDistributeOrgService.save(dictMedicineDistributeOrg);
    return success(dictMedicineDistributeOrg, DictMedicineDistributeOrgVO.class);
    }

    @PostMapping(value = BaseRequestMapping.DictMedicineDistributeOrg.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    dictMedicineDistributeOrgService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.DictMedicineDistributeOrg.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<DictMedicineDistributeOrgVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        DictMedicineDistributeOrgDO dictMedicineDistributeOrg = toEntity(jsonData, DictMedicineDistributeOrgDO.class);
        if (null == dictMedicineDistributeOrg.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        dictMedicineDistributeOrg = dictMedicineDistributeOrgService.save(dictMedicineDistributeOrg);
        return success(dictMedicineDistributeOrg, DictMedicineDistributeOrgVO.class);
        }

        @GetMapping(value = BaseRequestMapping.DictMedicineDistributeOrg.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<DictMedicineDistributeOrgVO> page (
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
            List<DictMedicineDistributeOrgDO> dictMedicineDistributeOrgs = dictMedicineDistributeOrgService.search(fields, filters, sorts, page, size);
                int count = (int)dictMedicineDistributeOrgService.getCount(filters);
                return success(dictMedicineDistributeOrgs, count, page, size, DictMedicineDistributeOrgVO.class);
         }

         @GetMapping(value = BaseRequestMapping.DictMedicineDistributeOrg.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<DictMedicineDistributeOrgVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<DictMedicineDistributeOrgDO> dictMedicineDistributeOrgs = dictMedicineDistributeOrgService.search(fields, filters, sorts);
                  return success(dictMedicineDistributeOrgs, DictMedicineDistributeOrgVO.class);
         }

 }
