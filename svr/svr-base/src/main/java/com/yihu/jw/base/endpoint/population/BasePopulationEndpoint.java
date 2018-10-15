package com.yihu.jw.base.endpoint.population;

import com.yihu.jw.base.service.area.BaseCityService;
import com.yihu.jw.base.service.area.BaseProvinceService;
import com.yihu.jw.base.service.area.BaseTownService;
import com.yihu.jw.base.service.population.BasePopulationService;
import com.yihu.jw.base.service.saas.SaasService;
import com.yihu.jw.entity.base.area.BaseTownDO;
import com.yihu.jw.entity.base.population.BasePopulationDO;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.restmodel.base.population.BasePopulationVO;
import com.yihu.jw.restmodel.web.Envelop;
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
import java.util.Map;

/**
 * 基础人口基数信息控制器
 *
 * @version <pre>
 *                                                       Author	Version		Date		Changes
 *                                                       litaohong 	1.0  		2018年09月26日 	update
 *
 *                                                       </pre>
 * @since 1.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.BasePopulation.PREFIX)
@Api(value = "基础人口基数信息管理", description = "基础人口基数信息管理服务接口", tags = {"wlyy基础服务 - 基础人口基数信息管理服务接口"})
public class BasePopulationEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private BasePopulationService basePopulationService;
    @Autowired
    private SaasService saasService;
    @Autowired
    private BaseTownService baseTownService;
    @Autowired
    private BaseProvinceService baseProvinceService;
    @Autowired
    private BaseCityService baseCityService;


    @PostMapping(value = BaseRequestMapping.BasePopulation.CREATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建")
    public ObjEnvelop<BasePopulationVO> create(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        BasePopulationDO basePopulation = toEntity(jsonData, BasePopulationDO.class);
        //根据saasid获取所属机构的行政区划
        if (StringUtils.isNotBlank(basePopulation.getSaasId())) {
            basePopulation = updateBasePopulation(basePopulation);
        } else {
            return failed("租户id不能为空！", ObjEnvelop.class);
        }
        basePopulation = basePopulationService.save(basePopulation);
        return success(basePopulation, BasePopulationVO.class);
    }

    @PostMapping(value = BaseRequestMapping.BasePopulation.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        basePopulationService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BasePopulation.UPDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新")
    public ObjEnvelop<BasePopulationVO> update(
            @ApiParam(name = "json_data", value = "Json数据", required = true)
            @RequestBody String jsonData) throws Exception {
        BasePopulationDO basePopulation = toEntity(jsonData, BasePopulationDO.class);
        if (null == basePopulation.getId()) {
            return failed("ID不能为空", ObjEnvelop.class);
        }
        //根据saasid获取所属机构的行政区划
        if (StringUtils.isNotBlank(basePopulation.getSaasId())) {
            basePopulation = updateBasePopulation(basePopulation);
        } else {
            return failed("租户id不能为空！", ObjEnvelop.class);
        }
        basePopulation = basePopulationService.save(basePopulation);
        return success(basePopulation, BasePopulationVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BasePopulation.PAGE)
    @ApiOperation(value = "基数统计-获取分页")
    public PageEnvelop<BasePopulationVO> page(
            @ApiParam(name = "saasName", value = "租户名称，支持模糊搜索")
            @RequestParam(value = "saasName", required = false) String saasName,
            @ApiParam(name = "provinceCode", value = "省份编码")
            @RequestParam(value = "provinceCode", required = false) String provinceCode,
            @ApiParam(name = "cityCode", value = "城市编码")
            @RequestParam(value = "cityCode", required = false) String cityCode,
            @ApiParam(name = "year", value = "时间")
            @RequestParam(value = "year", required = false) String year,
            @ApiParam(name = "page", value = "分页大小", required = true, defaultValue = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(name = "size", value = "页码", required = true, defaultValue = "15")
            @RequestParam(value = "size") int size) throws Exception {
        StringBuffer s = new StringBuffer();
        if (StringUtils.isNotBlank(saasName)) {
            s.append("saasName?" + saasName + " g1;");
        }
        if (StringUtils.isNotBlank(provinceCode)) {
            s.append("provinceCode=" + provinceCode);
        }
        if (StringUtils.isNotBlank(cityCode)) {
            s.append("cityCode=" + cityCode);
        }
        if (StringUtils.isNotBlank(year)) {
            s.append("year=" + year);
        }
        //时间（最近时间排最前）>租户创建时间（最新创建租户排最前）
        String sorts = "-createTime,-saasCreateTime";
        String filters = s.toString();
        List<BasePopulationDO> basePopulations = basePopulationService.search(null, filters, sorts, page, size);
        int count = (int) basePopulationService.getCount(filters);
        return success(basePopulations, count, page, size, BasePopulationVO.class);
    }

    @GetMapping(value = BaseRequestMapping.BasePopulation.LIST)
    @ApiOperation(value = "获取列表")
    public ListEnvelop<BasePopulationVO> list(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        List<BasePopulationDO> basePopulations = basePopulationService.search(fields, filters, sorts);
        return success(basePopulations, BasePopulationVO.class);
    }

    /**
     * 根据基数中的saasid,更新基数中租户相关信息
     *
     * @param basePopulation
     * @return
     * @throws Exception
     */
    public BasePopulationDO updateBasePopulation(BasePopulationDO basePopulation) throws Exception {
        SaasDO saasDO = saasService.findById(basePopulation.getSaasId());
        basePopulation.setSaasCreateTime(saasDO.getCreateTime());
        String areaCode = saasDO.getAreaNumber();
        String filters = "province?" + areaCode + " g1;city?" + areaCode + " g1;code?" + areaCode + " g1;";
        List<BaseTownDO> baseTowns = baseTownService.search(filters);
        BaseTownDO baseTownDO = (null != baseTowns && baseTowns.size() > 0 ? baseTowns.get(0) : null);
        if (null != baseTownDO) {
            if (baseTownDO.getCode().equals(areaCode)) {
                basePopulation.setProvinceCode(baseTownDO.getProvince());
                basePopulation.setProvinceName(baseProvinceService.getNameByCode(baseTownDO.getProvince()));
                //市编码
                basePopulation.setCityCode(baseTownDO.getCity());
                basePopulation.setCityName(baseCityService.getNameByCode(baseTownDO.getCity()));
                //区县编码
                basePopulation.setDistrictCode(areaCode);
                basePopulation.setDistrictName(baseTownDO.getName());
            } else if (baseTownDO.getCity().equals(areaCode)) {
                basePopulation.setProvinceCode(baseTownDO.getProvince());
                basePopulation.setProvinceName(baseProvinceService.getNameByCode(baseTownDO.getProvince()));
                //市编码
                basePopulation.setCityCode(areaCode);
                basePopulation.setCityName(baseCityService.getNameByCode(areaCode));
                //区县编码
                basePopulation.setDistrictCode("");
                basePopulation.setDistrictName("");

            } else if (baseTownDO.getProvince().equals(areaCode)) {
                //省编码
                basePopulation.setProvinceCode(areaCode);
                basePopulation.setProvinceName(baseProvinceService.getNameByCode(areaCode));
                //市编码
                basePopulation.setCityCode("");
                basePopulation.setCityName("");
                //区县编码
                basePopulation.setDistrictCode("");
                basePopulation.setDistrictName("");
            }
        }
        //更新慢病总人数
        basePopulation.setNcdNum(basePopulation.getHbpNum() + basePopulation.getDmNum());
        return basePopulation;
    }

    @PostMapping(value = BaseRequestMapping.BasePopulation.FINDBYID)
    @ApiOperation(value = "根据基数统计id获取信息")
    public ObjEnvelop<BasePopulationVO> audit(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(value = "id") String id) throws Exception {
        BasePopulationDO basePopulationDO = basePopulationService.findById(id);
        if (null == basePopulationDO) {
            return failed("无相关基数统计信息", ObjEnvelop.class);
        }
        return success(basePopulationDO,BasePopulationVO.class);
    }



}
