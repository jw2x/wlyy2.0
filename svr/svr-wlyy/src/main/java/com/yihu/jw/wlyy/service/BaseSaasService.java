package com.yihu.jw.wlyy.service;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.base.base.Saas;
import com.yihu.jw.wlyy.dao.BaseSaasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class BaseSaasService extends BaseJpaService<Saas, BaseSaasDao> {
    @Autowired
    private BaseSaasDao saasDao;

    public Saas findByCode(String code) {
        return saasDao.findByCode(code);
    }

    public Saas findByName(String cityName) {
        return saasDao.findByName(cityName);
    }
}
