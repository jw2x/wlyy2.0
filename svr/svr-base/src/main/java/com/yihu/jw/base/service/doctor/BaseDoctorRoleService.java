package com.yihu.jw.base.service.doctor;

import com.yihu.jw.base.dao.doctor.BaseDoctorRoleDao;
import com.yihu.jw.entity.base.doctor.BaseDoctorRoleDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 医生与业务模块角色关联信息服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年10月25日 Created
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
    public List<BaseDoctorRoleDO> queryDoctorRoleList(String doctorCode) {
        List<BaseDoctorRoleDO> result = new ArrayList<>();
        if (StringUtils.isEmpty(doctorCode)) {
            return result;
        }
        return baseDoctorRoleDao.findByDoctorCode(doctorCode);
    }
}
