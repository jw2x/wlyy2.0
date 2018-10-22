package com.yihu.jw.base.service.saas;

import com.yihu.jw.base.dao.saas.SaasBusinessCardDao;
import com.yihu.jw.base.service.doctor.BaseDoctorService;
import com.yihu.jw.base.service.patient.BasePatientService;
import com.yihu.jw.entity.base.saas.SaasBusinessCardDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service - SAAS名片
 * @author progr1mmer
 * @date Created on 2018/9/7.
 */
@Service
public class SaasBusinessCardService extends BaseJpaService<SaasBusinessCardDO, SaasBusinessCardDao> {

    @Autowired
    private BaseDoctorService doctorService;
    @Autowired
    private BasePatientService basePatientService;

    /**
     * 此接口配置的字段应和实体的字段名相同
     *
     * @param type
     * @param saasId
     * @param sourceId
     * @param orgId
     * @return
     * @throws Exception
     */
    public Map<String, Object> generateBusinessCard(SaasBusinessCardDO.Type type, String saasId, String sourceId, String orgId) throws Exception {
        List<SaasBusinessCardDO> saasBusinessCardDO = search("type=" + type  + ";saasId=" + saasId);
        /*Map<String, Object> card = new HashMap<>();
        if (type == SaasBusinessCardDO.Type.doctor) {
            //模拟加载医生数据
            Map<String, Object> source = doctorService.getDoctorInfo(orgId, sourceId);
            saasBusinessCardDO.forEach(item -> card.put(item.getField(), source.get(item.getField())));
            return card;
        } else if (type == SaasBusinessCardDO.Type.patient) {
            //模拟加载居民数据
            Map<String, Object> source = basePatientService.getPatientById(sourceId);
            saasBusinessCardDO.forEach(item -> card.put(item.getField(), source.get(item.getField())));
            return card;
        }
        return card;*/
        return null;
    }

}
