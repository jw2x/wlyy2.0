package com.yihu.jw.base.service.team;

import com.yihu.jw.base.dao.doctor.BaseDoctorRoleDao;
import com.yihu.jw.base.dao.team.BaseTeamMemberDao;
import com.yihu.jw.entity.base.patient.PatientMedicareCardDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.team.BaseTeamMemberDO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 团队成员服务service
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
public class BaseTeamMemberService extends BaseJpaService<BaseTeamMemberDO, BaseTeamMemberDao> {
    @Autowired
    private BaseTeamMemberDao baseTeamMemberDao;

    /**
     * 根据团队标识获取团队成员Id列表
     *
     * @param teamCode
     * @return
     */
    public Set<Object> findMemberIdList(String teamCode) {
        Set<Object> result = new HashSet<>();
        if (StringUtils.isEmpty(teamCode)) {
            return result;
        }
        return baseTeamMemberDao.findIdListByTeamCode(teamCode);
    }

    /**
     * 根据团队标识获取居民团队成员列表
     * @param teamCode
     * @return
     */
    public List<BaseTeamMemberDO> findMembersByTeamCode(String teamCode){
        List<BaseTeamMemberDO> result = new ArrayList<>();
        if(org.apache.commons.lang.StringUtils.isEmpty(teamCode)){
            return result;
        }
        return baseTeamMemberDao.findByTeamCode(teamCode);
    }
}
