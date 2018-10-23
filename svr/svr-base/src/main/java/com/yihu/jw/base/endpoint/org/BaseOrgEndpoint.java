package com.yihu.jw.base.endpoint.org;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.service.org.BaseOrgService;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.entity.base.org.BaseOrgDO;
import com.yihu.jw.restmodel.base.org.BaseOrgVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = BaseRequestMapping.BaseOrg.CREATE )
    @ApiOperation(value = "创建")
    public Envelop create(
            @ApiParam(name = "jsonData", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
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

    @PostMapping(value = BaseRequestMapping.BaseOrg.UPDATE )
    @ApiOperation(value = "更新")
    public Envelop update(
            @ApiParam(name = "jsonData", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        BaseOrgDO baseOrg = toEntity(jsonObject.getJSONObject("org").toJSONString(), BaseOrgDO.class);
        String  msg = baseOrgService.createOrUpdateOrg(baseOrg,jsonObject.getJSONObject("admin"));
        if(StringUtils.endsWithIgnoreCase(msg, ConstantUtils.SUCCESS)){
            return success(msg);
        }
        return failed(msg);
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
    @GetMapping(value = BaseRequestMapping.BaseOrg.queryOneById)
    @ApiOperation(value = "根据机构id查询机构")
    public Envelop queryOne (
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id) throws Exception {
        JSONObject result = baseOrgService.queryOneById(id);
        if(StringUtils.endsWithIgnoreCase(ConstantUtils.FAIL,result.getString("response"))){
            return failed(result.getString("msg"));
        }
      return success(result.get("msg"));
    }


    @PostMapping(value = BaseRequestMapping.BaseOrg.baseInfoList)
    @ApiOperation(value = "获取机构基础信息列表")
    public PageEnvelop queryBaseOrgInfolist(
            @ApiParam(name = "codeOrName", value = "机构名称或机构代码")
            @RequestParam(value = "codeOrName", required = false) String codeOrName,
            @ApiParam(name = "orgStatus", value = "机构状态")
            @RequestParam(value = "orgStatus", required = false) String orgStatus,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        JSONObject result = baseOrgService.queryOrgBaseInfoList(codeOrName, orgStatus,page,size);
        return success(result.getJSONArray("msg"),result.getInteger("count"),page,size);
    }

    @GetMapping(value = BaseRequestMapping.BaseOrg.check_code)
    @ApiOperation(value = "检查代码是否可用(message=available代表可用，message=inavailable代表不可用)")
    public Envelop checkCode (
            @ApiParam(name = "code", value = "机构代码", required = true)
            @RequestParam(value = "code", required = false) String code) throws Exception {
        if (baseOrgService.existCode(code)) {
            return success("inavailable");
        } else {
            return success("available");
        }
    }

    /**
     * 生效或失效某个机构
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    @PostMapping(value = BaseRequestMapping.BaseOrg.enableOrDis)
    @ApiOperation(value = "生效或失效某个机构")
    public Envelop enableOrDisableOrg(
            @ApiParam(name = "id", value = "医生标识")
            @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "status", value = "生效或失效标识")
            @RequestParam(value = "status", required = true) String status) throws Exception {
        String str = baseOrgService.enableOrDisableOrg(id, status);
        JSONObject jsonObject = JSONObject.parseObject(str);
        if (jsonObject.getString("response").equalsIgnoreCase(ConstantUtils.FAIL)) {
            return failed(jsonObject.getString("msg"));
        }
        return success(jsonObject.getString("response"));
    }

    /**
     * 查询机构列表
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = BaseRequestMapping.BaseOrg.queryCodeList)
    @ApiOperation(value = "查询机构列表")
    public ListEnvelop queryOrgCodeAndNameList() throws Exception {
        return success(baseOrgService.findOrgCodeListBySaasId(""));
    }
}
