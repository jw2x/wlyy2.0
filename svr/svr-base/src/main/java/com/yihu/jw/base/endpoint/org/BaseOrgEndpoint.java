package com.yihu.jw.base.endpoint.org;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.service.org.BaseOrgService;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.entity.base.org.BaseOrgDO;
import com.yihu.jw.restmodel.base.org.BaseOrgVO;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 机构信息控制器
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.BaseOrg.PREFIX)
@Api(value = "机构信息管理", description = "机构信息管理服务接口", tags = {"wlyy基础服务 - 机构信息管理服务接口"})
public class BaseOrgEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private BaseOrgService baseOrgService;

    @PostMapping(value = BaseRequestMapping.BaseOrg.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public Envelop create(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        BaseOrgDO baseOrg = toEntity(jsonObject.getJSONObject("org").toJSONString(), BaseOrgDO.class);
        String  msg = baseOrgService.createOrUpdateOrg(baseOrg,jsonObject.getJSONObject("admin"));
        if(StringUtils.endsWithIgnoreCase(msg, ConstantUtils.SUCCESS)){
            return success(msg);
        }
        return failed(msg);
    }

    @PostMapping(value = BaseRequestMapping.BaseOrg.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        baseOrgService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseOrg.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BaseOrgVO> update(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        BaseOrgDO baseOrg = toEntity(jsonData, BaseOrgDO.class);
        if (null == baseOrg.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        baseOrg = baseOrgService.save(baseOrg);
        return success(baseOrg, BaseOrgVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BaseOrg.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<BaseOrgVO> page(
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
        List<BaseOrgDO> baseOrgs = baseOrgService.search(fields, filters, sorts, page, size);
        int count = (int) baseOrgService.getCount(filters);
        return success(baseOrgs, count, page, size, BaseOrgVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BaseOrg.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<BaseOrgVO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<BaseOrgDO> baseOrgs = baseOrgService.search(fields, filters, sorts);
        return success(baseOrgs, BaseOrgVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BaseOrg.getOrgAreaTree)
    @ApiOperation(value = "获取机构树")
    public Envelop getOrgAreaTree (
            @ApiParam(name = "saasId", value = "saasId")
            @RequestParam(value = "saasId", required = false) String saasId) throws Exception {
       if(StringUtils.isEmpty(saasId)){
           return success(baseOrgService.getOrgAreaTree());
       }else {
           return success(baseOrgService.getOrgAreaTree(saasId));
       }
    }


    @PostMapping(value = BaseRequestMapping.BaseOrg.baseInfoList)
    @ApiOperation(value = "获取机构基础信息列表")
    public ListEnvelop queryBaseOrgInfolist(
            @ApiParam(name = "orgCode", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "orgCode", required = false) String orgCode,
            @ApiParam(name = "orgName", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "orgName", required = false) String orgName,
            @ApiParam(name = "orgStatus", value = "排序，规则参见说明文档")
            @RequestParam(value = "orgStatus", required = false) String orgStatus,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "0")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<Map<String, Object>> list = baseOrgService.queryOrgBaseInfoList(orgCode, orgName, orgStatus,page,size,sorts);
        return success(list);
    }

    @GetMapping(value = BaseRequestMapping.BaseOrg.check_code)
    @ApiOperation(value = "检查代码是否可用(message=1代表可用，message=0代表不可用)")
    public Envelop checkCode (
            @ApiParam(name = "code", value = "机构代码", required = true)
            @RequestParam(value = "code", required = false) String code) throws Exception {
        if (baseOrgService.existCode(code)) {
            return success("1");
        } else {
            return success("0");
        }
    }
}
