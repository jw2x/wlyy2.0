package com.yihu.jw.base.endpoint.population;

import ch.qos.logback.core.util.TimeUtil;
import com.yihu.jw.base.service.population.BaseYearService;
import com.yihu.jw.entity.base.population.BaseYearDO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.jw.util.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 基数-年份维护
 * Created by zdm on 2018/10/12.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.BaseYear.PREFIX)
@Api(value = "年份管理", description = "基础人口-年份管理接口", tags = {"wlyy基础服务 - 基础人口-年份管理接口"})
public class BaseYearEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private BaseYearService baseYearService;

    @PostMapping(value = BaseRequestMapping.BaseYear.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取系统当前年份，创建时间列表至1990年")
    public ObjEnvelop<Boolean> create() throws Exception {
        int year = DateUtil.getNowYear();
        baseYearService.save(year);
        return success(true);
    }

    @PostMapping(value = BaseRequestMapping.BaseYear.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        baseYearService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseYear.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BaseYearDO> update(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        BaseYearDO BaseYear = toEntity(jsonData, BaseYearDO.class);
        if (null == BaseYear.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        BaseYear = baseYearService.save(BaseYear);
        return success(BaseYear);
    }


    @GetMapping(value = BaseRequestMapping.BaseYear.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<String> getYearList() throws Exception {
        List<String> BaseYears = baseYearService.getYearList();
        return success(BaseYears);
    }

}
