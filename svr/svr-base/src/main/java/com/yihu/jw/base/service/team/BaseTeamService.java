package com.yihu.jw.base.service.team;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.base.dao.team.BaseTeamDao;
import com.yihu.jw.base.dao.team.BaseTeamMemberDao;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.entity.base.team.BaseTeamMemberDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.team.BaseTeamDO;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 团队信息服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class BaseTeamService extends BaseJpaService<BaseTeamDO, BaseTeamDao> {

    @Autowired
    private BaseTeamMemberService baseTeamMemberService;

    @Autowired
    private BaseTeamMemberDao baseTeamMemberDao;

    @Autowired
    private BaseTeamDao baseTeamDao;

    @Autowired
    private ObjectMapper objectMapper;

    public String createTeam(String jsonData) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        JSONObject teamJSONObject = jsonObject.getJSONObject("team");
        JSONArray teamMemberArray = jsonObject.getJSONArray("teamMember");
        if(null == teamJSONObject || CollectionUtils.isEmpty(teamMemberArray) ){
            return ConstantUtils.FAIL;
        }
        BaseTeamDO baseTeamDO = objectMapper.readValue(teamJSONObject.toJSONString(),BaseTeamDO.class);

        List<BaseTeamMemberDO> memberList = new ArrayList<>();
        teamMemberArray.forEach((teamMember)->{
            try {
                BaseTeamMemberDO baseTeamMemberDO = objectMapper.readValue(teamMember.toString(),BaseTeamMemberDO.class);
                memberList.add(baseTeamMemberDO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.save(baseTeamDO);
        baseTeamMemberService.batchInsert(memberList);
        return ConstantUtils.SUCCESS;
    }

    /**
     * 获取团队机构列表
     * @return
     */
    public List<Map<String,Object>> getTeamOrgList(){
        List<Map<String,Object>> result = new ArrayList<>();
        result = baseTeamDao.getTeamOrgList();
        return result;
    }

    /**
     * 查看团队成员
     * @param orgCode
     * @param teamCode
     * @return
     */
    public String getTeamMemberList(String orgCode,String teamCode){
        List<Map<String,Object>> result = new ArrayList<>();
        if( StringUtils.isEmpty(orgCode) || StringUtils.isEmpty(orgCode) ){
            return null;
        }
        JSONArray list = new JSONArray();
        result = baseTeamMemberDao.getTeamMemberList();
        for(Map<String,Object> map : result){
            JSONObject jsonObject = JSONObject.parseObject(map.toString());
            list.add(jsonObject);
        }
        return list.toJSONString();
    }

}
