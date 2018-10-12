package com.yihu.jw.healthyhouse.service.facility;

import com.yihu.jw.healthyhouse.dao.facility.FacilityServerDao;
import com.yihu.jw.healthyhouse.model.facility.Facility;
import com.yihu.jw.healthyhouse.model.facility.FacilityServer;
import com.yihu.mysql.query.BaseJpaService;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 设施服务管理器.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.19
 */
@Service
@Transactional
public class FacilityServerService extends BaseJpaService<FacilityServer, FacilityServerDao> {

    @Autowired
    private FacilityServerDao facilityServerDao;

    public FacilityServer findById(String id) {
        return  facilityServerDao.findById(id);
    }

    public List<FacilityServer> findByNameIn (List<String> names) {
       return facilityServerDao.findByNameIn(names);
    }

    /**
     * 设施服务编码生成
     *
     * @return
     */
    public String genFacilityServerCode() {
        String code = "HFHS" + randomString(5);
        FacilityServer facility = facilityServerDao.findByCode(code);
        while (facility != null) {
            code = "HFHS" + randomString(5);
            facility = facilityServerDao.findByCode(code);
        }
        return code;
    }


}
