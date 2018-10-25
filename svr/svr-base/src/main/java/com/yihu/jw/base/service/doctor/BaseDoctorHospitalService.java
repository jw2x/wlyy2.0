package com.yihu.jw.base.service.doctor;

import com.yihu.jw.base.dao.doctor.BaseDoctorHospitalDao;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.doctor.BaseDoctorHospitalDO;

import java.util.*;

/**
 * 医生职业信息服务service
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class BaseDoctorHospitalService extends BaseJpaService<BaseDoctorHospitalDO, BaseDoctorHospitalDao> {

    @Autowired
    private BaseDoctorHospitalDao baseDoctorHospitalDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据机构标识和医生标识获取医生相关联的部门职务职称等
     *
     * @param hospCode
     * @param doctorCode
     * @return
     */
    List<BaseDoctorHospitalDO> getDoctorHospitalListInfo(String hospCode, String doctorCode) {
        List<BaseDoctorHospitalDO> result = new ArrayList<>();
        if (StringUtils.isEmpty(hospCode) || StringUtils.isEmpty(doctorCode)) {
            return result;
        }
        return baseDoctorHospitalDao.findByOrgCodeAndDoctorCode(hospCode, doctorCode);
    }

    /**
     * 根据医院标识和医生标识获取医生相关关联的部门职务职称Id列表
     *
     * @param doctorCode
     * @return
     */
    Set<Object> findDocHospIdList(String doctorCode) {
        Set<Object> result = new HashSet<>();
        if (StringUtils.isEmpty(doctorCode)) {
            return result;
        }
        return baseDoctorHospitalDao.findIdListByOrgCodeAndDoctorCode(doctorCode);
    }

    /**
     * 根据医生标识获取医生已经选择的机构和职务信息
     *
     * @param doctorCode
     * @return
     */
    List<Map<String, Object>> getOrgAndDutyListByDoctorCode(String doctorCode) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (StringUtils.isEmpty(doctorCode)) {
            return result;
        }
        String sql = "SELECT" +
                "  org_code         AS orgCode," +
                "  org_name         AS orgName," +
                "  doctor_duty_code AS doctorDutyCode," +
                "  doctor_duty_name AS doctorDutyName " +
                " FROM base_doctor_hospital" +
                " WHERE doctor_code = '{doctorCode}'" +
                " GROUP BY orgCode" +
                " ORDER BY doctorDutyName DESC";
        return jdbcTemplate.queryForList(sql.replace("{doctorCode}",doctorCode));
    }
}
