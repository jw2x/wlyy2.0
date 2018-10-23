package com.yihu.jw.base.service.doctor;

import com.yihu.jw.base.dao.doctor.BaseDoctorRoleDao;
import com.yihu.jw.entity.base.doctor.BaseDoctorRoleDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 医生角色关联信息服务service
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年10月19日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class BaseDoctorRoleService extends BaseJpaService<BaseDoctorRoleDO, BaseDoctorRoleDao> {

    @Autowired
    private BaseDoctorRoleDao baseDoctorRoleDao;

    /**
     * 根据医生标识获取医生相关联的角色Id列表
     *
     * @param doctorCode
     * @return
     */
    public Set<Object> findRoleIdList(String doctorCode) {
        Set<Object> result = new HashSet<>();
        if (StringUtils.isEmpty(doctorCode)) {
            return result;
        }
        return baseDoctorRoleDao.findIdListByDoctorCode(doctorCode);
    }

    /**
     * 根据医生标识获取医生相关联的角色Id列表
     *
     * @param doctorCode
     * @return
     */
    public List<BaseDoctorRoleDO> queryDoctorRoleList(String doctorCode,String del) {
        List<BaseDoctorRoleDO> result = new ArrayList<>();
        if (StringUtils.isEmpty(doctorCode)) {
            return result;
        }
        return baseDoctorRoleDao.findByDoctorCodeAndDel(doctorCode,del);
    }
}
