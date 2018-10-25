package com.yihu.jw.base.endpoint.team;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.service.team.BaseTeamService;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.entity.base.team.BaseTeamDO;
import com.yihu.jw.restmodel.base.team.BaseTeamVO;
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

/**
 * 团队信息控制器
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong 	1.0  		2018年08月31日 	Created
 *
 * </pre>
 * @since 1.
 */
@RestController
@RequestMapping(value = BaseRequestMapping.BaseTeam.PREFIX)
@Api(value = "团队信息管理", description = "团队信息管理服务接口", tags = {"wlyy基础服务 - 团队信息管理服务接口"})
public class BaseTeamEndpoint extends EnvelopRestEndpoint {

    @Autowired
    private BaseTeamService baseTeamService;

    @PostMapping(value = BaseRequestMapping.BaseTeam.CREATE)
    @ApiOperation(value = "创建团队")
    public Envelop create(
            @ApiParam(name = " jsonData", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        String msg = baseTeamService.createTeam(jsonData);
        if (StringUtils.equalsIgnoreCase(ConstantUtils.SUCCESS, msg)) {
            return success(msg);
        }
        return failed(msg);
    }

    @PostMapping(value = BaseRequestMapping.BaseTeam.DELETE)
    @ApiOperation(value = "删除")
    public Envelop delete(
            @ApiParam(name = "ids", value = "id串，中间用,分隔", required = true)
            @RequestParam(value = "ids") String ids) {
        baseTeamService.delete(ids.split(","));
        return success("删除成功");
    }

    @PostMapping(value = BaseRequestMapping.BaseTeam.UPDATE)
    @ApiOperation(value = "更新团队")
    public Envelop update(
            @ApiParam(name = " jsonData", value = "Json数据", required = true)
            @RequestParam String jsonData) throws Exception {
        JSONObject jsonObject = baseTeamService.updateTeam(jsonData);
        if (jsonObject.getString("response").equalsIgnoreCase(ConstantUtils.FAIL)) {
            return failed(jsonObject.getString("msg"));
        }
        return success(jsonObject.getString("msg"));
    }

        @GetMapping(value = BaseRequestMapping.BaseTeam.PAGE)
        @ApiOperation(value = "获取分页")
        public PageEnvelop<BaseTeamVO> page (
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
            List<BaseTeamDO> baseTeams = baseTeamService.search(fields, filters, sorts, page, size);
            int count = (int) baseTeamService.getCount(filters);
            return success(baseTeams, count, page, size, BaseTeamVO.class);
        }

        @GetMapping(value = BaseRequestMapping.BaseTeam.LIST)
        @ApiOperation(value = "获取列表")
        public ListEnvelop<BaseTeamVO> list (
                @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
                @RequestParam(value = "fields", required = false) String fields,
                @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
                @RequestParam(value = "filters", required = false) String filters,
                @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
                @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
            List<BaseTeamDO> baseTeams = baseTeamService.search(fields, filters, sorts);
            return success(baseTeams, BaseTeamVO.class);
        }


         @GetMapping(value = BaseRequestMapping.BaseTeam.queryMemberList)
         @ApiOperation(value = "查看团队成员列表")
         public Envelop getTeamMemberList (
             @ApiParam(name = "orgCode", value = "机构标识")
             @RequestParam(value = "orgCode", required = true) String orgCode,
             @ApiParam(name = "teamCode", value = "团队标识")
             @RequestParam(value = "teamCode", required = true) String teamCode) throws Exception {
             JSONObject baseTeams = baseTeamService.getTeamMemberList(orgCode, teamCode);
             if (baseTeams.getString("response").equalsIgnoreCase(ConstantUtils.FAIL)) {
                 return failed(baseTeams.getString("msg"));
             }
            return success(baseTeams.getJSONArray("msg"));
         }

         @GetMapping(value = BaseRequestMapping.BaseTeam.queryOne)
         @ApiOperation(value = "查询某一个团队")
         public Envelop getTeamById (
             @ApiParam(name = "teamCode", value = "根据团队标识查询某一个团队信息")
             @RequestParam(value = "teamCode", required = true) String teamId) throws Exception {
             JSONObject team = baseTeamService.getTeamById(teamId);
             if (team.getString("response").equalsIgnoreCase(ConstantUtils.FAIL)) {
                 return failed(team.getString("msg"));
             }
             return success(team.getJSONObject("msg"));
         }

         @GetMapping(value = BaseRequestMapping.BaseTeam.getTeamTree)
         @ApiOperation(value = "团队机构/科室/医生树形结构")
         public Envelop generateOneOrgDeptDoctorTree (
             @ApiParam(name = "jsonData", value = "json,包含团队机构，团队机构名称，已勾选的医生")
             @RequestParam(value = "jsonData", required = true) String jsonData) throws Exception {
             JSONObject team = baseTeamService.generateOneOrgDeptDoctorTree(jsonData);
             if (team.getString("response").equalsIgnoreCase(ConstantUtils.FAIL)) {
                 return failed(team.getString("msg"));
             }
             return success(team.getJSONArray("msg"));
         }
}
