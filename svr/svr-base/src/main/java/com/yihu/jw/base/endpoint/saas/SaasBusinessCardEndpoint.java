package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.service.saas.SaasBusinessCardService;
import com.yihu.jw.entity.base.saas.SaasBusinessCardDO;
import com.yihu.jw.restmodel.base.saas.SaasBusinessCardVO;
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
import java.util.Map;

/**
 * Endpoint - SAAS名片
 * Created by progr1mmer on 2018/8/14.
 * @author progr1mmer
 */
@RestController
@RequestMapping(value = BaseRequestMapping.SaasBusinessCard.PREFIX)
@Api(value = "SAAS名片管理", description = "SAAS名片管理服务接口", tags = {"wlyy基础服务 - SAAS名片管理服务接口"})
public class SaasBusinessCardEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SaasBusinessCardService saasBusinessCardService;

    @PostMapping(value = BaseRequestMapping.SaasBusinessCard.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SaasBusinessCardVO> create (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasBusinessCardDO saasBusinessCardDO = toEntity(jsonData, SaasBusinessCardDO.class);
        saasBusinessCardDO = saasBusinessCardService.save(saasBusinessCardDO);
        return success(saasBusinessCardDO, SaasBusinessCardVO.class);
    }

    @PostMapping(value = BaseRequestMapping.SaasBusinessCard.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        saasBusinessCardService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.SaasBusinessCard.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasBusinessCardDO saasBusinessCardDO = toEntity(jsonData, SaasBusinessCardDO.class);
        if (null == saasBusinessCardDO.getId()) {
            return failed("ID不能为空", Envelop.class);
        }
        saasBusinessCardDO = saasBusinessCardService.save(saasBusinessCardDO);
        return success(saasBusinessCardDO);
    }

    @GetMapping(value = BaseRequestMapping.SaasBusinessCard.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SaasBusinessCardVO> page (
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
        List<SaasBusinessCardDO> saasBusinessCardDOS = saasBusinessCardService.search(fields, filters, sorts, page, size);
        int count = (int)saasBusinessCardService.getCount(filters);
        return success(saasBusinessCardDOS, count, page, size, SaasBusinessCardVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SaasBusinessCard.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasBusinessCardVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SaasBusinessCardDO> saasBusinessCardDOS = saasBusinessCardService.search(fields, filters, sorts);
        return success(saasBusinessCardDOS, SaasBusinessCardVO.class);
    }

    @GetMapping(value = BaseRequestMapping.SaasBusinessCard.GENERATE)
    @ApiOperation(value = "生成名片")
    public ObjEnvelop<Map> generate (
            @ApiParam(name = "type", value = "名片类型", required = true)
            @RequestParam(value = "type") SaasBusinessCardDO.Type type,
            @ApiParam(name = "saasId", value = "SAAS ID", required = true)
            @RequestParam(value = "saasId") String saasId,
            @ApiParam(name = "sourceId", value = "实体对象ID，如医生、居民", required = true)
            @RequestParam(value = "sourceId") String sourceId,
            @ApiParam(name = "orgId", value = "机构编码")
            @RequestParam(value = "orgId", required = false) String orgId) throws Exception {
        Map<String, Object> card = saasBusinessCardService.generateBusinessCard(type, saasId, sourceId, orgId);
        return success(card);
    }

}
