package com.yihu.jw.wlyy.service;

import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.wlyy.dao.BaseSaasDao;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class BaseSaasService extends BaseJpaService<SaasDO, BaseSaasDao> {
    @Autowired
    private BaseSaasDao saasDao;

    public SaasDO findByCode(String code) {
        return saasDao.findByCode(code);
    }

    public SaasDO findByName(String cityName) {
        return saasDao.findByName(cityName);
    }
}
