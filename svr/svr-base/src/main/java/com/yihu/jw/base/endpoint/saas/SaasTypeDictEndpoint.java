package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.service.saas.SaasTypeDictService;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import com.yihu.jw.restmodel.base.saas.SaasTypeDictVO;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoint - SaasTypeDict
 * Created by zdm on 2018/10/10.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.SaasTypeDict.PREFIX)
@Api(value = "Saas类型管理", description = "Saas类型管理服务接口", tags = {"wlyy基础服务 - Saas类型管理服务接口"})
public class SaasTypeDictEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SaasTypeDictService saasTypeDictService;
//    @Autowired
//    private SaasDefaultModuleFunctionService saasDefaultModuleFunctionService;

    @PostMapping(value = BaseRequestMapping.SaasTypeDict.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<SaasTypeDictVO> create(
            @ApiParam(name = "saasTypeDictJson", value = "saas类型Json数据")
            @RequestParam(value = "saasTypeDictJson", required = true) String saasTypeDictJson,
            @ApiParam(name = "saasTypeDefaultModuleIds", value = "Saas类型默认的模块id,用逗号隔开")
            @RequestParam(value = "saasTypeDefaultModuleIds", required = true) String saasTypeDefaultModuleIds) throws Exception {
        SaasTypeDictDO saasTypeDictDO = toEntity(saasTypeDictJson, SaasTypeDictDO.class);
        //名称重复判断
        List<SaasTypeDictDO> saasTypeDictDOList = saasTypeDictService.findByField("name", saasTypeDictDO.getName());
        if (null != saasTypeDictDOList && saasTypeDictDOList.size() > 0) {
            return failed("租户类型名称重复！", ObjEnvelop.class);
        }
        saasTypeDictDO = saasTypeDictService.save(saasTypeDictDO, saasTypeDefaultModuleIds);
        return success("创建成功", saasTypeDictDO, SaasTypeDictVO.class);
    }


    @PostMapping(value = BaseRequestMapping.Saas.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<SaasTypeDictVO> update(
            @ApiParam(name = "saasTypeDictJson", value = "saas类型Json数据")
            @RequestParam(value = "saasTypeDictJson", required = true) String saasTypeDictJson,
            @ApiParam(name = "saasTypeDefaultModuleIds", value = "Saas类型默认的模块id,用逗号隔开")
            @RequestParam(value = "saasTypeDefaultModuleIds", required = true) String saasTypeDefaultModuleIds) throws Exception {
        SaasTypeDictDO saasTypeDictDO = toEntity(saasTypeDictJson, SaasTypeDictDO.class);
        if (null == saasTypeDictDO.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        if (saasTypeDictService.isSaasTypeDictExistByNameAndId(saasTypeDictDO.getId(),saasTypeDictDO.getName())) {
            return failed("租户类型名称重复！", ObjEnvelop.class);
        }
        //删除关联的模块
//        saasDefaultModuleFunctionService.deleteBySaasType(saasTypeDictDO.getCode());
        saasTypeDictDO = saasTypeDictService.save(saasTypeDictDO, saasTypeDefaultModuleIds);
        return success("更新成功！", saasTypeDictDO, SaasTypeDictVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Saas.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SaasTypeDictVO> page(
            @ApiParam(name = "name", value = "租户类型名称")
            @RequestParam(value = "name", required = false) String name,
            @ApiParam(name = "status", value = "设施状态：失效-invalid，生效effective")
            @RequestParam(value = "status", required = false) SaasTypeDictDO.Status status,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        StringBuffer s = new StringBuffer();
        if (StringUtils.isNotEmpty(name)) {
            s.append("name?" + name + "g1;");
        }
        if (null != status) {
            s.append("status=" + status);
        }
        //状态（生效中>已失效）>租户类型ID（租户类型ID降序排序，即最新创建的租户类型排在最前）
        String sorts = "-status,-id";
        String filters = s.toString();
        List<SaasTypeDictDO> saasTypeDictDOS = saasTypeDictService.search("", filters, sorts, page, size);
        int count = (int) saasTypeDictService.getCount(filters);
        return success(saasTypeDictDOS, count, page, size, SaasTypeDictVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Saas.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasTypeDictVO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SaasTypeDictDO> saasTypeDictDOS = saasTypeDictService.search(fields, filters, sorts);
        return success(saasTypeDictDOS, SaasTypeDictVO.class);
    }


}
