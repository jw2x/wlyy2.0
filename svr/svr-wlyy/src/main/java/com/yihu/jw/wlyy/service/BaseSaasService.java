package com.yihu.jw.wlyy.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.wlyy.dao.BaseSaasDao;
import com.yihu.jw.wlyy.entity.BaseSaas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class BaseSaasService extends BaseJpaService<BaseSaas, BaseSaasDao> {
    @Autowired
    private BaseSaasDao saasDao;

    public BaseSaas findByCode(String code) {
        return saasDao.findByCode(code);
    }

    public BaseSaas findByName(String cityName) {
        return saasDao.findByName(cityName);
    }
}
