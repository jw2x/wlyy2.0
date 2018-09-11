package com.yihu.jw.base.endpoint.dict;

import com.yihu.jw.base.service.dict.DictMedicineSubjectService;
import com.yihu.jw.entity.base.dict.DictMedicineSubjectDO;
import com.yihu.jw.restmodel.base.dict.DictMedicineSubjectVO;
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
* 药品科目类别字典控制器
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
@RequestMapping(value = BaseRequestMapping.DictMedicineSubject.PREFIX)
@Api(value = "药品科目类别字典管理", description = "药品科目类别字典管理服务接口", tags = {"wlyy基础服务 - 药品科目类别字典管理服务接口"})
public class DictMedicineSubjectEndpoint extends EnvelopRestEndpoint {

@Autowired
private DictMedicineSubjectService dictMedicineSubjectService;

@PostMapping(value = BaseRequestMapping.DictMedicineSubject.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiOperation(value = "创建")
public ObjEnvelop<DictMedicineSubjectVO> create (
    @ApiParam(name = "json_data", value = "Json数据", required = true)
    @RequestBody String jsonData) throws Exception {
    DictMedicineSubjectDO dictMedicineSubject = toEntity(jsonData, DictMedicineSubjectDO.class);
    dictMedicineSubject = dictMedicineSubjectService.save(dictMedicineSubject);
    return success(dictMedicineSubject, DictMedicineSubjectVO.class);
    }

    @PostMapping(value = BaseRequestMapping.DictMedicineSubject.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
    @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
    @RequestParam(value = "ids") String ids) {
    dictMedicineSubjectService.delete(ids.split(","));
    return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.DictMedicineSubject.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<DictMedicineSubjectVO> update (
        @ApiParam(name = "json_data", value = "Json数据", required = true)
        @RequestBody String jsonData) throws Exception {
        DictMedicineSubjectDO dictMedicineSubject = toEntity(jsonData, DictMedicineSubjectDO.class);
        if (null == dictMedicineSubject.getId()) {
        return failed("ID不能为空", ObjEnvelop.class);
        }
        dictMedicineSubject = dictMedicineSubjectService.save(dictMedicineSubject);
        return success(dictMedicineSubject, DictMedicineSubjectVO.class);
        }

        @GetMapping(value = BaseRequestMapping.DictMedicineSubject.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<DictMedicineSubjectVO> page (
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
            List<DictMedicineSubjectDO> dictMedicineSubjects = dictMedicineSubjectService.search(fields, filters, sorts, page, size);
                int count = (int)dictMedicineSubjectService.getCount(filters);
                return success(dictMedicineSubjects, count, page, size, DictMedicineSubjectVO.class);
         }

         @GetMapping(value = BaseRequestMapping.DictMedicineSubject.LIST)
         @ApiOperation(value = "获取列表")
         public ListEnvelop<DictMedicineSubjectVO> list (
             @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
             @RequestParam(value = "fields", required = false) String fields,
             @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
             @RequestParam(value = "filters", required = false) String filters,
             @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
             @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
             List<DictMedicineSubjectDO> dictMedicineSubjects = dictMedicineSubjectService.search(fields, filters, sorts);
                  return success(dictMedicineSubjects, DictMedicineSubjectVO.class);
         }

 }
