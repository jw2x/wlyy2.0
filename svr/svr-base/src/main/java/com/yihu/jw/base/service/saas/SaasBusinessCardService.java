package com.yihu.jw.base.service.saas;

import com.yihu.jw.base.dao.saas.SaasBusinessCardDao;
import com.yihu.jw.base.service.doctor.BaseDoctorService;
import com.yihu.jw.entity.base.saas.SaasBusinessCardDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service - SAAS名片
 * Created by progr1mmer on 2018/9/7.
 */
@Service
public class SaasBusinessCardService extends BaseJpaService<SaasBusinessCardDO, SaasBusinessCardDao> {

    @Autowired
    private BaseDoctorService doctorService;

    public Map<String, Object> generateBusinessCard(SaasBusinessCardDO.Type type, String saasId, String id) throws Exception {
        List<SaasBusinessCardDO> saasBusinessCardDO = search("type=" + type  + ";saasId=" + saasId);
        Map<String, Object> card = new HashMap<>();
        if (type == SaasBusinessCardDO.Type.doctor) {
            //模拟加载医生数据
            Map<String, Object> source = new HashMap<>();
            saasBusinessCardDO.forEach(item -> card.put(item.getField(), source.get(item.getField())));
            return card;
        } else if (type == SaasBusinessCardDO.Type.patient) {
            //模拟加载居民数据
            Map<String, Object> source = new HashMap<>();
            saasBusinessCardDO.forEach(item -> card.put(item.getField(), source.get(item.getField())));
            return card;
        }
        return card;
    }

}
