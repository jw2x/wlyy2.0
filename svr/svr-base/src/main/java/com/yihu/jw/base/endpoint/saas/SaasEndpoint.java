package com.yihu.jw.base.endpoint.saas;

import com.yihu.jw.base.service.UserService;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.base.service.SaasService;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.restmodel.base.saas.SaasVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
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
 * Endpoint - SAAS
 * Created by progr1mmer on 2018/8/14.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.Saas.PREFIX)
@Api(value = "Saas管理", description = "Saas管理服务接口", tags = {"SAAS - Saas管理服务接口"})
public class SaasEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private SaasService saasService;
    @Autowired
    private UserService userService;

    @PostMapping(value = BaseRequestMapping.Saas.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public Envelop create (
            @ApiParam(name = "saas", value = "Json数据", required = true)
            @RequestParam(value = "saas") SaasDO saasDO,
            @ApiParam(name = "user", value = "Json数据", required = true)
            @RequestParam(value = "user") UserDO userDO) throws Exception {
        if (userService.search("username=" + userDO.getUsername()).size() > 0) {
            return failed("管理员用户名已存在", Envelop.class);
        }
        saasService.save(saasDO, userDO);
        return success("创建成功");
    }

    @PostMapping(value = BaseRequestMapping.Saas.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        saasService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.Saas.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public Envelop update (
            @ApiParam(name = "json", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        SaasDO saasDO = toEntity(jsonData, SaasDO.class);
        if (null == saasDO.getId()) {
            return failed("ID不能为空", Envelop.class);
        }
        saasDO = saasService.save(saasDO);
        return success(saasDO);
    }

    @GetMapping(value = BaseRequestMapping.Saas.PAGE)
    @ApiOperation(value = "获取分页")
    public PageEnvelop<SaasVO> page (
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
        List<SaasDO> saasDOS = saasService.search(fields, filters, sorts, page, size);
        int count = (int)saasService.getCount(filters);
        return success(saasDOS, count, page, size, SaasVO.class);
    }

    @GetMapping(value = BaseRequestMapping.Saas.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<SaasVO> list (
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<SaasDO> saasDOS = saasService.search(fields, filters, sorts);
        return success(saasDOS, SaasVO.class);
    }

    @PostMapping(value = BaseRequestMapping.Saas.AUDIT)
    @ApiOperation(value = "审核")
    public Envelop audit (
            @ApiParam(name = "id", value = "SaasId", required = true)
            @RequestParam(value = "id") String id,
            @ApiParam(name = "status", value = "状态", required = true)
            @RequestParam(value = "status") SaasDO.Status status) throws Exception {
        SaasDO saasDO = saasService.retrieve(id);
        if (null == saasDO) {
            return failed("无相关SAAS配置", Envelop.class);
        }
        saasDO.setStatus(status);
        saasService.save(saasDO);
        return success("操作成功");
    }

}
